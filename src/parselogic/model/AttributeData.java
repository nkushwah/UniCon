package parselogic.model;

/**
 * Created by nkushwah on 9/19/2017.
 */
public class AttributeData {

    public AttributeData() {
    }

    public AttributeData(String name, String value) {
        this.name = name;
        this.value = value;
    }

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
