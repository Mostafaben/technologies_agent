package com.company;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class CentralAgent extends Agent {

    protected void setup() {
        if (this.getLocalName().equals("CentralAgent")) {
            this.addBehaviour(new OneShotBehaviour() {
                @Override
                public void action() {

                    Object[] T = this.myAgent.getArguments();
                    String a = (String) T[0];
                    System.out.println("This is cenral agent: A1.");
                    System.out.println("L'argument passe' est");
                    System.out.println(a);
                    ACLMessage envoye;
                    envoye = new ACLMessage(ACLMessage.INFORM);
                    envoye.addReceiver(new AID("Agence1", AID.ISLOCALNAME));
                    envoye.setContent(String.valueOf(a));
                    send(envoye);
                    System.out.print("A1: message envoye a A2");

                }
            });
        }

    }

}
