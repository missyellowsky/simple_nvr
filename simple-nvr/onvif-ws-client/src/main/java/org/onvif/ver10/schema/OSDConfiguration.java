
package org.onvif.ver10.schema;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>OSDConfiguration complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OSDConfiguration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.onvif.org/ver10/schema}DeviceEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="VideoSourceConfigurationToken" type="{http://www.onvif.org/ver10/schema}OSDReference"/&gt;
 *         &lt;element name="Type" type="{http://www.onvif.org/ver10/schema}OSDType"/&gt;
 *         &lt;element name="Position" type="{http://www.onvif.org/ver10/schema}OSDPosConfiguration"/&gt;
 *         &lt;element name="TextString" type="{http://www.onvif.org/ver10/schema}OSDTextConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="Image" type="{http://www.onvif.org/ver10/schema}OSDImgConfiguration" minOccurs="0"/&gt;
 *         &lt;element name="Extension" type="{http://www.onvif.org/ver10/schema}OSDConfigurationExtension" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OSDConfiguration", propOrder = {
    "videoSourceConfigurationToken",
    "type",
    "position",
    "textString",
    "image",
    "extension"
})
public class OSDConfiguration
    extends DeviceEntity
{

    @XmlElement(name = "VideoSourceConfigurationToken", required = true)
    protected OSDReference videoSourceConfigurationToken;
    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected OSDType type;
    @XmlElement(name = "Position", required = true)
    protected OSDPosConfiguration position;
    @XmlElement(name = "TextString")
    protected OSDTextConfiguration textString;
    @XmlElement(name = "Image")
    protected OSDImgConfiguration image;
    @XmlElement(name = "Extension")
    protected OSDConfigurationExtension extension;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * 获取videoSourceConfigurationToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OSDReference }
     *     
     */
    public OSDReference getVideoSourceConfigurationToken() {
        return videoSourceConfigurationToken;
    }

    /**
     * 设置videoSourceConfigurationToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OSDReference }
     *     
     */
    public void setVideoSourceConfigurationToken(OSDReference value) {
        this.videoSourceConfigurationToken = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OSDType }
     *     
     */
    public OSDType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OSDType }
     *     
     */
    public void setType(OSDType value) {
        this.type = value;
    }

    /**
     * 获取position属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OSDPosConfiguration }
     *     
     */
    public OSDPosConfiguration getPosition() {
        return position;
    }

    /**
     * 设置position属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OSDPosConfiguration }
     *     
     */
    public void setPosition(OSDPosConfiguration value) {
        this.position = value;
    }

    /**
     * 获取textString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OSDTextConfiguration }
     *     
     */
    public OSDTextConfiguration getTextString() {
        return textString;
    }

    /**
     * 设置textString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OSDTextConfiguration }
     *     
     */
    public void setTextString(OSDTextConfiguration value) {
        this.textString = value;
    }

    /**
     * 获取image属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OSDImgConfiguration }
     *     
     */
    public OSDImgConfiguration getImage() {
        return image;
    }

    /**
     * 设置image属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OSDImgConfiguration }
     *     
     */
    public void setImage(OSDImgConfiguration value) {
        this.image = value;
    }

    /**
     * 获取extension属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OSDConfigurationExtension }
     *     
     */
    public OSDConfigurationExtension getExtension() {
        return extension;
    }

    /**
     * 设置extension属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OSDConfigurationExtension }
     *     
     */
    public void setExtension(OSDConfigurationExtension value) {
        this.extension = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.DEFAULT_STYLE);
    }

}
