package logic;

import utility.*;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static costants.costants.*;

public class ReadXML {
    public static void letturaCarteXML(String filename, ArrayList<? super Carta> mazzo) {
        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        boolean arma = false;
        boolean carta = false;
        boolean equipaggiabile = false;
        String nome = "";
        String descrizione = "";
        String distanza = "";
        String copie = "";
        String valore = "";
        String seme = "";
        ArrayList<CoppiaValoreSeme> valoreSeme = new ArrayList<>();
        TipoEquipaggiabile tipoEquipaggiabile = null;
        TipoGiocaEScarta tipoGiocaEScarta = null;


        try (FileInputStream reader = new FileInputStream(filename)) {

            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, reader);

            try {
                while (xmlr.hasNext()) {
                    switch (xmlr.getEventType()) {
                        case XMLStreamConstants.START_ELEMENT:
                            if (arma) {
                                switch (xmlr.getLocalName()) {
                                    case "nome":
                                        nome = xmlr.getElementText();
                                        break;
                                    case "distanza":
                                        distanza = xmlr.getElementText();
                                        break;
                                    case "copie":
                                        copie = xmlr.getAttributeValue(0);
                                        break;
                                    case "valore":
                                        valore = xmlr.getElementText();
                                        break;
                                    case "seme":
                                        seme = xmlr.getElementText();
                                        valoreSeme.add(new CoppiaValoreSeme(seme, valore));
                                        break;
                                    default:
                                        break;
                                }
                            }
                            else if (carta) {
                                switch (xmlr.getLocalName()) {
                                    case "nome":
                                        nome = xmlr.getElementText();
                                        if (equipaggiabile) {
                                            switch (nome) {
                                                case "Barile":
                                                    tipoEquipaggiabile = TipoEquipaggiabile.Barile;
                                                    break;
                                                case "Mirino":
                                                    tipoEquipaggiabile = TipoEquipaggiabile.Mirino;
                                                    break;
                                                case "Mustang":
                                                    tipoEquipaggiabile = TipoEquipaggiabile.Mustang;
                                                default:
                                                    break;
                                            }
                                        }
                                        else {
                                            switch (nome) {
                                                case "Bang!":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Bang;
                                                    break;
                                                case "Birra":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Birra;
                                                    break;
                                                case "Cat Balou":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Cat_Balou;
                                                    break;
                                                case "Diligenza":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Diligenza;
                                                    break;
                                                case "Gatling":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Gatling;
                                                    break;
                                                case "Mancato!":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Mancato;
                                                    break;
                                                case "Panico!":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Panico;
                                                    break;
                                                case "Saloon":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Saloon;
                                                    break;
                                                case "Wells Fargo":
                                                    tipoGiocaEScarta = TipoGiocaEScarta.Wells_Fargo;
                                                    break;
                                            }
                                        }
                                        break;
                                    case "descrizione":
                                        descrizione = xmlr.getElementText();
                                        break;
                                    case "copie":
                                        copie = xmlr.getAttributeValue(0);
                                        break;
                                    case "valore":
                                        valore = xmlr.getElementText();
                                        break;
                                    case "seme":
                                        seme = xmlr.getElementText();
                                        valoreSeme.add(new CoppiaValoreSeme(seme, valore));
                                        break;
                                    default:
                                        break;
                                }

                            }
                            else {
                                switch (xmlr.getLocalName()) {
                                    case "arma":
                                        arma = true;
                                        break;
                                    case "carta":
                                        carta = true;
                                        equipaggiabile = xmlr.getAttributeValue(0).equals("true");
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;

                        case XMLStreamConstants.END_ELEMENT:
                            switch (xmlr.getLocalName()) {
                                case "arma":
                                    mazzo.add(new Arma(nome, descrizione, Integer.parseInt(copie), valoreSeme, TipoEquipaggiabile.Arma, Integer.parseInt(distanza)));
                                    arma = false;
                                    valoreSeme = new ArrayList<>();
                                    break;
                                case "carta":
                                    if (equipaggiabile) {
                                        mazzo.add(new Equipaggiabile(nome, descrizione, Integer.parseInt(copie), valoreSeme, tipoEquipaggiabile));
                                    }
                                    else {
                                        mazzo.add(new GiocaEScarta(nome, descrizione, Integer.parseInt(copie), valoreSeme, tipoGiocaEScarta ));
                                    }
                                    carta = false;
                                    valoreSeme = new ArrayList<>();
                                    break;
                                default:
                                    break;
                            }
                            break;

                        default:
                            break;
                    }

                    xmlr.next();
                }

                xmlr.close();
            }
            catch (XMLStreamException e) {
                System.out.println(ERRORE_READER);
                System.out.println(e.getMessage());
            }
        }
        catch (FactoryConfigurationError | XMLStreamException | IOException e) {
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Personaggio> letturaPersonaggiXML(String filename) {
        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        ArrayList<Personaggio> personaggi = new ArrayList<>();

        int PF = 0;
        String nome = "";
        String descrizione = "";
        boolean personaggio = false;


        try (FileInputStream reader = new FileInputStream(filename)) {

            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, reader);

        try {
            while (xmlr.hasNext()) {
                switch (xmlr.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        if(personaggio){
                            if (xmlr.getLocalName().equals("nome")) {
                                nome = xmlr.getElementText();
                            }
                            else if (xmlr.getLocalName().equals("descrizione")) {
                                descrizione = xmlr.getElementText();
                            }
                        }
                        else if(xmlr.getLocalName().equals("personaggio")) {
                            personaggio = true;
                            PF = Integer.parseInt(xmlr.getAttributeValue(0));
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if (xmlr.getLocalName().equals("personaggio")) {
                            personaggi.add(new Personaggio(nome, PF, descrizione));
                            personaggio = false;
                        }
                        break;

                    default:
                        break;
                }

                xmlr.next();
            }

            xmlr.close();
        }
        catch (XMLStreamException e) {
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }
    }
        catch (FactoryConfigurationError | XMLStreamException | IOException e) {
        System.out.println(ERRORE_READER);
        System.out.println(e.getMessage());
    }
        return personaggi;
    }
}
