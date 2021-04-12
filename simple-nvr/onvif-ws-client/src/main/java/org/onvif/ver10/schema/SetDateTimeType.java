
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SetDateTimeType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="SetDateTimeType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Manual"/&gt;
 *     &lt;enumeration value="NTP"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SetDateTimeType")
@XmlEnum
public enum SetDateTimeType {


    /**
     * Indicates that the date and time are set manually.
     * 
     */
    @XmlEnumValue("Manual")
    MANUAL("Manual"),

    /**
     * Indicates that the date and time are set through NTP
     * 
     */
    NTP("NTP");
    private final String value;

    SetDateTimeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SetDateTimeType fromValue(String v) {
        for (SetDateTimeType c: SetDateTimeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
