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

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.TaskMonitor;
import cytoscape.task.ui.JTaskConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.geo.data.SOFT;

/**
 *
 * @author djiao
 */
public class GSMImportTask extends AbstractTask {

    /**
     * The network key attribute to map
     */
    private String nodeAttr;

    /**
     * List of GSM data
     */
    private List<SOFT> softList;

    public void run() {
        CyNetwork network = Cytoscape.getCurrentNetwork();
        List<CyNode> nodes = (List<CyNode>) network.nodesList();
        CyAttributes cyattrs = Cytoscape.getNodeAttributes();

        if (taskMonitor != null) {
            taskMonitor.setStatus("Preparing for importing Data into Network " + network.getTitle());
        }

        Map<String, Integer> summary = new HashMap<String, Integer>();

        for (SOFT soft : softList) {
            DataTable dt = soft.getDataTables().getFirst(); // GSM should only have one datatable
            Map<String, List> importedData = dt.getData();

            if (taskMonitor != null) {
                taskMonitor.setStatus("Importing Data into Network");
            }
            
            int totalAdded = 0;
            String attrName = getAttrName(cyattrs, nodes.get(0), "VALUE", soft);

            int valueIndex = new ArrayList(dt.getHeaders().keySet()).indexOf("VALUE");

            // Now map imported data to network nodes
            for (int i = 0; i < nodes.size(); i++) {
                CyNode node = nodes.get(i);
                String nid = node.getIdentifier();

                if ("ID".equals(nodeAttr)) {
                    String attrValue = node.getIdentifier();
                    if (importedData.containsKey(attrValue)) {
                        cyattrs.setAttribute(nid, attrName, Double.valueOf((String) importedData.get(attrValue).get(valueIndex)));
                        totalAdded++;
                    }
                } else {
                    switch (cyattrs.getType(nodeAttr)) {
                        case CyAttributes.TYPE_SIMPLE_LIST:
                            List list = cyattrs.getListAttribute(nid, nodeAttr);
                            Set<String> keys = importedData.keySet();
                            for (String key: keys) {
                                if (list.contains(key)) {
                                    cyattrs.setAttribute(nid, attrName, Double.valueOf((String) importedData.get(key).get(valueIndex)));
                                    break;
                                }
                            }
                            totalAdded++;
                            break;
                        case CyAttributes.TYPE_STRING:
                            String attrValue = cyattrs.getStringAttribute(nid, nodeAttr);
                            if (importedData.containsKey(attrValue)) {
                                cyattrs.setAttribute(nid, attrName, Double.valueOf((String) importedData.get(attrValue).get(valueIndex)));
                                totalAdded++;
                            }
                            break;
                        default:
                            JOptionPane.showConfirmDialog(null, "Can't import. Only support LIST and STRING attribute type. Wrong attribute type: " + nodeAttr,
                                    "Error", JOptionPane.OK_OPTION);
                    }
                }
            }
            summary.put(soft.getId(), totalAdded);
        }
        if (taskMonitor != null) {
            StringBuilder status = new StringBuilder();
            status.append("Import complete. Imported ");
            status.append(softList.size());
            status.append(" samples.\n");
            status.append("Summary: ");
            status.append("Network total number of nodes: ");
            status.append(nodes.size());
            status.append(".\n");
            for (Map.Entry<String, Integer> entry: summary.entrySet()) {
                status.append("Sample: ");
                status.append(entry.getKey());
                status.append(", number of value imported: ");
                status.append(entry.getValue());
                status.append(".\n");
            }

            taskMonitor.setStatus(status.toString());
        }
    }

    public GSMImportTask(String networkKeyAttr, List<SOFT> softList) {
        this.nodeAttr = networkKeyAttr;
        this.softList = softList;
    }

    public String getTitle() {
        return "Importing Gene Expression Data";
    }

    /**
     * Returns a new attribute name 
     *
     * @param attrs
     * @param node
     * @param tgtValueAttr
     * @param soft
     * @return
     */
    public static String getAttrName(CyAttributes attrs, CyNode node, String tgtValueAttr, SOFT soft) {
        String name = String.format("%s[%s]", soft.getId(), tgtValueAttr);
        if (attrs.hasAttribute(node.getIdentifier(), name)) {
            int i = 1;
            do {
                name = String.format("%s[%s-%d]", soft.getId(), tgtValueAttr, i);
                i = i+1;
            } while (attrs.hasAttribute(node.getIdentifier(), name));
        }
        return name;
    }

    public JTaskConfig getDefaultTaskConfig() {
        JTaskConfig config = new JTaskConfig();

        config.setOwner(null);
        config.displayCancelButton(true);
        config.displayCloseButton(true);
        config.displayStatus(true);
        config.setAutoDispose(false);

        return config;
    }
}
