package persist.savedata.xmls;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Shristi on 9/21/2017.
 */
@XmlRootElement
public class Node {
    private String name;
    private String value;

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
}
