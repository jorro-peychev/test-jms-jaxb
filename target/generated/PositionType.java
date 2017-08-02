
package generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PositionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PositionType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="GK"/&gt;
 *     &lt;enumeration value="DEF"/&gt;
 *     &lt;enumeration value="MID"/&gt;
 *     &lt;enumeration value="ATT"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PositionType")
@XmlEnum
public enum PositionType {

    GK,
    DEF,
    MID,
    ATT;

    public String value() {
        return name();
    }

    public static PositionType fromValue(String v) {
        return valueOf(v);
    }

}
