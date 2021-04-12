
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>NetworkProtocolType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="NetworkProtocolType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="HTTP"/&gt;
 *     &lt;enumeration value="HTTPS"/&gt;
 *     &lt;enumeration value="RTSP"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "NetworkProtocolType")
@XmlEnum
public enum NetworkProtocolType {

    HTTP,
    HTTPS,
    RTSP;

    public String value() {
        return name();
    }

    public static NetworkProtocolType fromValue(String v) {
        return valueOf(v);
    }

}
