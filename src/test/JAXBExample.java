package test;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by nkushwah on 9/17/2017.
 */
public class JAXBExample {

    public static void main(String args[]) throws IOException, SAXException, ParserConfigurationException, org.xml.sax.SAXException {

        // approch 1 start:

//        Customer customer;
//        customer.setId(100);
//        customer.setName("neha");
//        customer.setAge(24);
        String filepath = "E:\\JavaCode\\HelloWorld\\FileToRead\\applicationContext.xml";
        File file = new File(filepath);
//            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
        //Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
        //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
//
        //jaxbMarshaller.marshal(customer,file);
        //jaxbMarshaller.marshal(customer,System.out);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            customer = (Customer) jaxbUnmarshaller.unmarshal(file);
//            System.out.println("CUST: " + customer.toString());
//            System.out.println("NAME: " + customer.getName());
//            System.out.println("ID: " + customer.getId());

        //approch 1 end

        //approch 2 start
//           try {
//
//               SAXParserFactory factory = SAXParserFactory.newInstance();
//               SAXParser saxParser = factory.newSAXParser();
//               DefaultHandler handler = new DefaultHandler() {
//
//                   boolean bfname = false;
//                   boolean blname = false;
//                   boolean bnname = false;
//                   boolean bsalary = false;
//                   public void startElement(String uri, String localName,String qName,
//                                            Attributes attributes) throws SAXException {
//
//                       System.out.println("Start Element :" + qName);
//
//                       if (qName.equalsIgnoreCase("FIRSTNAME")) {
//                           bfname = true;
//                       }
//
//                       if (qName.equalsIgnoreCase("LASTNAME")) {
//                           blname = true;
//                       }
//
//                       if (qName.equalsIgnoreCase("NICKNAME")) {
//                           bnname = true;
//                       }
//
//                       if (qName.equalsIgnoreCase("SALARY")) {
//                           bsalary = true;
//                       }
//
//                   }
//
//                   public void endElement(String uri, String localName,
//                                          String qName) {
//
//                       System.out.println("End Element :" + qName);
//
//                   }
//
//                   public void characters(char ch[], int start, int length) {
//
//                       if (bfname) {
//                           System.out.println("First Name : " + new String(ch, start, length));
//                           bfname = false;
//                       }
//
//                       if (blname) {
//                           System.out.println("Last Name : " + new String(ch, start, length));
//                           blname = false;
//                       }
//
//                       if (bnname) {
//                           System.out.println("Nick Name : " + new String(ch, start, length));
//                           bnname = false;
//                       }
//
//                       if (bsalary) {
//                           System.out.println("Salary : " + new String(ch, start, length));
//                           bsalary = false;
//                       }
//
//                   }
//
//               };
//
//               saxParser.parse("E:\\JavaCode\\config\\file.xml", handler);
//
//           } catch (Exception e) {
//               e.printStackTrace();
//           }

        //approch 2 end


        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(filepath));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all employees
        NodeList nList = document.getElementsByTagName("FXConfig");
        System.out.println("============================");

        visitChildNodes(nList);
    }

    //This function is called recursively
    private static void visitChildNodes(NodeList nList)
    {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
                //Check all attributes
                if (node.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++)
                    {
                        Node tempNode = nodeMap.item(i);
                        System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
                    }
                    if (node.hasChildNodes()) {
                        //We got more childs; Let's visit them as well
                        visitChildNodes(node.getChildNodes());
                    }
                }
            }
        }
    }
}



