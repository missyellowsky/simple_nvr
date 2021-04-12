
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MoveStatus的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="MoveStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="IDLE"/&gt;
 *     &lt;enumeration value="MOVING"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MoveStatus")
@XmlEnum
public enum MoveStatus {

    IDLE,
    MOVING,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static MoveStatus fromValue(String v) {
        return valueOf(v);
    }

}
