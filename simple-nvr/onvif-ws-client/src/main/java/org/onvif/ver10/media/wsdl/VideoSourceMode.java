
package org.onvif.ver10.media.wsdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.onvif.ver10.schema.VideoResolution;


/**
 * <p>VideoSourceMode complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="VideoSourceMode"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MaxFramerate" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="MaxResolution" type="{http://www.onvif.org/ver10/schema}VideoResolution"/&gt;
 *         &lt;element name="Encodings" type="{http://www.onvif.org/ver10/media/wsdl}EncodingTypes"/&gt;
 *         &lt;element name="Reboot" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Description" type="{http://www.onvif.org/ver10/schema}Description" minOccurs="0"/&gt;
 *         &lt;element name="Extension" type="{http://www.onvif.org/ver10/media/wsdl}VideoSourceModeExtension" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="token" use="required" type="{http://www.onvif.org/ver10/schema}ReferenceToken" /&gt;
 *       &lt;attribute name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoSourceMode", propOrder = {
    "maxFramerate",
    "maxResolution",
    "encodings",
    "reboot",
    "description",
    "extension"
})
public class VideoSourceMode {

    @XmlElement(name = "MaxFramerate")
    protected float maxFramerate;
    @XmlElement(name = "MaxResolution", required = true)
    protected VideoResolution maxResolution;
    @XmlList
    @XmlElement(name = "Encodings", required = true)
    protected List<String> encodings;
    @XmlElement(name = "Reboot")
    protected boolean reboot;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Extension")
    protected VideoSourceModeExtension extension;
    @XmlAttribute(name = "token", required = true)
    protected String token;
    @XmlAttribute(name = "Enabled")
    protected Boolean enabled;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * 获取maxFramerate属性的值。
     * 
     */
    public float getMaxFramerate() {
        return maxFramerate;
    }

    /**
     * 设置maxFramerate属性的值。
     * 
     */
    public void setMaxFramerate(float value) {
        this.maxFramerate = value;
    }

    /**
     * 获取maxResolution属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VideoResolution }
     *     
     */
    public VideoResolution getMaxResolution() {
        return maxResolution;
    }

    /**
     * 设置maxResolution属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VideoResolution }
     *     
     */
    public void setMaxResolution(VideoResolution value) {
        this.maxResolution = value;
    }

    /**
     * Gets the value of the encodings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encodings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncodings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEncodings() {
        if (encodings == null) {
            encodings = new ArrayList<String>();
        }
        return this.encodings;
    }

    /**
     * 获取reboot属性的值。
     * This getter has been renamed from isReboot() to getReboot() by cxf-xjc-boolean plugin.
     * 
     */
    public boolean getReboot() {
        return reboot;
    }

    /**
     * 设置reboot属性的值。
     * 
     */
    public void setReboot(boolean value) {
        this.reboot = value;
    }

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * 获取extension属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VideoSourceModeExtension }
     *     
     */
    public VideoSourceModeExtension getExtension() {
        return extension;
    }

    /**
     * 设置extension属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VideoSourceModeExtension }
     *     
     */
    public void setExtension(VideoSourceModeExtension value) {
        this.extension = value;
    }

    /**
     * 获取token属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * 获取enabled属性的值。
     * This getter has been renamed from isEnabled() to getEnabled() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置enabled属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnabled(Boolean value) {
        this.enabled = value;
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
