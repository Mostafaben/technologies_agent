package Agencies;

import Interface.gui;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AgentCentral extends Agent {

    private ACLMessage envoye;
    private int red_dehos_estivale = 20;
    private int red_enf_7 = 30;
    private int red_7_11 = 30;
    private int red_sup75 = 30;
    private ACLMessage msg = null;

    protected void setup() {

        if (this.getLocalName().equals("AgentCentral")) {
            this.addBehaviour(new OneShotBehaviour() {
                @Override
                public void action() {

                    Object[] T = this.myAgent.getArguments();
                    String p_agees = (String) (T[0]);
                    String enfants7 = (String) (T[1]);
                    String enfants711 = (String) (T[2]);
                    String nbrAutres = (String) (T[3]);
                    String date = (String) (T[4]);
                    String depat = (String) (T[5]);
                    String destiation = (String) (T[6]);

                    gui.dialogTextArea.append("This is cenral agent \n");
                    gui.dialogTextArea.append("Passed Arguments are: \n");
                    gui.dialogTextArea.append("Depart            : " + depat + "\n");
                    gui.dialogTextArea.append("Destination       : " + destiation + "\n");
                    gui.dialogTextArea.append("Date              : " + date + "\n");
                    gui.dialogTextArea.append("Kids 7 _ 11 yo    : " + enfants711 + "\n");
                    gui.dialogTextArea.append("Kids under 7yo    : " + enfants7 + "\n");
                    gui.dialogTextArea.append("Other adults      : " + nbrAutres + "\n\n");

                    ACLMessage envoye;
                    envoye = new ACLMessage(ACLMessage.INFORM);
                    envoye.addReceiver(new AID("Agence1", AID.ISLOCALNAME));
                    envoye.addReceiver(new AID("Agence2", AID.ISLOCALNAME));
                    envoye.addReceiver(new AID("Agence3", AID.ISLOCALNAME));
                    envoye.addReceiver(new AID("Agence4", AID.ISLOCALNAME));
                    envoye.setContent(p_agees + " " + enfants711 + " " + enfants7 + " " + nbrAutres + " " + date + " "
                            + depat + " " + destiation);
                    send(envoye);
                    gui.dialogTextArea.append("Central Agent: message sent to annex agents \n\n");

                    MessageTemplate mt1 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                            MessageTemplate.MatchSender(new AID("Agence1", AID.ISLOCALNAME)));

                    MessageTemplate mt2 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                            MessageTemplate.MatchSender(new AID("Agence2", AID.ISLOCALNAME)));

                    MessageTemplate mt3 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                            MessageTemplate.MatchSender(new AID("Agence3", AID.ISLOCALNAME)));

                    MessageTemplate mt4 = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                            MessageTemplate.MatchSender(new AID("Agence4", AID.ISLOCALNAME)));

                    ACLMessage msg1 = receive(mt1);
                    while (msg1 == null) {
                        msg1 = receive(mt1);
                    }
                    if (msg1 != null) {
                        gui.dialogTextArea.append("\nThis is Central Agent: Anwsers received from A1 \n");
                        System.out.println(msg1.getContent());
                    }

                    ACLMessage msg2 = receive(mt2);
                    while (msg2 == null) {
                        msg2 = receive(mt2);
                    }
                    if (msg2 != null) {
                        gui.dialogTextArea.append("This is Central Agent: Anwsers received from A2 \n");

                        System.out.println(msg2.getContent());
                    }

                    ACLMessage msg3 = receive(mt3);
                    while (msg3 == null) {
                        msg3 = receive(mt3);
                    }
                    if (msg3 != null) {
                        gui.dialogTextArea.append("This is Central Agent: Anwsers received from A3 \n");
                        System.out.println(msg3.getContent());
                    }

                    ACLMessage msg4 = receive(mt4);
                    while (msg4 == null) {
                        msg4 = receive(mt4);
                    }
                    if (msg4 != null) {
                        gui.dialogTextArea.append("This is Central Agent: Anwsers received from A4 \n");
                        System.out.println(msg4.getContent());
                    }

                    ArrayList<ACLMessage> msgs = new ArrayList<ACLMessage>();
                    msgs.add(msg1);
                    msgs.add(msg2);
                    msgs.add(msg3);
                    msgs.add(msg4);

                    ArrayList<String> vectVols = new ArrayList<String>();

                    DefaultTableModel dm = (DefaultTableModel) gui.table.getModel();
                    dm.getDataVector().removeAllElements();
                    dm.fireTableDataChanged();
                    for (ACLMessage rep : msgs) {
                        String msg = rep.getContent();
                        String[] vols = msg.split(":");

                        for (String vol : vols) {
                            System.out.println(vol);
                            vectVols.add(vol);
                            String[] details = vol.split(" ");
                            System.out.println(details[7]);
                            dm.addRow(new Object[] { details[8], details[1], details[2], details[3], details[4],
                                    details[7] });
                        }
                    }
                }
            });
        }

    }

}
