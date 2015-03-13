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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.tasks;

import gov.nih.nlm.ncbi.soap.eutils.EUtilsService;
import gov.nih.nlm.ncbi.soap.eutils.EUtilsServiceSoap;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchRequest;
import gov.nih.nlm.ncbi.soap.eutils.esearch.ESearchResult;
import gov.nih.nlm.ncbi.soap.eutils.esummary.DocSumType;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ESummaryRequest;
import gov.nih.nlm.ncbi.soap.eutils.esummary.ESummaryResult;
import java.util.List;
import javax.swing.SwingUtilities;
import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.ui.SearchResultViewer;

/**
 *
 * @author djiao
 */
public class GEOSearchTask extends AbstractTask {
    
    private ESearchRequest query = null;
    private SearchResultViewer viewer = null;

    public GEOSearchTask(ESearchRequest query, SearchResultViewer viewer) {
        super();
        this.query = query;
        this.viewer = viewer;
    }

    public void run() {
        EUtilsService service = new EUtilsService();
        EUtilsServiceSoap clientStub = service.getEUtilsServiceSoap();
        final ESearchResult result = clientStub.runESearch(query);
        final int total = Integer.parseInt(result.getCount());
        String ids = GEOQuery.join(result.getIdList().getId(), ",");

        ESummaryRequest req = new ESummaryRequest();
        req.setDb("gds");
        req.setId(ids);

        ESummaryResult res = clientStub.runESummary(req);
        final List<DocSumType> docsum = res.getDocSum();

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                viewer.viewResults(total, docsum);
            }
        });
    }

    public String getTitle() {
        return "Searching GEO";
    }

}
