/*
 * settingDialog.java
 *
 * Created on 2007-10-10, 10:51 PM
teedict , to be the best dictionary application for java me enabled devices.
Copyright (C) 2006,2007  Yong Li. All rights reserved.
 
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.  
 */

package com.teesoft.javadict;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author  wind
 */
public class settingDialog extends javax.swing.JDialog {
    private j2seConfigManager configManager;
    
    /** Creates new form settingDialog */
    public settingDialog(java.awt.Frame parent, boolean modal,j2seConfigManager configManager) {
        super(parent, modal);
        initComponents();
        this.configManager = configManager;
        
        loadConfig();
        centerScreen();
        this.setVisible(true);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        chkMinimize = new javax.swing.JCheckBox();
        chkScan = new javax.swing.JCheckBox();
        chkMonitoringClipboard = new javax.swing.JCheckBox();
        chkKeyShift = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        chkKeyCtrl = new javax.swing.JCheckBox();
        chkKeyAlt = new javax.swing.JCheckBox();
        chkAutosearch = new javax.swing.JCheckBox();
        txtMilliSecond = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        chkAutoPlaySound = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtScanMilliSecond = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/teesoft/javadict/resources/JavaDict"); // NOI18N
        chkMinimize.setText(bundle.getString("settingDialog.chkMinimize.text")); // NOI18N

        chkScan.setText(bundle.getString("settingDialog.chkScan.text")); // NOI18N
        chkScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkScanActionPerformed(evt);
            }
        });

        chkMonitoringClipboard.setText(bundle.getString("settingDialog.chkMonitoringClipboard.text")); // NOI18N

        chkKeyShift.setSelected(true);
        chkKeyShift.setText(bundle.getString("settingDialog.chkKeyShift.text")); // NOI18N

        jLabel1.setText(bundle.getString("settingDialog.jLabel1.text")); // NOI18N

        chkKeyCtrl.setText(bundle.getString("settingDialog.chkKeyCtrl.text")); // NOI18N

        chkKeyAlt.setText(bundle.getString("settingDialog.chkKeyAlt.text")); // NOI18N

        chkAutosearch.setText(bundle.getString("settingDialog.chkAutosearch.text")); // NOI18N

        txtMilliSecond.setText(bundle.getString("settingDialog.txtMilliSecond.text")); // NOI18N
        txtMilliSecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMilliSecondActionPerformed(evt);
            }
        });

        jLabel2.setText(bundle.getString("settingDialog.jLabel2.text")); // NOI18N

        chkAutoPlaySound.setText(bundle.getString("settingDialog.chkAutoPlaySound.text")); // NOI18N

        jLabel3.setText(bundle.getString("settingDialog.jLabel3.text")); // NOI18N

        txtScanMilliSecond.setText(bundle.getString("settingDialog.txtScanMilliSecond.text")); // NOI18N
        txtScanMilliSecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtScanMilliSecondActionPerformed(evt);
            }
        });

        jLabel4.setText(bundle.getString("settingDialog.jLabel4.text")); // NOI18N

        btnOK.setText(bundle.getString("settingDialog.btnOK.text")); // NOI18N
        btnOK.setActionCommand(bundle.getString("settingDialog.btnOK.actionCommand")); // NOI18N
        buttonGroup1.add(btnOK);
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText(bundle.getString("settingDialog.btnCancel.text")); // NOI18N
        buttonGroup1.add(btnCancel);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnApply.setText(bundle.getString("settingDialog.btnApply.text")); // NOI18N
        buttonGroup1.add(btnApply);
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(chkMinimize)
                    .add(chkMonitoringClipboard)
                    .add(layout.createSequentialGroup()
                        .add(chkAutosearch)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtMilliSecond, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2))
                    .add(chkAutoPlaySound)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(chkScan)
                                .add(18, 18, 18)
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtScanMilliSecond))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(12, 12, 12)
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(chkKeyShift)
                                .add(6, 6, 6)
                                .add(chkKeyCtrl)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(chkKeyAlt)))
                        .add(1, 1, 1)
                        .add(jLabel4)))
                .addContainerGap(144, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .add(btnOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnApply, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnCancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(chkMinimize)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chkAutosearch)
                    .add(txtMilliSecond, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chkScan)
                    .add(jLabel3)
                    .add(txtScanMilliSecond, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(chkKeyShift)
                    .add(chkKeyCtrl)
                    .add(chkKeyAlt))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chkMonitoringClipboard)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chkAutoPlaySound)
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnOK)
                    .add(btnApply)
                    .add(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkScanActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_chkScanActionPerformed

    private void loadConfig() {
        this.chkMinimize.setSelected(configManager.getStartMinimize());
        this.chkAutosearch.setSelected(configManager.getAutoSearch());
        this.txtMilliSecond.setText( String.valueOf(configManager.getDelay()));
        
        this.chkScan.setSelected(configManager.getScan());
        this.txtScanMilliSecond.setText( String.valueOf(configManager.getScanDelay()));
        int scanKey = configManager.getScanKey();
        this.chkKeyShift.setSelected( (scanKey & com.teesoft.screentextj.ScreenTextMonitor.keyShift) ==  com.teesoft.screentextj.ScreenTextMonitor.keyShift);
        this.chkKeyCtrl.setSelected( (scanKey & com.teesoft.screentextj.ScreenTextMonitor.keyCtrl) ==  com.teesoft.screentextj.ScreenTextMonitor.keyCtrl);
        this.chkKeyAlt.setSelected( (scanKey & com.teesoft.screentextj.ScreenTextMonitor.keyAlt) ==  com.teesoft.screentextj.ScreenTextMonitor.keyAlt);

        this.chkMonitoringClipboard.setSelected(configManager.getMonitoringClipboard());
        this.chkAutoPlaySound.setSelected(configManager.getPlaySoundDirectly());
    }
    private int getIntValue(String value,int defaultValue)
    {
        try{
            int intValue = Integer.parseInt(value);
            return intValue;
        }catch(NumberFormatException ex)
        {
            return defaultValue;
        }
    }

    private void saveConfig() {
        configManager.setStartMinimize(this.chkMinimize.isSelected());
        configManager.setAutoSearch(this.chkAutosearch.isSelected());
        configManager.setDelay(getIntValue(this.txtMilliSecond.getText(),300));
        
        configManager.setScan(this.chkScan.isSelected());
        configManager.setScanDelay(getIntValue(this.txtScanMilliSecond.getText(),500));
        
        int scanKey =0;
        if (this.chkKeyShift.isSelected())
            scanKey += com.teesoft.screentextj.ScreenTextMonitor.keyShift;
        if (this.chkKeyCtrl.isSelected())
            scanKey += com.teesoft.screentextj.ScreenTextMonitor.keyCtrl;
        if (this.chkKeyAlt.isSelected())
            scanKey += com.teesoft.screentextj.ScreenTextMonitor.keyAlt;
        configManager.setScanKey(scanKey);
        
        configManager.setMonitoringClipboard(this.chkMonitoringClipboard.isSelected());
        configManager.setPlaySoundDirectly(this.chkAutoPlaySound.isSelected());
        
    }
    private void txtMilliSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMilliSecondActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtMilliSecondActionPerformed

    private void txtScanMilliSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScanMilliSecondActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtScanMilliSecondActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        saveConfig();
        configManager.saveConfig();
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        saveConfig();
        configManager.saveConfig();
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        settingDialog dialog = new settingDialog(new javax.swing.JFrame(), true, j2seConfigManager.getJ2seInstance());
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        dialog.setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkAutoPlaySound;
    private javax.swing.JCheckBox chkAutosearch;
    private javax.swing.JCheckBox chkKeyAlt;
    private javax.swing.JCheckBox chkKeyCtrl;
    private javax.swing.JCheckBox chkKeyShift;
    private javax.swing.JCheckBox chkMinimize;
    private javax.swing.JCheckBox chkMonitoringClipboard;
    private javax.swing.JCheckBox chkScan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtMilliSecond;
    private javax.swing.JTextField txtScanMilliSecond;
    // End of variables declaration//GEN-END:variables
    
    public void centerScreen() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
        //requestFocus();
    }
}
