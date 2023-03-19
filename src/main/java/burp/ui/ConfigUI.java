/*
 * Created by JFormDesigner on Sat Mar 18 02:09:30 CST 2023
 */

package burp.ui;

import burp.common.Base;
import burp.common.Constant;
import burp.vendor.CheckAKSK;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author Administrator
 */
public class ConfigUI extends JPanel {
    public ConfigUI() {
        initComponents();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = jFileChooser.showOpenDialog(null);
        if (i == JFileChooser.FILES_ONLY){
            File selectedFile = jFileChooser.getSelectedFile();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    CheckAKSK.customDict.add(line);
                }
                list1.setListData(CheckAKSK.customDict.toArray());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void button3(ActionEvent e) {
        // TODO add your code here
        list1.setListData(new Object[0]);
        CheckAKSK.customDict.clear();
    }

    private void checkBox1(ActionEvent e) {
        // TODO add your code here
        if (checkBox1.isSelected()){
            Constant.putAcl = checkBox1.isSelected();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("ConfigUI");
        label1 = new JLabel();
        checkBox1 = new JCheckBox();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        button1 = new JButton();
        button3 = new JButton();

        //======== this ========
        setLayout(null);

        //---- label1 ----
        label1.setText(bundle.getString("label1.text"));
        add(label1);
        label1.setBounds(new Rectangle(new Point(225, 15), label1.getPreferredSize()));

        //---- checkBox1 ----
        checkBox1.setText(bundle.getString("checkBox1.text"));
        checkBox1.addActionListener(e -> checkBox1(e));
        add(checkBox1);
        checkBox1.setBounds(new Rectangle(new Point(5, 25), checkBox1.getPreferredSize()));

        //---- label2 ----
        label2.setText(bundle.getString("label2.text"));
        add(label2);
        label2.setBounds(new Rectangle(new Point(10, 45), label2.getPreferredSize()));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(10, 80, 160, 205);

        //---- button1 ----
        button1.setText(bundle.getString("button1.text"));
        button1.addActionListener(e -> button1(e));
        add(button1);
        button1.setBounds(new Rectangle(new Point(175, 225), button1.getPreferredSize()));

        //---- button3 ----
        button3.setText(bundle.getString("button3.text"));
        button3.addActionListener(e -> button3(e));
        add(button3);
        button3.setBounds(175, 255, 112, 30);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JCheckBox checkBox1;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JList list1;
    private JButton button1;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
