package Agencies;

import Interface.gui;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.*;
import java.util.*;

public class Agence2 extends Agent {
    int red_dehos_estivale = 30;
    int red_enf_7 = 44;
    int red_7_11 = 20;
    int red_sup75 = 20;
    private int red_plus4 = 13;
    ACLMessage message = null;

    private ArrayList<String> listVols = new ArrayList<String>();

    protected void setup() {
        if (this.getLocalName().equals("Agence2")) {
            this.addBehaviour(new CyclicBehaviour() {
                @Override
                public void action() {
                    ACLMessage msg = null;
                    msg = this.myAgent.blockingReceive();
                    if (msg.getContent() != null) {

                        System.out.println("This is Agence1: Message received from Central Agent");
                        gui.dialogTextArea.append("This is Agence1: Message received from Central Agent\n");
                        try {
                            Scanner scanner = new Scanner(new File("./src/Agences/agence2.txt"));
                            while (scanner.hasNextLine()) {
                                listVols.add(scanner.nextLine());
                            }
                            scanner.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        System.out.println(msg.getContent());
                        String[] input = msg.getContent().split(" ");
                        ArrayList<String> volsRetournes = new ArrayList<String>();
                        if (input[4].equals("null") && input[5].equals("unspecified")
                                && input[6].equals("unspecified")) {
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                        + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                / 100
                                        + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6]) * (100 - red_enf_7)
                                                / 100
                                        + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6]) * (100 - red_7_11)
                                                / 100;

                                if (Integer.parseInt(input[0]) + Integer.parseInt(input[1]) + Integer.parseInt(input[2])
                                        + Integer.parseInt(input[3]) >= 4) {
                                    if (Integer.parseInt(input[0]) != 0) {
                                        total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                    } else if (Integer.parseInt(input[1]) != 0) {
                                        total = total
                                                - (Integer.parseInt(infoVol[6]) * (100 - red_sup75) * red_plus4 / 100);
                                    }

                                }
                                volsRetournes.add(vol + " " + total + " A2");

                            }
                        } else if (input[4].equals("null") && input[5].equals("unspecified")) {
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (input[6].equals(infoVol[2])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }

                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }

                        } else if (input[4].equals("null") && input[6].equals("unspecified")) {
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (input[5].equals(infoVol[1])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }

                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }

                        } else if (input[10].equals("unspecified") && input[11].equals("unspecified")) {
                            int day = getDay(input[4]);
                            int month = getMonth(input[5]);
                            int dayOfMonth = Integer.parseInt(input[6]);
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (day == Integer.parseInt(infoVol[3])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);

                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }
                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {
                                        total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }

                        } else if (input[10].equals("unspecified")) {
                            int day = getDay(input[4]);
                            int month = getMonth(input[5]);
                            int dayOfMonth = Integer.parseInt(input[6]);
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (day == Integer.parseInt(infoVol[3]) && input[11].equals(infoVol[2])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);

                                        }

                                    }
                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {
                                        total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }

                        } else if (input[11].equals("unspecified")) {
                            int day = getDay(input[4]);
                            int month = getMonth(input[5]);
                            int dayOfMonth = Integer.parseInt(input[6]);
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (day == Integer.parseInt(infoVol[3]) && input[10].equals(infoVol[1])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }
                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {
                                        total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }
                        } else if (input[4].equals("null")) {
                            int day = getDay(input[4]);
                            int month = getMonth(input[5]);
                            int dayOfMonth = Integer.parseInt(input[6]);
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");

                                if (input[10].equals(infoVol[1]) && input[11].equals(infoVol[2])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;

                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }
                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {
                                        total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }

                        } else {
                            int day = getDay(input[4]);
                            int month = getMonth(input[5]);
                            int dayOfMonth = Integer.parseInt(input[6]);
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (day == Integer.parseInt(infoVol[3]) && input[10].equals(infoVol[1])
                                        && input[11].equals(infoVol[2])) {
                                    float total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(input[0]) + Integer.parseInt(input[1])
                                            + Integer.parseInt(input[2]) + Integer.parseInt(input[3]) >= 4) {
                                        if (Integer.parseInt(input[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(input[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }
                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {
                                        total = Integer.parseInt(input[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(input[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }
                                    volsRetournes.add(vol + " " + total + " A2");
                                }
                            }
                        }
                        ACLMessage envoye;
                        envoye = new ACLMessage(ACLMessage.INFORM);
                        envoye.addReceiver(new AID("AgentCentral", AID.ISLOCALNAME));
                        envoye.setContent(parseIntegerToString(volsRetournes));
                        send(envoye);
                    }
                }
            });
        }
    }

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String parseIntegerToString(ArrayList<String> l) {
        String all = "";
        for (String e : l) {
            all = all + e + ":";
        }
        return all;
    }

    public static int getDay(String input) {

        int day = 0;
        System.out.println(input.substring(0, 3));

        if (input.substring(0, 3).equals("Sun")) {
            day = 1;
        } else if (input.substring(0, 3).equals("Mon")) {
            day = 2;
        } else if (input.substring(0, 3).equals("Tue")) {
            day = 3;
        } else if (input.substring(0, 3).equals("Wed")) {
            day = 4;
        } else if (input.substring(0, 3).equals("Thu")) {
            day = 5;
        } else if (input.substring(0, 3).equals("Fri")) {
            day = 6;
        } else if (input.substring(0, 3).equals("Sat")) {
            day = 7;
        }
        return day;
    }

    public static int getMonth(String input) {
        int month = 0;
        if (input.equals("Jan")) {
            month = 1;
        } else if (input.equals("Feb")) {
            month = 2;
        } else if (input.equals("Mar")) {
            month = 3;
        } else if (input.equals("Avr")) {
            month = 4;
        } else if (input.equals("May")) {
            month = 5;
        } else if (input.equals("Jun")) {
            month = 6;
        } else if (input.equals("Jul")) {
            month = 7;
        } else if (input.equals("Aug")) {
            month = 8;
        } else if (input.equals("Sep")) {
            month = 9;
        } else if (input.equals("Oct")) {
            month = 10;
        } else if (input.equals("Nov")) {
            month = 11;
        } else if (input.equals("Dec")) {
            month = 12;
        }

        return month;
    }
}