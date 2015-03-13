/*
 Copyright 2010 Alexander Pico
 Licensed under the Apache License, Version 2.0 (the "License"); 
 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 	
 	http://www.apache.org/licenses/LICENSE-2.0 
 	
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, 
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 See the License for the specific language governing permissions and 
 limitations under the License. 
 */

package org.genmapp.expressionreader.geo.parser;

import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.geo.data.GSE;
import org.genmapp.expressionreader.geo.data.SOFT;
import org.genmapp.expressionreader.geo.data.SOFT.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.genmapp.expressionreader.geo.data.GDS;

public class SOFTParser {

    public static final String SAMPLE = "SAMPLE";
    public static final String PLATFORM = "PLATFORM";
    private static final String ID_PATTERN = "\\^(.*?)\\s=\\s(.*)";
    private static final String FIELD_PATTERN = "\\!(.*?)\\s=\\s(.*)";
    private static final String TABLE_HEADER_PATTERN = "\\#(.*?)\\s=\\s(.*)";
    private static final String TABLE_PATTERN = "\\!(.*)_table_begin(\\s=\\s(.*))?";
    private String currentId = null;
    private String currentType = null;
    private int lineNumber = 0;

    private SOFT parseSOFTSection(BufferedReader in) throws IOException, ParseException {
        SOFT soft = new SOFT(currentId);
        soft.setTypeStr(currentType);
        Pattern fieldPattern = Pattern.compile(FIELD_PATTERN);
        Pattern tableheaderPattern = Pattern.compile(TABLE_HEADER_PATTERN);
        Pattern idPattern = Pattern.compile(ID_PATTERN);
        Pattern tablePattern = Pattern.compile(TABLE_PATTERN);

        LinkedList<DataTable> tables = new LinkedList<DataTable>();
        LinkedHashMap<String, String> header = new LinkedHashMap<String, String>();
        LinkedHashMap<String, List> data = new LinkedHashMap<String, List>();

        DataTable table = null;

        String line = null;
        LinkedHashMap<String, Object> fields = new LinkedHashMap<String, Object>();
        boolean inTable = false;

        int start = lineNumber;
        while ((line = in.readLine()) != null) {
            if ("".equals(line.trim())) {
                // Some files have two types of line terminators (\r\n and \n)
                // This becomes a problem in parsing. The parser will break
                // them into two lines, and failed to parse. So there
                // we have to skip blank lines
                // See: http://socrates2.cgl.ucsf.edu/GenMAPP/ticket/24
                continue;
            }
            lineNumber++;
            Matcher m = idPattern.matcher(line); // end parsing this section
            if (m.matches()) {
                currentType = m.group(1);
                currentId = m.group(2);
                break;
            }

            m = tableheaderPattern.matcher(line);
            if (m.matches()) {
                header.put(m.group(1), m.group(2));
                continue;
            }

            m = tablePattern.matcher(line); // table starting line
            if (m.matches()) {

                inTable = true;
                in.readLine(); // consumes the header line
                lineNumber++;
                table = new DataTable(m.group(3));
                continue;
            }

            if (inTable) {
                if (line.indexOf("_table_end") > 0) { // end of table
                    inTable = false;
                    table.setData(data);
                    table.setHeaders(header);
                    tables.add(table);

                    header = new LinkedHashMap<String, String>();
                    data = new LinkedHashMap<String, List>();
                } else {
                    List<String> row = Arrays.asList(line.split("\t", -1)); // interesting, see http://stackoverflow.com/questions/545957/java-split-method-strips-empty-strings-at-the-end
                    data.put(row.get(0), row);
                }
                continue;
            }

            m = fieldPattern.matcher(line);  // NOTE! This must be put after checking table header
            if (m.matches()) {  // in a field
                String field = m.group(1);
                String value = m.group(2);
                if (fields.containsKey(field)) {
                    Object obj = fields.get(field);
                    List<String> list = null;
                    if (obj instanceof String) {
                        list = new ArrayList<String>();
                        list.add((String) obj);
                        list.add(value);
                    } else {
                        list = (List<String>) obj;
                        list.add(value);
                    }
                    fields.put(field, list);
                } else {
                    fields.put(field, value);
                }
                continue;
            }

            throw new ParseException("Failed to parse line (" + lineNumber + "): " + line, lineNumber);
        }

        if (lineNumber > start) { // check if any line was parsed
            soft.setFields(fields);
            soft.setDataTables(tables);
            return soft;
        } else {
            return null;
        }
    }
    
    public SOFT parse(InputStream in, SOFT.Type type) throws ParseException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return parse(reader, type);
    }

    public SOFT parse(Reader in, SOFT.Type type) throws ParseException {
        SOFT soft = null;
        if (SOFT.Type.GDS == type) {
            soft = parseGDS(in);
        } else if (SOFT.Type.GSE == type) {
            soft = parseGSE(in);
        } else {
            soft = parseSOFT(in, type);
        }
        return soft;
    }

    public GDS parseGDS(Reader in) throws ParseException {
        GDS gds = null;
        BufferedReader reader = null;
        try {
            reader = in instanceof BufferedReader ? (BufferedReader) in : new BufferedReader(in);
            Pattern idPattern = Pattern.compile(ID_PATTERN);
            String line = reader.readLine();
            lineNumber++;
            Matcher m = idPattern.matcher(line);

            SOFT database = null;
            if (m.matches()) {
                this.currentId = m.group(2);
                this.currentType = m.group(1);

                if (!"DATABASE".equals(currentType)) {
                    throw new ParseException(String.format("[%d] Wrong type: %s, where expecting DATABASE.", lineNumber, currentType), lineNumber);
                }
                database = parseSOFTSection(reader);
            }

            SOFT soft = parseSOFTSection(reader);
            gds = new GDS(soft);
            gds.setDatabase(database);

            List<SOFT> subsets = new ArrayList<SOFT>();
            while ((soft = parseSOFTSection(reader)) != null) {
                if ("SUBSET".equals(soft.getTypeStr())) {
                    subsets.add(soft);
                } else if ("DATASET".equals(soft.getTypeStr())) {
                    gds.setDataTables(soft.getDataTables());
                } else {
                    throw new ParseException(String.format("[%d] Wrong type found: %s", lineNumber, soft.getType()), lineNumber);
                }
            }
            gds.setSubsets(subsets);
        } catch (IOException ex) {
            Logger.getLogger(SOFTParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gds;
    }

    public GSE parseGSE(Reader in) throws ParseException {
        GSE gse = null;
        BufferedReader reader = null;
        try {
            reader = in instanceof BufferedReader ? (BufferedReader) in : new BufferedReader(in);
            Pattern idPattern = Pattern.compile(ID_PATTERN);
            String line = reader.readLine();
            lineNumber++;
            Matcher m = idPattern.matcher(line);
            SOFT database = null;
            if (m.matches()) {
                this.currentId = m.group(2);
                this.currentType = m.group(1);

                if ("DATABASE".equals(currentType)) {
                    database = parseSOFTSection(reader);
                } else if (!"SERIES".equals(currentType)) {
                    throw new ParseException(String.format("[%d] Wrong type: %s, where expecting DATABASE.", lineNumber, currentType), lineNumber);
                }

            }

            SOFT soft = parseSOFTSection(reader);
            gse = new GSE(soft);
            gse.setDatabase(database);

            List<SOFT> platforms = new ArrayList<SOFT>();
            List<SOFT> samples = new ArrayList<SOFT>();
            while ((soft = parseSOFTSection(reader)) != null) {
                if ("PLATFORM".equals(soft.getTypeStr())) {
                    platforms.add(soft);
                } else if ("SAMPLE".equals(soft.getTypeStr())) {
                    samples.add(soft);
                } else {
                    throw new ParseException(String.format("[%d] Wrong type found: %s", lineNumber, soft.getType()), lineNumber);
                }
            }
            gse.setPlatforms(platforms);
            gse.setSamples(samples);
        } catch (IOException ex) {
            Logger.getLogger(SOFTParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioex) {
                }
            }
        }
        return gse;
    }

    private SOFT parseSOFT(Reader in, Type type) throws ParseException {
        SOFT soft = null;
        BufferedReader reader = in instanceof BufferedReader ? (BufferedReader) in : new BufferedReader(in);
        try {
            Pattern idPattern = Pattern.compile(ID_PATTERN);
            String line = reader.readLine();
            lineNumber++;
            Matcher m = idPattern.matcher(line);
            if (m.matches()) {
                this.currentId = m.group(2);
                this.currentType = m.group(1);
                if (!getTypeHeader(type).equals(currentType)) {
                    throw new ParseException("Wrong type: " + currentType + ", where expecting " + getTypeHeader(type), 0);
                }
                soft = (SOFT) parseSOFTSection(reader);
            } else {
                throw new ParseException("Invalid first line", 0);
            }
        } catch (IOException ex) {
            Logger.getLogger(SOFTParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioex) {
                }
            }
        }

        return soft;
    }

    public static String getTypeHeader(SOFT.Type type) {
        switch (type) {
            case GPL:
                return "PLATFORM";
            case GSM:
                return "SAMPLE";
            case GSE:
                return "SERIES";
            case GDS:
                return "DATASET";
            default:
                return "";
        }
    }
}
