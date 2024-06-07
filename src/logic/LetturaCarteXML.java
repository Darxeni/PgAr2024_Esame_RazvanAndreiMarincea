package logic;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.IOException;

import static costants.costants.*;

public class LetturaCarteXML {
    public static void readXML(String filename)  {
        XMLInputFactory xmlif;
        XMLStreamReader xmlr;

        try (FileInputStream reader = new FileInputStream(filename))    {

            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, reader);

            try {
                while (xmlr.hasNext()) {
                    switch (xmlr.getEventType()) {
                        case XMLStreamConstants.START_ELEMENT:

                            break;

                        case XMLStreamConstants.END_ELEMENT:

                            break;

                        default:
                            break;
                    }

                    xmlr.next();
                }

                xmlr.close();
            }
            catch (XMLStreamException e)    {
                System.out.println(ERRORE_READER);
                System.out.println(e.getMessage());
            }
        }
        catch (FactoryConfigurationError | XMLStreamException | IOException e)  {
            System.out.println(ERRORE_READER);
            System.out.println(e.getMessage());
        }
    }
}
