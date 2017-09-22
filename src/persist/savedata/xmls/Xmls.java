package persist.savedata.xmls;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Shristi on 9/21/2017.
 */
@XmlRootElement
@XmlType(propOrder = {"name" , "path" , "node"})
public class Xmls {
    private String name;
    private String path;
    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
