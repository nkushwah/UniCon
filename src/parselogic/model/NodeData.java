package parselogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Each bean in vms file
 * Created by nkushwah on 9/19/2017.
 */
public class NodeData {
    private String name;
    private String value;
    private List<AttributeData> attributes = new ArrayList<>();
    public List<NodeData> childNodes = new ArrayList<>();

    public List<NodeData> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<NodeData> childNodes) {
        this.childNodes = childNodes;
    }

    public NodeData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public NodeData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AttributeData> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeData> attributes) {
        this.attributes = attributes;
    }
}
