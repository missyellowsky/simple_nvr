
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>TrackType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="TrackType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Video"/&gt;
 *     &lt;enumeration value="Audio"/&gt;
 *     &lt;enumeration value="Metadata"/&gt;
 *     &lt;enumeration value="Extended"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TrackType")
@XmlEnum
public enum TrackType {

    @XmlEnumValue("Video")
    VIDEO("Video"),
    @XmlEnumValue("Audio")
    AUDIO("Audio"),
    @XmlEnumValue("Metadata")
    METADATA("Metadata"),

    /**
     * Placeholder for future extension.
     * 
     */
    @XmlEnumValue("Extended")
    EXTENDED("Extended");
    private final String value;

    TrackType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TrackType fromValue(String v) {
        for (TrackType c: TrackType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
