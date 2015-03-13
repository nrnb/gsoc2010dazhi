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

package org.genmapp.expressionreader.geo.data;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Data model for SOFT files
 *
 * @author djiao
 */
public class SOFT {

    /**
     * The type of the SOFT file
     */
    public enum Type {

        GSM, GSE, GPL, GDS
    }

    private String typeStr;

    public enum Format {
        full, brief, quick, data, family
    }

    private LinkedHashMap<String, Object> fields;
    private LinkedList<DataTable> dataTables;
    private String id;
    private Type type;
    private Format format;

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public LinkedList<DataTable> getDataTables() {
        return dataTables;
    }

    public void setDataTables(LinkedList<DataTable> dataTables) {
        this.dataTables = dataTables;
    }

    /**
     * Constructor
     */
    public SOFT() {
        super();
    }

    /**
     * Constructor
     * @param id id of the SOFT file
     */
    public SOFT(String id) {
        super();
        this.id = id;
    }

    /**
     * Returns the type
     * @return
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Returns the fields
     * @return
     */
    public LinkedHashMap<String, Object> getFields() {
        return fields;
    }

    /**
     * Sets the fields
     * @param fields
     */
    public void setFields(LinkedHashMap<String, Object> fields) {
        this.fields = fields;
    }

    /**
     * Returns ID
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets ID
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

}
