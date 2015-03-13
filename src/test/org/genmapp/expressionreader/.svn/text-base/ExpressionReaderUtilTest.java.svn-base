/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader;

import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.geo.data.GSE;
import java.util.List;
import org.genmapp.expressionreader.geo.data.GDS;
import org.genmapp.expressionreader.geo.data.SOFT;
import java.io.File;
import org.genmapp.expressionreader.geo.data.SOFT.Type;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author djiao
 */
public class ExpressionReaderUtilTest {

    public ExpressionReaderUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDownloadSOFTGZ() throws Exception {
        String url = String.format(GEOQuery.GDS_FTP, "GDS507");
        boolean result = GEOQuery.downloadSOFTGZ(url, new File(System.getProperty("java.io.tmpdir"), "GDS507.soft"));
        assertEquals(true, result);
        assertEquals(true, new File(System.getProperty("java.io.tmpdir"), "GDS507.soft").exists());
    }

    @Test
    public void testGetGDS() throws Exception {
        GDS soft = (GDS)GEOQuery.getGDS("GDS507");
        assertNotNull(soft);
        assertEquals(12, soft.getSubsets().size());

        SOFT gsm = (SOFT)GEOQuery.getGDS("GSM11805");
        assertEquals(22283, gsm.getDataTables().getFirst().getData().size());
    }

    @Test
    public void testGetGSE() throws Exception {
        GSE soft = (GSE) GEOQuery.getGSE("GSE8854", SOFT.Format.full);
        assertNotNull(soft);
        assertEquals(9, soft.getPlatforms().size());
    }

    @Test
    public void testGetGSE2() throws Exception {
        GSE soft = (GSE) GEOQuery.getGSE("GSE9914", SOFT.Format.quick);
        assertNotNull(soft);
        assertEquals(6, soft.getDataTables().size());
    }

    /**
     * Test of downloadURL method, of class ExpressionReaderUtil.
     */
    @Test
    public void testDownloadURL() {
        System.out.println("downloadURL");
        String urlStr = "";
        File file = null;
        boolean expResult = false;
        boolean result = GEOQuery.downloadURL(urlStr, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of openURL method, of class ExpressionReaderUtil.
     */
    @Test
    public void testOpenURL() {
        System.out.println("openURL");
        String url = "";
        GEOQuery.openURL(url);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of join method, of class ExpressionReaderUtil.
     */
    @Test
    public void testJoin() {
        System.out.println("join");
        List<? extends CharSequence> s = null;
        String delimiter = "";
        String expResult = "";
        String result = GEOQuery.join(s, delimiter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSoftNameLblText method, of class ExpressionReaderUtil.
     */
    @Test
    public void testGetSoftNameLblText() {
        System.out.println("getSoftNameLblText");
        SOFT soft = null;
        String expResult = "";
        String result = GEOQuery.getSoftNameLblText(soft);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class ExpressionReaderUtil.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String geoId = "";
        Type expResult = null;
        Type result = GEOQuery.getType(geoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}