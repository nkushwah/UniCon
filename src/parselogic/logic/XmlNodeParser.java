package parselogic.logic;

import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.*;
import parselogic.model.AttributeData;
import parselogic.model.NodeData;
import parselogic.model.XmlData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkushwah on 9/17/2017.
 */
public class XmlNodeParser {

    public static void main(String[] args ) throws Exception {
        XmlNodeParser xmlNodeParser = new XmlNodeParser();
        xmlNodeParser.parseXMLFile("D:/javaprojectfiles/test/applicationContext.xml");
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
        System.out.println(root.getNodeName());

        XmlData xmlData = new XmlData();
        //Get all employees
        NodeList nList = document.getElementsByTagName("bean");

        List<NodeData> beanList = visitChildNodes(nList);
        xmlData.getNodes().addAll(beanList);
        System.out.println("EOF");
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


