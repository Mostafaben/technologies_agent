package Agencies;

import Interface.gui;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Agence4 extends Agent {
    int red_dehos_estivale = 30;
    int red_enf_7 = 20;
    int red_7_11 = 30;
    int red_sup75 = 20;
    private int red_plus4 = 20;
    ACLMessage msg = null;

    private ArrayList<String> listVols = new ArrayList<String>();

    protected void setup() {
        if (this.getLocalName().equals("Agence4")) {
            this.addBehaviour(new CyclicBehaviour() {
                @Override
                public void action() {
                    ACLMessage message = null;
                    message = this.myAgent.blockingReceive();
                    if (message.getContent() != null) {
                        System.out.println("This is Agence1: Message received from Central Agent");
                        gui.dialogTextArea.append("This is Agence1: Message received from Central Agent\n");
                        try {
                            Scanner scanner = new Scanner(new File("./src/Agences/agence4.txt"));
                            while (scanner.hasNextLine()) {
                                System.out.print(scanner.nextLine());
                                listVols.add(scanner.nextLine());
                            }
                            scanner.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.out.println(message.getContent());
                        String[] UserInput = message.getContent().split(" ");
                        ArrayList<String> filteredFlights = new ArrayList<String>();
                        if (UserInput[4].equals("null") && UserInput[5].equals("unspecified")
                                && UserInput[6].equals("unspecified")) {
                            for (String flight : listVols) {
                                String[] flightInformations = flight.split(" ");
                                float totalPrice = CalculateTotalPrice(UserInput, flightInformations);
                                if (checkNumberOfReservations(UserInput)) {
                                    if (Integer.parseInt(UserInput[0]) != 0) {
                                        totalPrice = applyFourTicketesReduction(totalPrice, flightInformations);
                                    } else if (Integer.parseInt(UserInput[1]) != 0) {
                                        totalPrice = applyReduction(totalPrice, flightInformations);
                                    }
                                }
                                filteredFlights.add(flight + " " + totalPrice + " A4");
                            }
                        } else if (UserInput[4].equals("null") && UserInput[5].equals("unspecified")) {
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (UserInput[6].equals(infoVol[2])) {
                                    float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(UserInput[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }
                                    }
                                    filteredFlights.add(vol + " " + total + " A4");
                                }
                            }
                        } else if (UserInput[4].equals("null") && UserInput[6].equals("unspecified")) {
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (UserInput[5].equals(infoVol[1])) {
                                    float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;

                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);

                                        } else if (Integer.parseInt(UserInput[1]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);

                                        }

                                    }

                                    filteredFlights.add(vol + " " + total + " A4");
                                }
                            }

                        } else if (UserInput[10].equals("unspecified") && UserInput[11].equals("unspecified")) {
                            int day = getDay(UserInput[4]);
                            int month = detMonth(UserInput[5]);
                            int dayOfMonth = Integer.parseInt(UserInput[6]);
                            for (String vol : listVols) {
                                String[] infoVol = vol.split(" ");
                                if (day == Integer.parseInt(infoVol[3])) {
                                    float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(UserInput[1]) != 0) {
                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }
                                    }
                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {
                                        total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }
                                    filteredFlights.add(vol + " " + total + " A4");
                                }
                            }

                        } else if (UserInput[10].equals("unspecified")) {

                            int day = getDay(UserInput[4]);
                            int month = detMonth(UserInput[5]);
                            int dayOfMonth = Integer.parseInt(UserInput[6]);

                            for (String vol : listVols) {

                                String[] infoVol = vol.split(" ");
                                if (day == Integer.parseInt(infoVol[3]) && UserInput[11].equals(infoVol[2])) {
                                    float totalPrice = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;
                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {
                                            totalPrice = totalPrice - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
                                        } else if (Integer.parseInt(UserInput[1]) != 0) {
                                            totalPrice = totalPrice - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);
                                        }

                                    }

                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {

                                        totalPrice = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    filteredFlights.add(vol + " " + totalPrice + " A4");
                                }
                            }

                        } else if (UserInput[11].equals("unspecified")) {

                            int day = getDay(UserInput[4]);
                            int month = detMonth(UserInput[5]);
                            int dayOfMonth = Integer.parseInt(UserInput[6]);

                            for (String vol : listVols) {

                                String[] infoVol = vol.split(" ");

                                if (day == Integer.parseInt(infoVol[3]) && UserInput[10].equals(infoVol[1])) {

                                    float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;

                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);

                                        } else if (Integer.parseInt(UserInput[1]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);

                                        }

                                    }

                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {

                                        total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    filteredFlights.add(vol + " " + total + " A4");
                                }
                            }

                            /*
                             * for (String vol : volsRetournes) { System.out.println(vol); }
                             * 
                             */

                        } else if (UserInput[4].equals("null")) {

                            int day = getDay(UserInput[4]);
                            int month = detMonth(UserInput[5]);
                            int dayOfMonth = Integer.parseInt(UserInput[6]);

                            for (String vol : listVols) {

                                String[] infoVol = vol.split(" ");

                                if (UserInput[10].equals(infoVol[1]) && UserInput[11].equals(infoVol[2])) {

                                    float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;

                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);

                                        } else if (Integer.parseInt(UserInput[1]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);

                                        }

                                    }

                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {

                                        total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    filteredFlights.add(vol + " " + total + " A4");
                                }
                            }

                        } else {

                            int day = getDay(UserInput[4]);

                            int month = detMonth(UserInput[5]);
                            int dayOfMonth = Integer.parseInt(UserInput[6]);

                            for (String vol : listVols) {

                                String[] infoVol = vol.split(" ");

                                if (day == Integer.parseInt(infoVol[3]) && UserInput[10].equals(infoVol[1])
                                        && UserInput[11].equals(infoVol[2])) {

                                    float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                            + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_sup75) / 100
                                            + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_enf_7) / 100
                                            + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                    * (100 - red_7_11) / 100;

                                    if (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1])
                                            + Integer.parseInt(UserInput[2]) + Integer.parseInt(UserInput[3]) >= 4) {
                                        if (Integer.parseInt(UserInput[0]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);

                                        } else if (Integer.parseInt(UserInput[1]) != 0) {

                                            total = total - (Integer.parseInt(infoVol[6]) * (100 - red_sup75)
                                                    * red_plus4 / 100);

                                        }

                                    }

                                    if ((month != 8 && month != 7) || (month == 7 && dayOfMonth < 15)) {

                                        total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                                                * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_sup75) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_enf_7) / 100 * (100 - red_dehos_estivale) / 100
                                                + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6])
                                                        * (100 - red_7_11) / 100 * (100 - red_dehos_estivale) / 100;
                                    }

                                    filteredFlights.add(vol + " " + total + " A4");
                                }
                            }

                        }
                        ACLMessage envoye;
                        envoye = new ACLMessage(ACLMessage.INFORM);
                        envoye.addReceiver(new AID("AgentCentral", AID.ISLOCALNAME));
                        envoye.setContent(convertIntToString(filteredFlights));
                        send(envoye);
                    }
                }
            });
        }
    }

    public float applyReduction(float price, String[] infoVol) {
        return price - (Integer.parseInt(infoVol[6]) * (100 - red_sup75) * red_plus4 / 100);
    }

    public float applyFourTicketesReduction(float price, String[] infoVol) {
        return price - (Integer.parseInt(infoVol[6]) * red_plus4 / 100);
    }

    public Boolean checkNumberOfReservations(String[] UserInput) {
        return (Integer.parseInt(UserInput[0]) + Integer.parseInt(UserInput[1]) + Integer.parseInt(UserInput[2])
                + Integer.parseInt(UserInput[3]) >= 4);
    }

    public float CalculateTotalPrice(String[] UserInput, String[] infoVol) {
        float total = Integer.parseInt(UserInput[3]) * Integer.parseInt(infoVol[6])
                + Integer.parseInt(UserInput[0]) * Integer.parseInt(infoVol[6]) * (100 - red_sup75) / 100
                + Integer.parseInt(UserInput[1]) * Integer.parseInt(infoVol[6]) * (100 - red_enf_7) / 100
                + Integer.parseInt(UserInput[2]) * Integer.parseInt(infoVol[6]) * (100 - red_7_11) / 100;
        return total;
    }

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String convertIntToString(ArrayList<String> l) {
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

    public static int detMonth(String input) {
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