package parselogic.logic;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.*;
import parselogic.model.AttributeData;
import parselogic.model.NodeData;
import parselogic.model.XmlData;

import javax.xml.crypto.dsig.TransformException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkushwah on 9/17/2017.
 */
public class XmlNodeParser {

    private static String location = "D:/javaprojectfiles/test/applicationContext.xml";
    public static void main(String[] args ) throws Exception {
        XmlNodeParser xmlNodeParser = new XmlNodeParser();
        xmlNodeParser.parseXMLFile(location);
    }

    public void parseXMLFile(String filePath) throws IOException, SAXException, ParserConfigurationException, org.xml.sax.SAXException {
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Build Document
        Document document = builder.parse(new File(filePath));
        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
            root.getAttributeNode("id").setValue("FUCK");

        saveChangedData(document , location);
        System.out.println(root.getNodeName());

        XmlData xmlData = new XmlData();
        //Get all employees
        NodeList nList = document.getElementsByTagName("bean");

        List<NodeData> beanList = visitChildNodes(nList);

        xmlData.getNodes().addAll(beanList);
        System.out.println("EOF");
    }

    private void changeBean(Document doc ) {


    }

    private void addBean(Document doc) {

    }

    private void deleteBean(Node node ){
        while(node.hasChildNodes()){
            NodeList nList = node.getChildNodes();
            int index = node.getChildNodes().getLength() - 1;

            Node n = nList.item(index);
            deleteBean(n);
            node.removeChild(n);
        }

    }




    private void saveChangedData(Document doc , String filePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transFormer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transFormer.transform(source, result);
            System.out.println("SAVE DONE ");
        } catch (TransformerConfigurationException ex ) {
            ex.printStackTrace();
        } catch (TransformerException ex ) {
            ex.printStackTrace();
        }

    }


    /**
     * recursively visits child nodes to create XmlDataList
     * @param nList
     * @return
     */
    private static List<NodeData> visitChildNodes(NodeList nList)
    {
        List<NodeData> nodeDataList = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            //nodes or individual beans
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {

                System.out.println("Bean Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
                NodeData nodeData = new NodeData(node.getNodeName() , node.getNodeValue());
                nodeDataList.add(nodeData);
                //Check all attributes
                if (node.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++)
                    {
                        Node tempNode = nodeMap.item(i);
                        System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
                        AttributeData attributeData = new AttributeData(tempNode.getNodeName() , tempNode.getNodeValue());
                        nodeData.getAttributes().add(attributeData);
                    }
                    if (node.hasChildNodes()) {
                        //We got more childs; Let's visit them as well
                        nodeData.getChildNodes().addAll(visitChildNodes(node.getChildNodes()));
                    }
                }
            }
        }
        return nodeDataList;
    }
}


