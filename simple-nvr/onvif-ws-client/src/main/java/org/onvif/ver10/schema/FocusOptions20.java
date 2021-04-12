
package org.onvif.ver10.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>FocusOptions20 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="FocusOptions20"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AutoFocusModes" type="{http://www.onvif.org/ver10/schema}AutoFocusMode" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DefaultSpeed" type="{http://www.onvif.org/ver10/schema}FloatRange" minOccurs="0"/&gt;
 *         &lt;element name="NearLimit" type="{http://www.onvif.org/ver10/schema}FloatRange" minOccurs="0"/&gt;
 *         &lt;element name="FarLimit" type="{http://www.onvif.org/ver10/schema}FloatRange" minOccurs="0"/&gt;
 *         &lt;element name="Extension" type="{http://www.onvif.org/ver10/schema}FocusOptions20Extension" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FocusOptions20", propOrder = {
    "autoFocusModes",
    "defaultSpeed",
    "nearLimit",
    "farLimit",
    "extension"
})
public class FocusOptions20 {

    @XmlElement(name = "AutoFocusModes")
    @XmlSchemaType(name = "string")
    protected List<AutoFocusMode> autoFocusModes;
    @XmlElement(name = "DefaultSpeed")
    protected FloatRange defaultSpeed;
    @XmlElement(name = "NearLimit")
    protected FloatRange nearLimit;
    @XmlElement(name = "FarLimit")
    protected FloatRange farLimit;
    @XmlElement(name = "Extension")
    protected FocusOptions20Extension extension;

    /**
     * Gets the value of the autoFocusModes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autoFocusModes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutoFocusModes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AutoFocusMode }
     * 
     * 
     */
    public List<AutoFocusMode> getAutoFocusModes() {
        if (autoFocusModes == null) {
            autoFocusModes = new ArrayList<AutoFocusMode>();
        }
        return this.autoFocusModes;
    }

    /**
     * 获取defaultSpeed属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FloatRange }
     *     
     */
    public FloatRange getDefaultSpeed() {
        return defaultSpeed;
    }

    /**
     * 设置defaultSpeed属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FloatRange }
     *     
     */
    public void setDefaultSpeed(FloatRange value) {
        this.defaultSpeed = value;
    }

    /**
     * 获取nearLimit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FloatRange }
     *     
     */
    public FloatRange getNearLimit() {
        return nearLimit;
    }

    /**
     * 设置nearLimit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FloatRange }
     *     
     */
    public void setNearLimit(FloatRange value) {
        this.nearLimit = value;
    }

    /**
     * 获取farLimit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FloatRange }
     *     
     */
    public FloatRange getFarLimit() {
        return farLimit;
    }

    /**
     * 设置farLimit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FloatRange }
     *     
     */
    public void setFarLimit(FloatRange value) {
        this.farLimit = value;
    }

    /**
     * 获取extension属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FocusOptions20Extension }
     *     
     */
    public FocusOptions20Extension getExtension() {
        return extension;
    }

    /**
     * 设置extension属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FocusOptions20Extension }
     *     
     */
    public void setExtension(FocusOptions20Extension value) {
        this.extension = value;
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
