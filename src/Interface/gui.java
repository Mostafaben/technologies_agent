package Interface;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import jade.Boot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class gui {
    private JPanel panel1;
    private JPanel paramsPan;
    private JPanel dialogPan;
    private JPanel resultsPan;
    private JTextField kids7TextField;
    private JTextField kids711TextField;
    private JComboBox cbDepart;
    private JComboBox cbDestination;
    private JTextField dateTextField;
    private JTextField otherTextField;
    private JTextField over70textField;
    private JButton validateButton;
    private JLabel labelDepart;
    private JLabel labelDestination;
    private JLabel labelKids7;
    private JLabel labelKids711;
    private JLabel labelOver70;
    private JLabel labelOthers;
    private JLabel labelDate;
    public static JTable table;
    public static JTextArea dialogTextArea;
    private JButton choiceValBtn;

    public gui() {

        validateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nbrAutres = Integer.parseInt(otherTextField.getText());
                int nbrAgees = Integer.parseInt(over70textField.getText());
                int nbrEnf7 = Integer.parseInt(kids7TextField.getText());
                int nbrEnf711 = Integer.parseInt(kids711TextField.getText());
                String depart = cbDepart.getSelectedItem().toString();
                String destination = cbDestination.getSelectedItem().toString();
                try {
                    if (!dateTextField.getText().equals("")) {
                        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = simple.parse(dateTextField.getText());

                        creatAgents(nbrAgees, nbrEnf7, nbrEnf711, nbrAutres, date.toString(), depart, destination);
                    } else {
                        creatAgents(nbrAgees, nbrEnf7, nbrEnf711, nbrAutres, "null", depart, destination);
                    }
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
        choiceValBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = table.getSelectedRow();
                String agence = table.getModel().getValueAt(row, 0).toString();
                String depart = table.getModel().getValueAt(row, 1).toString();
                String dest = table.getModel().getValueAt(row, 2).toString();
                String date = table.getModel().getValueAt(row, 3).toString();
                String time = table.getModel().getValueAt(row, 4).toString();
                String total = table.getModel().getValueAt(row, 5).toString();

                gui.dialogTextArea.append("\n\nSELECTED FLIGHT IS:\n");
                gui.dialogTextArea
                        .append("" + agence + " " + depart + " " + dest + " " + date + " " + time + " " + total + "\n");

                String path = "";
                if (agence.equals("A1")) {
                    path = "./src/Agences/agence1.txt";
                } else if (agence.equals("A2")) {
                    path = "./src/Agences/agence2.txt";
                } else if (agence.equals("A3")) {
                    path = "./src/Agences/agence3.txt";
                } else if (agence.equals("A4")) {
                    path = "./src/Agences/agence4.txt";
                }

                ArrayList<String> listVols = new ArrayList<String>();

                try {
                    Scanner scanner = new Scanner(new File("./src/Agences/agence1.txt"));
                    while (scanner.hasNextLine()) {

                        String line = scanner.nextLine();
                        String[] details = line.split(" ");
                        if (details[1].equals(depart) && details[2].equals(dest) && details[3].equals(date)
                                && details[4].equals(time)) {

                            System.out.println("FOUND INSTANCE");
                            int nbPlaces = Integer.parseInt(details[5]) - 1;
                            listVols.add("" + details[0] + " " + details[1] + " " + details[2] + " " + details[3] + " "
                                    + details[4] + " " + nbPlaces + " " + details[6]);
                        } else {

                            listVols.add(line);
                        }
                    }
                    scanner.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                File fout = new File(path);
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(fout);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                for (String vol : listVols) {
                    try {
                        bw.write(vol);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        bw.newLine();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                try {
                    bw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
    }

    {
        $$$setupUI$$$();
    }

    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        paramsPan = new JPanel();
        paramsPan.setLayout(new GridBagLayout());
        panel1.add(paramsPan, BorderLayout.EAST);
        final JPanel spacer1 = new JPanel();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(spacer1, gbc);
        kids7TextField = new JTextField();
        kids7TextField.setText("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(kids7TextField, gbc);
        kids711TextField = new JTextField("");
        kids711TextField.setText("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(kids711TextField, gbc);
        cbDepart = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(cbDepart, gbc);
        cbDestination = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(cbDestination, gbc);
        labelDepart = new JLabel();
        labelDepart.setText("Depart");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelDepart, gbc);
        labelDestination = new JLabel();
        labelDestination.setText("Destination");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelDestination, gbc);
        labelOver70 = new JLabel();
        labelOver70.setText("Over 75");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelOver70, gbc);
        over70textField = new JTextField("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(over70textField, gbc);
        dateTextField = new JTextField("04-08-2021");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(dateTextField, gbc);
        labelDate = new JLabel();
        labelDate.setText("Date");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelDate, gbc);
        validateButton = new JButton();
        validateButton.setText("Validate");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(validateButton, gbc);
        otherTextField = new JTextField("1");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(otherTextField, gbc);
        labelOthers = new JLabel();
        labelOthers.setText("Others");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelOthers, gbc);
        labelKids7 = new JLabel();
        labelKids7.setText("kids under 7");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelKids7, gbc);
        labelKids711 = new JLabel();
        labelKids711.setText("Kids 7/11");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        paramsPan.add(labelKids711, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        paramsPan.add(spacer3, gbc);
        dialogPan = new JPanel();
        dialogPan.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(dialogPan, BorderLayout.SOUTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        dialogPan.add(scrollPane1,
                new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null,
                        0, false));
        dialogTextArea = new JTextArea("");
        dialogTextArea.setEditable(false);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setRows(12);
        dialogTextArea.setText("........................................................................."
                + "................................................................"
                + "....................................................\n");
        dialogTextArea.append("INTERACTION DISPLAYER\n");
        dialogTextArea.append("........................................................................."
                + "................................................................"
                + "....................................................\n");
        scrollPane1.setViewportView(dialogTextArea);
        resultsPan = new JPanel();
        resultsPan.setLayout(new BorderLayout(0, 0));
        panel1.add(resultsPan, BorderLayout.CENTER);

        String data[][] = { { "A1", "Jai1", "jai2", "04/04/21", "13h", "10023" } };
        String column[] = { "Comp", "Dep", "Dest", "Date", "Time", "Total" };

        table = new JTable(new DefaultTableModel(new Object[] { "Comp", "Dep", "Dest", "Date", "Time", "Total" }, 30));
        // table = new JTable(data,column);
        table.setAutoCreateRowSorter(true);
        table.setShowHorizontalLines(true);
        JScrollPane scrollPane = new JScrollPane(table);
        resultsPan.add(scrollPane, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        resultsPan.add(panel2, BorderLayout.SOUTH);
        choiceValBtn = new JButton();
        choiceValBtn.setText("Validate Choice");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(choiceValBtn, gbc);
        cbDestination.addItem("unspecified");
        cbDestination.addItem("France_aeo1");
        cbDestination.addItem("France_aeo2");
        cbDestination.addItem("Algerie_aeo3");
        cbDestination.addItem("Algerie_aeo4");
        cbDestination.addItem("Turquie_aeo7");
        cbDestination.addItem("Turquie_aeo8");
        cbDestination.addItem("Tunisie_aeo9");
        cbDestination.addItem("Tunisie_aeo10");
        cbDepart.addItem("unspecified");
        cbDepart.addItem("France_aeo1");
        cbDepart.addItem("France_aeo2");
        cbDepart.addItem("Algerie_aeo3");
        cbDepart.addItem("Algerie_aeo4");
        cbDepart.addItem("Turquie_aeo7");
        cbDepart.addItem("Turquie_aeo8");
        cbDepart.addItem("Tunisie_aeo9");
        cbDepart.addItem("Tunisie_aeo10");
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("gui");
        frame.setContentPane(new gui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(750, 600));
        frame.setResizable(false);
        frame.pack();
        frame.show();
        frame.setVisible(true);
    }

    public void creatAgents(int p_agees, int enfnats7, int enfants711, int nbrAutres, String date, String depat,
            String Destiation) {
        String[] jadeArg = new String[2];
        StringBuffer SbAgent = new StringBuffer();
        SbAgent.append("AgentCentral:Traitement.AgentCentral(" + p_agees + "," + enfnats7 + "," + enfants711 + ","
                + nbrAutres + "," + date + "," + depat + "," + Destiation + ");");
        SbAgent.append("Agence1:Traitement.Agence1;");
        SbAgent.append("Agence2:Traitement.Agence2;");
        SbAgent.append("Agence3:Traitement.Agence3;");
        SbAgent.append("Agence4:Traitement.Agence4;");
        jadeArg[0] = "-gui";
        jadeArg[1] = SbAgent.toString();
        Boot.main(jadeArg);

    }
}
