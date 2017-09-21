package parselogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nkushwah on 9/19/2017.
 */
public class XmlData {
    private String fileLocation;
    private List<NodeData> nodes = new ArrayList<>();


    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<NodeData> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeData> nodes) {
        this.nodes = nodes;
    }
}
