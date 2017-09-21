package persist.savedata.xmls;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Shristi on 9/21/2017.
 */
@XmlRootElement
public class Xmls {
    private String name;
    private String path;

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
