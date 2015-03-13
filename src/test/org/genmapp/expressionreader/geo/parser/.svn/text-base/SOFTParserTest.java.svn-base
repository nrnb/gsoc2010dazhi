package org.genmapp.expressionreader.geo.parser;

import java.io.File;
import java.io.FileInputStream;
import static org.junit.Assert.assertEquals;
import org.genmapp.expressionreader.geo.data.SOFT;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import org.genmapp.expressionreader.geo.GEOQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author djiao
 *
 */
public class SOFTParserTest {

    static String root;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String path = SOFTParser.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        root = path.substring(0, path.indexOf("build/classes/"));
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGSM() throws Exception {
        URL url = new URL(String.format(GEOQuery.GEO_URL, "GSM11805", "text", SOFT.Format.quick));

        InputStream gplIn = url.openConnection().getInputStream();
        SOFT gsm = new SOFTParser().parse(gplIn, SOFT.Type.GSM);
        if (gplIn != null) {
            gplIn.close();
        }

        assertEquals(20, gsm.getDataTables().getFirst().getData().size());
        assertEquals("617-414-1646", gsm.getFields().get("Sample_contact_fax"));

        assertEquals(3, gsm.getDataTables().getFirst().getHeaders().keySet().size());
/*
        url = new URL(String.format(ExpressionReaderUtil.GEO_URL, "GSM11805", "text", SOFT.Format.full));

        gplIn = url.openConnection().getInputStream();
        gsm = new SOFTParser().parseSOFT(gplIn, SOFT.Type.GSM, SOFT.Format.full);
        if (gplIn != null) {
            gplIn.close();
        }
        assertEquals(22283, gsm.getDataTables().getFirst().getData().size());

 */
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGPL() throws Exception {
        URL url = new URL(String.format(GEOQuery.GEO_URL, "GPL96", "text", SOFT.Format.quick));

        InputStream gplIn = url.openConnection().getInputStream();
        SOFT gpl = new SOFTParser().parse(gplIn, SOFT.Type.GPL);
        if (gplIn != null) {
            gplIn.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(20, gpl.getDataTables().getFirst().getData().size());
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGSE() throws Exception {
        URL url = new URL(String.format(GEOQuery.GSE_FAMILY, "GSE9914", SOFT.Format.quick));

        InputStream in = url.openConnection().getInputStream();
        SOFT soft = new SOFTParser().parseGSE(new InputStreamReader(in));
        if (in != null) {
            in.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(6, soft.getDataTables().size());
    }

    /**
     * Test method for {@link geReader.SOFTParser#parseSOFT(java.io.InputStream, geReader.data.SOFT.Type)}.
     */
    @Test
    public void testParseGSEFamily() throws Exception {
        URL url = new URL(String.format(GEOQuery.GSE_FTP, "GSE9914"));

        InputStream in = url.openConnection().getInputStream();
        SOFT soft = new SOFTParser().parseGSE(new InputStreamReader(new GZIPInputStream(in)));
        if (in != null) {
            in.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(6, soft.getDataTables().size());
    }

    @Test
    public void testParseGDS() throws Exception {
        URL url = new URL(String.format(GEOQuery.GDS_FTP, "GDS507"));

        InputStream in = url.openConnection().getInputStream();
        SOFT soft = new SOFTParser().parseGDS(new InputStreamReader(new GZIPInputStream(in)));
        if (in != null) {
            in.close();
        }

        // Show the list of fields to let people to map to column
        assertEquals(1, soft.getDataTables().size());
    }

    @Test
    public void testParserGSM2() throws Exception {
        File file = new File(root, "data/GSM1351-full.txt");
        FileInputStream in = new FileInputStream(file);
        SOFT soft = new SOFTParser().parse(in, SOFT.Type.GSM);
        if (in != null) {
            in.close();
        }
        assertEquals(1, soft.getDataTables().size());
    }
}
