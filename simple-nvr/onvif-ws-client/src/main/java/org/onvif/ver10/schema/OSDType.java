
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OSDType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="OSDType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Text"/&gt;
 *     &lt;enumeration value="Image"/&gt;
 *     &lt;enumeration value="Extended"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OSDType")
@XmlEnum
public enum OSDType {

    @XmlEnumValue("Text")
    TEXT("Text"),
    @XmlEnumValue("Image")
    IMAGE("Image"),
    @XmlEnumValue("Extended")
    EXTENDED("Extended");
    private final String value;

    OSDType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OSDType fromValue(String v) {
        for (OSDType c: OSDType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
