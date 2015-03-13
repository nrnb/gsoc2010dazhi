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

/*
 * GSMViewerDialog.java
 *
 * Created on Jun 30, 2010, 10:23:05 AM
 */

package org.genmapp.expressionreader.geo.ui;

import cytoscape.Cytoscape;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.genmapp.expressionreader.geo.data.SOFT;
import org.genmapp.expressionreader.geo.GEOQuery;
import org.genmapp.expressionreader.tasks.SOFTDownloadTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxUI.PropertyChangeHandler;
import javax.swing.table.AbstractTableModel;
import org.genmapp.expressionreader.actions.GEOImportAction;
import org.genmapp.expressionreader.geo.data.DataTable;
import org.genmapp.expressionreader.geo.data.GSE;
import org.genmapp.expressionreader.ui.GSMImportDialog;
import org.genmapp.expressionreader.ui.GroupPane;

/**
 *
 * @author djiao
 */
public class GSEViewerDialog extends javax.swing.JDialog implements SOFTViewer {
    private SOFT soft;
    private Map<String, List<SOFT>> groupMap;

    /** Creates new form GSMViewerDialog */
    public GSEViewerDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initDnD();
        groupMap = new LinkedHashMap<String, List<SOFT>>();
    }

    public void setSOFT(SOFT soft) {
        this.soft = soft;
        final GSE gse = (GSE) soft;
        this.setTitle(soft.getId());
        sampleTable.setModel(new AbstractTableModel() {

            List<SOFT> samples = gse.getSamples();

            @Override
            public String getColumnName(int i) {
                switch (i) {
                    case 0: return "ID";
                    case 1: return "Title";
                    case 2: return "Source";
                    default: return "";
                }
            }

            public int getRowCount() {
                return samples.size();
            }

            public int getColumnCount() {
                return 3;
            }

            public Object getValueAt(int i, int i1) {
                SOFT sample = samples.get(i);
                if (i1 == 0) {
                    return sample.getId();
                } else if (i1 == 1) {
                    return sample.getFields().get("Sample_title");
                } else {
                    return sample.getFields().get("Sample_source_name_ch1");
                }
            }
        });

        platformTable.setModel(new AbstractTableModel() {

            List<SOFT> platforms = gse.getPlatforms();

            @Override
            public String getColumnName(int i) {
                if (i == 0) {
                    return "ID";
                } else {
                    return "Title";
                }
            }

            public int getRowCount() {
                return platforms.size();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int i, int i1) {
                SOFT sample = platforms.get(i);
                if (i1 == 0) {
                    return sample.getId();
                } else {
                    return sample.getFields().get("Platform_title");
                }
            }
        });

        metadataTable.setModel(new AbstractTableModel() {

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                LinkedHashMap<String, Object> fields = gse.getFields();
                List<String> fieldNames = new ArrayList<String>(fields.keySet());
                if (columnIndex == 0) {
                    return fieldNames.get(rowIndex);
                } else {
                    Object obj = fields.get(fieldNames.get(rowIndex));
                    if (obj instanceof String) {
                        return obj;
                    } else {
                        List<String> list = (List) obj;
                        return org.genmapp.expressionreader.geo.GEOQuery.join(list, "\n");
                    }
                }
            }

            @Override
            public int getRowCount() {
                return gse.getFields().size();
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public String getColumnName(int column) {
                return column == 0 ? "Field" : "Value";
            }
        });

        nameLbl.setText(org.genmapp.expressionreader.geo.GEOQuery.getSoftNameLblText(soft));

        Set<String> sources = new HashSet<String>();
        for (SOFT sample: gse.getSamples()) {
            String source = (String)sample.getFields().get("Sample_source_name_ch1");
            List<SOFT> list = groupMap.get(source);
            if (list == null) {
                list = new ArrayList<SOFT>();
            }
            list.add(sample);
            groupMap.put(source, list);
        }
        groupList.setModel(new AbstractListModel() {

            public int getSize() {
                return groupMap.keySet().size();
            }

            public Object getElementAt(int i) {
                return new ArrayList(groupMap.keySet()).get(i);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        contentSplitPane = new javax.swing.JSplitPane();
        sampleTabbedPane = new javax.swing.JTabbedPane();
        leftPane = new javax.swing.JSplitPane();
        gsmContentTabbedPane = new javax.swing.JTabbedPane();
        samplePane = new javax.swing.JPanel();
        sampleButtonPane = new javax.swing.JPanel();
        viewSampleBtn = new javax.swing.JButton();
        importSampleBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        sampleTable = new javax.swing.JTable();
        platformPane = new javax.swing.JPanel();
        platformButtonPane = new javax.swing.JPanel();
        viewPlatformBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        platformTable = new javax.swing.JTable();
        groupsPane = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        addGroupBtn = new javax.swing.JButton();
        removeGroupBtn = new javax.swing.JButton();
        renameGroupBtn = new javax.swing.JButton();
        viewGroupBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList();
        gsmInfoPane = new javax.swing.JPanel();
        metadataWrapperPane = new javax.swing.JPanel();
        metadataScrollPane = new javax.swing.JScrollPane();
        metadataTable = new javax.swing.JTable();
        namePane = new javax.swing.JPanel();
        nameLbl = new javax.swing.JLabel();
        viewInBrowserPane = new javax.swing.JPanel();
        viewInBroswerBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 400));

        sampleTabbedPane.setPreferredSize(new java.awt.Dimension(250, 500));
        contentSplitPane.setRightComponent(sampleTabbedPane);

        leftPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        gsmContentTabbedPane.setPreferredSize(new java.awt.Dimension(300, 200));

        samplePane.setLayout(new java.awt.BorderLayout());

        viewSampleBtn.setText("  View  ");
        viewSampleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSampleBtnActionPerformed(evt);
            }
        });
        sampleButtonPane.add(viewSampleBtn);

        importSampleBtn.setText(" Import ");
        importSampleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importSampleBtnActionPerformed(evt);
            }
        });
        sampleButtonPane.add(importSampleBtn);

        samplePane.add(sampleButtonPane, java.awt.BorderLayout.PAGE_END);

        sampleTable.setDragEnabled(true);
        sampleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sampleTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sampleTable);

        samplePane.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        gsmContentTabbedPane.addTab("Samples", samplePane);

        platformPane.setLayout(new java.awt.BorderLayout());

        viewPlatformBtn.setText("  View  ");
        viewPlatformBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPlatformBtnActionPerformed(evt);
            }
        });
        platformButtonPane.add(viewPlatformBtn);

        platformPane.add(platformButtonPane, java.awt.BorderLayout.PAGE_END);

        platformTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                platformTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(platformTable);

        platformPane.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        gsmContentTabbedPane.addTab("Platforms", platformPane);

        groupsPane.setLayout(new java.awt.BorderLayout());

        addGroupBtn.setText("  Add  ");
        addGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addGroupBtn);

        removeGroupBtn.setText("Remove");
        removeGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeGroupBtnActionPerformed(evt);
            }
        });
        jPanel1.add(removeGroupBtn);

        renameGroupBtn.setText("Rename");
        renameGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameGroupBtnActionPerformed(evt);
            }
        });
        jPanel1.add(renameGroupBtn);

        viewGroupBtn.setText("  View  ");
        viewGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGroupBtnActionPerformed(evt);
            }
        });
        jPanel1.add(viewGroupBtn);

        groupsPane.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        groupList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                groupListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(groupList);

        groupsPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gsmContentTabbedPane.addTab("Groups", groupsPane);

        leftPane.setBottomComponent(gsmContentTabbedPane);

        gsmInfoPane.setLayout(new java.awt.GridBagLayout());

        metadataWrapperPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Series Information"));
        metadataWrapperPane.setMinimumSize(new java.awt.Dimension(200, 140));
        metadataWrapperPane.setPreferredSize(new java.awt.Dimension(300, 240));
        metadataWrapperPane.setLayout(new java.awt.BorderLayout(5, 5));

        metadataTable.setMinimumSize(new java.awt.Dimension(0, 150));
        metadataTable.setRowHeight(22);
        metadataScrollPane.setViewportView(metadataTable);

        metadataWrapperPane.add(metadataScrollPane, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gsmInfoPane.add(metadataWrapperPane, gridBagConstraints);

        namePane.setFont(new java.awt.Font("DejaVu Serif", 1, 18));

        nameLbl.setText("SeriesID");
        namePane.add(nameLbl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gsmInfoPane.add(namePane, gridBagConstraints);

        viewInBroswerBtn.setText("View in Browser");
        viewInBroswerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInBroswerBtnActionPerformed(evt);
            }
        });
        viewInBrowserPane.add(viewInBroswerBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gsmInfoPane.add(viewInBrowserPane, gridBagConstraints);

        leftPane.setLeftComponent(gsmInfoPane);

        contentSplitPane.setLeftComponent(leftPane);

        getContentPane().add(contentSplitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewSampleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSampleBtnActionPerformed
        int[] rows = sampleTable.getSelectedRows();
        String[] ids = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            ids[i] = (String)sampleTable.getModel().getValueAt(rows[i], 0);
        }
        SOFTDownloadTask task = new SOFTDownloadTask(ids, this, SOFT.Format.quick);
        JTaskConfig config = task.getDefaultTaskConfig();
        boolean success = TaskManager.executeTask(task, config);
    }//GEN-LAST:event_viewSampleBtnActionPerformed

    private void viewInBroswerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInBroswerBtnActionPerformed
        String url = String.format(GEOQuery.GEO_URL, soft.getId(), "html", SOFT.Format.quick);
        GEOQuery.openURL(url);
    }//GEN-LAST:event_viewInBroswerBtnActionPerformed

    private void importSampleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importSampleBtnActionPerformed
        int[] rows = sampleTable.getSelectedRows();
        String[] ids = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            ids[i] = (String)sampleTable.getModel().getValueAt(rows[i], 0);
        }
        if (ids.length > 0) {
            SOFTDownloadTask task = new SOFTDownloadTask(ids, new SOFTViewer() {

                public void viewSOFT(List<SOFT> list) {
                    GEOImportAction.viewSample(list);
                }
                
            }, SOFT.Format.full);
            TaskManager.executeTask(task, task.getDefaultTaskConfig());
        }
    }//GEN-LAST:event_importSampleBtnActionPerformed

    private void viewPlatformBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPlatformBtnActionPerformed
        int[] rows = platformTable.getSelectedRows();
        String[] ids = new String[rows.length];
        for (int i = 0; i < rows.length; i++) {
            ids[i] = (String)platformTable.getModel().getValueAt(rows[i], 0);
        }
        SOFTDownloadTask task = new SOFTDownloadTask(ids, this, SOFT.Format.quick);
        JTaskConfig config = task.getDefaultTaskConfig();
        boolean success = TaskManager.executeTask(task, config);
    }//GEN-LAST:event_viewPlatformBtnActionPerformed

    private void viewGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewGroupBtnActionPerformed
        final int index = groupList.getSelectedIndex();
        if (index < 0) return;
        Object[] keys = groupList.getSelectedValues();

        for (Object key: keys) {
            int tabIndex = sampleTabbedPane.indexOfTab((String)key);
            if (tabIndex >= 0) {
                sampleTabbedPane.setSelectedIndex(index);
            } else {
                List<SOFT> softList = groupMap.get((String)key);

                GroupPane groupPane = new GroupPane();
                groupPane.addPropertyChangeListener(new PropertyChangeListener() {

                    public void propertyChange(PropertyChangeEvent pce) {
                        if (pce.getPropertyName().equals("GroupPane_ViewStatus")) {
                            if (pce.getNewValue() instanceof Integer) {
                                if ((Integer)pce.getNewValue() == WindowEvent.WINDOW_CLOSING) {
                                    GroupPane pane = (GroupPane) pce.getSource();
                                    sampleTabbedPane.remove(pane);
                                }
                            }
                        }
                    }
                });
                groupPane.setSamples(softList);
                sampleTabbedPane.add((String)key, groupPane);
                sampleTabbedPane.setSelectedComponent(groupPane);
            }
        }
    }//GEN-LAST:event_viewGroupBtnActionPerformed

    private void sampleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sampleTableMouseClicked
        if (evt.getClickCount() == 2) {
            viewSampleBtnActionPerformed(null);
        }
    }//GEN-LAST:event_sampleTableMouseClicked

    private void platformTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_platformTableMouseClicked
        if (evt.getClickCount() == 2) {
            viewPlatformBtnActionPerformed(null);
        }
    }//GEN-LAST:event_platformTableMouseClicked

    private void groupListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_groupListMouseClicked
        if (evt.getClickCount() == 2) {
            viewGroupBtnActionPerformed(null);
        }
    }//GEN-LAST:event_groupListMouseClicked

    private void addGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupBtnActionPerformed
        String response = (String) JOptionPane.showInputDialog(this,
                "Please enter name for the new group", "Add Group",
                JOptionPane.PLAIN_MESSAGE, null, null, "");
        if (response != null && !"".equals(response)) {
            groupMap.put(response, new ArrayList<SOFT>());
            groupList.updateUI();
        }
    }//GEN-LAST:event_addGroupBtnActionPerformed

    private void removeGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeGroupBtnActionPerformed
        Object[] values = groupList.getSelectedValues();
        StringBuilder sb = new StringBuilder();
        for (Object v: values) {
            sb.append(v);
            sb.append(";");
        }
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure to remove these groups? " + sb, "Remove groups", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            for (Object v: values) {
                groupMap.remove((String)v);
                int index = sampleTabbedPane.indexOfTab((String)v);
                if (index >= 0) sampleTabbedPane.remove(index);
            }
            groupList.updateUI();
        }            
    }//GEN-LAST:event_removeGroupBtnActionPerformed

    private void renameGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameGroupBtnActionPerformed
        int row = groupList.getSelectedIndex();
        String value = (String)groupList.getSelectedValue();
        String response = (String) JOptionPane.showInputDialog(this,
                "Please enter new name for the group", "Rename Group",
                JOptionPane.PLAIN_MESSAGE, null, null, value);
        if (response != null && !"".equals(response)) {
            if (groupMap.containsKey(response)) {
                int merge = JOptionPane.showConfirmDialog(this,
                        "The new group name you entered " + response +
                        " has already exists. Shall I merge the two groups? No will cancel this operation",
                        "Name Already Exists!", JOptionPane.YES_NO_OPTION);
                if (merge == JOptionPane.YES_OPTION) {
                    List<SOFT> list = groupMap.remove(value);
                    int index = sampleTabbedPane.indexOfTab(value);
                    if (index >= 0) {
                        sampleTabbedPane.remove(index);
                    }

                    // Now merge to the existing one
                    List<SOFT> merged = groupMap.get(response);
                    index = sampleTabbedPane.indexOfTab(response);
                    GroupPane pane = null;
                    if (index >= 0) {
                        pane = (GroupPane)sampleTabbedPane.getComponentAt(index);
                    }

                    for (SOFT s: list) {
                        if (!merged.contains(s)) {
                            merged.add(s);
                        }
                    }

                    if (pane != null) {
                        pane.updateSampleTable();
                        sampleTabbedPane.setSelectedComponent(pane);
                    }
                    groupMap.put(response, merged);
                    groupList.updateUI();
                }
            } else {
                List<SOFT> list = groupMap.remove(value);
                groupMap.put(response, list);
                groupList.updateUI();

                int index = sampleTabbedPane.indexOfTab(value);
                if (index >= 0) {
                    sampleTabbedPane.setTitleAt(index, response);
                }
            }
        }
    }//GEN-LAST:event_renameGroupBtnActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SOFT gse = GEOQuery.getGSE("GSE9914", SOFT.Format.quick);

                    GSEViewerDialog dialog = new GSEViewerDialog(new javax.swing.JFrame(), true);
                    dialog.setSOFT(gse);
                    dialog.setSize(1000, 800);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception ex) {
                } 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGroupBtn;
    private javax.swing.JSplitPane contentSplitPane;
    private javax.swing.JList groupList;
    private javax.swing.JPanel groupsPane;
    private javax.swing.JTabbedPane gsmContentTabbedPane;
    private javax.swing.JPanel gsmInfoPane;
    private javax.swing.JButton importSampleBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane leftPane;
    private javax.swing.JScrollPane metadataScrollPane;
    private javax.swing.JTable metadataTable;
    private javax.swing.JPanel metadataWrapperPane;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JPanel namePane;
    private javax.swing.JPanel platformButtonPane;
    private javax.swing.JPanel platformPane;
    private javax.swing.JTable platformTable;
    private javax.swing.JButton removeGroupBtn;
    private javax.swing.JButton renameGroupBtn;
    private javax.swing.JPanel sampleButtonPane;
    private javax.swing.JPanel samplePane;
    private javax.swing.JTabbedPane sampleTabbedPane;
    private javax.swing.JTable sampleTable;
    private javax.swing.JButton viewGroupBtn;
    private javax.swing.JButton viewInBroswerBtn;
    private javax.swing.JPanel viewInBrowserPane;
    private javax.swing.JButton viewPlatformBtn;
    private javax.swing.JButton viewSampleBtn;
    // End of variables declaration//GEN-END:variables

    public void closeView(SOFT soft) {
        sampleTabbedPane.remove(sampleTabbedPane.indexOfTab(soft.getId()));
    }

    public void viewSOFT(List<SOFT> list) {
        int index = -1;
        for (SOFT soft : list) {
            index = sampleTabbedPane.indexOfTab(soft.getId());
            if (index >= 0) {
                sampleTabbedPane.setSelectedIndex(index);
            } else {
                SOFTViewerPane pane = new SOFTViewerPane();
                pane.addPropertyChangeListener(new PropertyChangeListener() {

                    public void propertyChange(PropertyChangeEvent pce) {
                        if (pce.getPropertyName().equals("SOFTViewer_ViewStatus")) {
                            if (pce.getNewValue() instanceof Integer) {
                                if ((Integer)pce.getNewValue() == WindowEvent.WINDOW_CLOSING) {
                                    sampleTabbedPane.remove((JComponent)pce.getSource());
                                }
                            }
                        }
                    }
                });
                pane.setSoft(soft);
                sampleTabbedPane.add(soft.getId(), pane);
                sampleTabbedPane.setSelectedComponent(pane);
            }
        }
    }

    /**
     * Initialize Drag and Drop
     */
    private void initDnD() {
        sampleTable.setTransferHandler(new TransferHandler() {

            @Override
            public int getSourceActions(JComponent c) {
                return COPY_OR_MOVE;
            }

            @Override
            public Transferable createTransferable(final JComponent c) {
                final GSE gse = (GSE)soft;
                return new Transferable() {

                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[]{new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType, "SOFT")};
                    }

                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return true;
                    }

                    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                        JTable table = (JTable)c;
                        List<SOFT> samples = gse.getSamples();
                        List<SOFT> list = new ArrayList<SOFT>();
                        int[] rows = table.getSelectedRows();
                        for (int row: rows) {
                            list.add(samples.get(row));
                        }
                        return list;
                    }

                };
            }
        });
    }
}
