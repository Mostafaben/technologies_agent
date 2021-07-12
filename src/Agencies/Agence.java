package Agencies;

import java.io.BufferedReader;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Agence extends Agent {
	private static int nbAgence=4;
	public static int getnbAgence(){
		return nbAgence;
	}
	static int red_dehos_estivale ;
	static int red_enf_7 ;
	static int red_7_11 ;
	static int red_sup75 ;

	protected void  setup() {
		ACLMessage msg;
		Agence p = null;
		String ligne = "";
		ACLMessage m = null;
		BufferedReader br = null;
		int cpt = 0;

	}

}