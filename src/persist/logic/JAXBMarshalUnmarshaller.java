package persist.logic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import persist.savedata.xmls.*;

import java.io.File;

/**
 * Created by Shristi on 9/21/2017.
 */
public class JAXBMarshalUnmarshaller {

    private final String XML_DATA_FILE = "D:\\bava\\app-data.xml";

    public static void main(String[] args) {
        Xmls xmls = new Xmls();
        xmls.setName("ASd");
        xmls.setPath("mypath");

        JAXBMarshalUnmarshaller marshalUnmarshaller = new JAXBMarshalUnmarshaller();
        marshalUnmarshaller.marshaller(xmls);
    }


    private void marshaller(Xmls xmlData) {
        try {
            JAXBContext context = JAXBContext.newInstance(Xmls.class);
            Marshaller marshaller =context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(xmlData, new File(XML_DATA_FILE));
        } catch (JAXBException ex ) {
            ex.printStackTrace();
        }
    }

    private void unmarshaller() {
        try {
            JAXBContext context = JAXBContext.newInstance(Xmls.class);
            Unmarshaller unmarshaller= context.createUnmarshaller();
            unmarshaller.setProperty();
        } catch (JAXBException ex ) {
            ex.printStackTrace();
        }

    }

}
