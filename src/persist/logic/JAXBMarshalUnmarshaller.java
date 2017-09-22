package persist.logic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import persist.savedata.xmls.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shristi on 9/21/2017.
 */
public class JAXBMarshalUnmarshaller {

    private final String XML_DATA_FILE = "D:\\bava\\app-data.xml";

    public static void main(String[] args) {
        Xmls xmls = new Xmls();
        xmls.setName("ASd");
        xmls.setPath("mypath");

        Node node = new Node();
        node.setName("Node1");
        node.setValue("NodeValue1");
        xmls.setNode(node);

        Xmls xmls1 = new Xmls();
        xmls1.setName("Neha");
        xmls1.setPath("LKSAD");
        xmls1.setNode(node);

        XmlLibrary library = new XmlLibrary();
        List<Xmls> xmlList = new ArrayList<>();
        xmlList.add(xmls);
        xmlList.add(xmls1);
        library.setXmls(xmlList);

        JAXBMarshalUnmarshaller marshalUnmarshaller = new JAXBMarshalUnmarshaller();
        marshalUnmarshaller.marshaller(library);
        marshalUnmarshaller.unmarshaller();
    }


    private void marshaller(XmlLibrary xmlLibrary) {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlLibrary.class);
            Marshaller marshaller =context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(xmlLibrary, new File(XML_DATA_FILE));
        } catch (JAXBException ex ) {
            ex.printStackTrace();
        }
    }

    private void unmarshaller() {
        try {
            JAXBContext context = JAXBContext.newInstance(XmlLibrary.class);
            Unmarshaller unmarshaller= context.createUnmarshaller();
            XmlLibrary xmlLibrary = (XmlLibrary) unmarshaller.unmarshal(new FileReader(XML_DATA_FILE));
            System.out.println("ASD");
        } catch (JAXBException ex ) {
            ex.printStackTrace();
        } catch (IOException ex ) {
            ex.printStackTrace();
        }

    }

}
