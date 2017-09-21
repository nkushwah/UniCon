package persist.savedata.xmls;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Shristi on 9/21/2017.
 */
@XmlRootElement
public class XmlLibrary {
    private List<Xmls> currentXmls;

    public List<Xmls> getXmls() {
        return currentXmls;
    }

    public void setXmls(List<Xmls> currentXmls) {
        this.currentXmls = currentXmls;
    }
}
