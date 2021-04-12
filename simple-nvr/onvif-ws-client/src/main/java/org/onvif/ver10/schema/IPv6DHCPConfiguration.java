
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>IPv6DHCPConfiguration的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="IPv6DHCPConfiguration"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Auto"/&gt;
 *     &lt;enumeration value="Stateful"/&gt;
 *     &lt;enumeration value="Stateless"/&gt;
 *     &lt;enumeration value="Off"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "IPv6DHCPConfiguration")
@XmlEnum
public enum IPv6DHCPConfiguration {

    @XmlEnumValue("Auto")
    AUTO("Auto"),
    @XmlEnumValue("Stateful")
    STATEFUL("Stateful"),
    @XmlEnumValue("Stateless")
    STATELESS("Stateless"),
    @XmlEnumValue("Off")
    OFF("Off");
    private final String value;

    IPv6DHCPConfiguration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IPv6DHCPConfiguration fromValue(String v) {
        for (IPv6DHCPConfiguration c: IPv6DHCPConfiguration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
