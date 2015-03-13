/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.genmapp.expressionreader.commands;

import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.command.CyCommandException;
import cytoscape.command.CyCommandHandler;
import cytoscape.command.CyCommandResult;
import cytoscape.data.CyAttributes;
import cytoscape.layout.Tunable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.geo.data.SOFT;
import org.genmapp.expressionreader.geo.ui.SOFTViewer;

/**
 *
 * @author djiao
 */
public class GEOImportCyCommandHandler implements CyCommandHandler, SOFTViewer {
    public final static String NAMESPACE = "expression data reader";
    public final static String COMMAND_TOGGLE = "create network";
    public static boolean USE_ID_MAPPING = false;

    private Map<String, List<Tunable>> settings = new HashMap<String, List<Tunable>>();

    public GEOImportCyCommandHandler() {
        Tunable t = new Tunable("toggle", "Whether to create a network from imported GEO sample", Tunable.STRING, "false");
        List<Tunable> list = new ArrayList<Tunable>();
        list.add(t);
        settings.put(COMMAND_TOGGLE, list);

        /*
        t = new Tunable("geoID", "GEO Sample ID (GSMxxxxx)", Tunable.STRING, null);
        list = new ArrayList<Tunable>();
        list.add(t);
        settings.put(COMMAND_IMPORT, list);
         */
    }
    
    public List<String> getCommands() {
        List<String> val = new ArrayList<String>();
        val.add(COMMAND_TOGGLE);
        //val.add(COMMAND_IMPORT);
        return val;
    }

    public List<String> getArguments(String command) {
        List<Tunable> list = settings.get(command);
        if (list == null) return null;
        List<String> args = new ArrayList<String>();
        for (Tunable t : list) {
            args.add(t.getName());
        }
        return args;
    }

    public Map<String, Object> getSettings(String command) {
        List<Tunable> list = settings.get(command);
        Map<String, Object> val = new HashMap<String, Object>();
        for (Tunable t: list) {
            val.put(t.getName(), t.getValue());
        }
        return val;
    }

    public Map<String, Tunable> getTunables(String command) {
        Map<String, Tunable> map = new HashMap<String, Tunable>();
        List<Tunable> list = settings.get(command);
        for (Tunable t : list) {
            map.put(t.getName(), t);
        }
        return map;
    }

    public String getDescription(String string) {
        return "This CyCommandHandler imports Samples Gene Expression Data Reader";
    }

    public CyCommandResult execute(String command, Map<String, Object> map) throws CyCommandException {
        CyCommandResult result = new CyCommandResult();
        if (COMMAND_TOGGLE.equals(command)) {
            boolean val = Boolean.parseBoolean((String)map.get("toggle"));
            USE_ID_MAPPING = val;
            result.addMessage("toggle set to " + val);
        } else {
            result.addError("Command not supported: " + command);
        }
            /*else if (COMMAND_IMPORT.equals(command)) {
            // download
            String geoID = (String)map.get("geoID");
            SOFT.Type type = GEOQuery.getType(geoID);
            SOFTDownloadTask task = null;
            if (type == SOFT.Type.GSM) {
                task = new SOFTDownloadTask(new String[]{geoID}, this, SOFT.Format.full);
            } else {
                result.addError("Invalid GEO ID. It has to start with GSM");
                return result;
            }
            JTaskConfig config = task.getDefaultTaskConfig();
            boolean res = TaskManager.executeTask(task, config);
            if (res) {
                result.addMessage("Network [" + geoID + "] was successfully created.");
            }
        }*/
        return result;
    }

    public CyCommandResult execute(String string, Collection<Tunable> clctn) throws CyCommandException {
        Map<String, Object> kvSettings = new HashMap();
        for (Tunable t: clctn) {
            Object v = t.getValue();
            if (v != null)
                kvSettings.put(t.getName(), v.toString());
            else
                kvSettings.put(t.getName(), null);
        }
        return execute(string, kvSettings);
    }

    public void viewSOFT(List<SOFT> list) {
        CyAttributes cyattrs = Cytoscape.getNodeAttributes();
        for (SOFT s : list) {
            DataTable dt = s.getDataTables().get(0);

            Set<String> headers = dt.getHeaders().keySet();
            Set<String> keys = dt.getData().keySet();
            List<CyNode> nodes = new ArrayList<CyNode>();
            for (String key : keys) {
                CyNode node = Cytoscape.getCyNode(key, true);
                int headerIndex = 0;
                for (String header : headers) {
                    cyattrs.setAttribute(key, header, (String) dt.getData().get(key).get(headerIndex));
                    headerIndex++;
                }
                nodes.add(node);
            }
            Cytoscape.createNetwork(nodes, new ArrayList<CyEdge>(), s.getId());
        }
    }
}
