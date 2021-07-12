package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import jade.Boot;
import jade.lang.acl.ACLMessage;

public class MainFrame {

    public JPanel panel1;
    private JPanel resultPanel;
    private JPanel messagesPanel;
    private JPanel fromPanel;
    private JTextPane textPane1;

    private JTextField tfAutres;
    private JTextField nbrEnfants7;
    private JTextField tfEnfants7;
    private JTextField tfEnfants711;
    private JTextField tfDate;
    private JTextField tfPersAgees;
    private JComboBox cbDepart;
    private JComboBox cbDestination;
    private JTextField validr;

    private JButton validerPersAgees;
    private JButton validerEnfantMoins7;
    private JButton validerEnfant711;
    private JButton validerButton5;
    private JButton toutValider;
    private JButton validerDate;
    private JButton validerAutres;
    private JButton validerDepart;
    private JButton validerDestination;

    public MainFrame() {
        validerAutres.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int nbrAutres = Integer.parseInt(tfAutres.getText());
                creatAgents(-1, -1, -1, nbrAutres, "", "", "");
            }
        });
        validerPersAgees.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nbrAgees = Integer.parseInt(tfPersAgees.getText());
                creatAgents(nbrAgees, -1, -1, -1, "", "", "");
            }
        });

        validerEnfantMoins7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nbrEnf7 = Integer.parseInt(nbrEnfants7.getText());
                creatAgents(-1, nbrEnf7, -1, -1, "", "", "");

            }
        });

        validerEnfant711.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nbrEnf711 = Integer.parseInt(tfEnfants711.getText());
                creatAgents(-1, -1, nbrEnf711, -1, "", "", "");

            }
        });
        validerDate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date date = simple.parse(tfDate.getText());

                    creatAgents(-1, -1, -1, -1, date.toString(), "", "");

                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
        validerDepart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String depart = cbDepart.getSelectedItem().toString();
                creatAgents(-1, -1, -1, -1, "", depart, "");

            }
        });
        validerDestination.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String destination = cbDestination.getSelectedItem().toString();
                creatAgents(-1, -1, -1, -1, "", "", destination);
            }
        });
        toutValider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int nbrAutres = Integer.parseInt(tfAutres.getText());
                int nbrAgees = Integer.parseInt(tfPersAgees.getText());
                int nbrEnf7 = Integer.parseInt(nbrEnfants7.getText());
                int nbrEnf711 = Integer.parseInt(tfEnfants711.getText());
                String depart = cbDepart.getSelectedItem().toString();
                String destination = cbDestination.getSelectedItem().toString();

                try {
                    SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = simple.parse(tfDate.getText());

                    creatAgents(nbrAgees, nbrEnf7, nbrEnf711, nbrAutres, date.toString(), depart, destination);

                } catch (ParseException parseException) {
                    parseException.printStackTrace();
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
        fromPanel = new JPanel();
        fromPanel.setLayout(new GridLayoutManager(7, 10, new Insets(0, 140, 0, 0), -1, -1));
        panel1.add(fromPanel, BorderLayout.EAST);
        final JLabel label1 = new JLabel();
        label1.setText("Destnation");
        fromPanel.add(label1, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        fromPanel.add(spacer1,
                new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), new Dimension(30, -1),
                        new Dimension(30, -1), 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Depart");
        fromPanel.add(label2, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        fromPanel.add(spacer2,
                new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, 10), new Dimension(20, 10),
                        new Dimension(20, 10), 0, false));
        final Spacer spacer3 = new Spacer();
        fromPanel.add(spacer3,
                new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(20, 10), new Dimension(20, 10),
                        new Dimension(20, 10), 0, false));
        cbDestination = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        cbDestination.setModel(defaultComboBoxModel1);
        fromPanel.add(cbDestination,
                new GridConstraints(1, 8, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0,
                        false));
        final Spacer spacer4 = new Spacer();
        fromPanel.add(spacer4, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("DATE");
        fromPanel.add(label3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfDate = new JTextField();
        tfDate.setText("02-08-2021");
        fromPanel.add(tfDate,
                new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                        new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("nbr enfants -7");
        fromPanel.add(label4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("nbr ages +75");
        fromPanel.add(label5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nbrEnfants7 = new JTextField();
        nbrEnfants7.setText("0");
        fromPanel.add(nbrEnfants7,
                new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                        new Dimension(150, -1), null, 0, false));
        tfPersAgees = new JTextField();
        tfPersAgees.setText("0");
        fromPanel.add(tfPersAgees,
                new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                        new Dimension(150, -1), null, 0, false));
        validerPersAgees = new JButton();
        validerPersAgees.setText("Valider");
        fromPanel.add(validerPersAgees,
                new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validerEnfantMoins7 = new JButton();
        validerEnfantMoins7.setText("Valider");
        fromPanel.add(validerEnfantMoins7,
                new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validerDate = new JButton();
        validerDate.setText(" Valider");
        fromPanel.add(validerDate,
                new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validerDepart = new JButton();
        validerDepart.setText("Valider");
        fromPanel.add(validerDepart,
                new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validerDestination = new JButton();
        validerDestination.setText("Valider");
        fromPanel.add(validerDestination,
                new GridConstraints(3, 8, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("nbr des autres passagers");
        fromPanel.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfAutres = new JTextField();
        tfAutres.setText("1");
        fromPanel.add(tfAutres,
                new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                        new Dimension(150, -1), null, 0, false));
        cbDepart = new JComboBox();
        fromPanel.add(cbDepart,
                new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0,
                        false));
        toutValider = new JButton();
        toutValider.setText("Tout valider");
        fromPanel.add(toutValider,
                new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validerEnfant711 = new JButton();
        validerEnfant711.setText("Valider");
        fromPanel.add(validerEnfant711,
                new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("nbr enfants entre 7 et 11");
        fromPanel.add(label7, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfEnfants711 = new JTextField();
        tfEnfants711.setText("0");
        fromPanel.add(tfEnfants711,
                new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null,
                        new Dimension(150, -1), null, 0, false));
        validerAutres = new JButton();
        validerAutres.setText("Valider");
        fromPanel.add(validerAutres,
                new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 300, 0, 0), -1, -1));
        panel1.add(resultPanel, BorderLayout.WEST);
        final Spacer spacer5 = new Spacer();
        resultPanel.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        resultPanel.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new GridLayoutManager(2, 3, new Insets(40, 0, 0, 0), -1, -1));
        panel1.add(messagesPanel, BorderLayout.SOUTH);
        final Spacer spacer7 = new Spacer();
        messagesPanel.add(spacer7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textPane1 = new JTextPane();
        textPane1.setText("");
        messagesPanel.add(textPane1,
                new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null,
                        new Dimension(250, 50), null, 0, false));
        final Spacer spacer8 = new Spacer();
        messagesPanel.add(spacer8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        messagesPanel.add(spacer9, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));

        cbDestination.addItem("France_aeo1");
        cbDestination.addItem("France_aeo2");
        cbDestination.addItem("Algerie_aeo3");
        cbDestination.addItem("Algerie_aeo4");
        cbDestination.addItem("Turquie_aeo7");
        cbDestination.addItem("Turquie_aeo8");
        cbDestination.addItem("Tunisie_aeo9");
        cbDestination.addItem("Tunisie_aeo10");

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
        return (JComponent) panel1;
    }

    private void createUIComponents() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1400, 400));
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
        SbAgent.append("Agence2:Traitement.Agence2");
        SbAgent.append("Agence3:Traitement.Agence3");
        SbAgent.append("Agence4:Traitement.Agence4");
        jadeArg[0] = "-gui";
        jadeArg[1] = SbAgent.toString();
        Boot.main(jadeArg);

    }
}