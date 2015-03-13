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

package org.genmapp.expressionreader;


import javax.swing.JMenu;

import org.genmapp.expressionreader.actions.GEOImportAction;
import cytoscape.Cytoscape;
import cytoscape.command.CyCommandManager;
import cytoscape.command.CyCommandNamespace;
import cytoscape.logger.CyLogger;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.CytoscapeDesktop;
import org.genmapp.expressionreader.commands.GEOImportCyCommandHandler;

/**
 * This plugin reads gene expression data from GEO and ArrayExpress
 */
public class ExpressionReaderPlugin extends CytoscapePlugin {

    private static CyLogger logger = CyLogger.getLogger(ExpressionReaderPlugin.class);

    /**
     * This is the main constructor, which will be called by Cytoscape's Plugin
     * Manager. Add our listeners and create the main menu.
     */
    public ExpressionReaderPlugin() {
        try {
            // Set ourselves up to listen for new networks
            Cytoscape.getDesktop().getSwingPropertyChangeSupport().addPropertyChangeListener(CytoscapeDesktop.NETWORK_VIEW_CREATED, this);
        } catch (ClassCastException ccex) {
            logger.error("Unable to setup network listeners: " + ccex.getMessage(), ccex);
            return;
        }

        JMenu pluginMenu = Cytoscape.getDesktop().getCyMenus().getOperationsMenu();
        JMenu readerMenu = new JMenu("Gene Expression Reader");
        pluginMenu.add(readerMenu);

        try {
            readerMenu.add(new GEOImportAction());
        } catch (Exception e) {
            logger.error("Unable to initialize menus: " + e.getMessage(), e);
        }

        // register CyCommand
        CyCommandNamespace ns = CyCommandManager.reserveNamespace(GEOImportCyCommandHandler.NAMESPACE);
        CyCommandManager.register(ns, GEOImportCyCommandHandler.COMMAND_TOGGLE, new GEOImportCyCommandHandler());
    }
}
