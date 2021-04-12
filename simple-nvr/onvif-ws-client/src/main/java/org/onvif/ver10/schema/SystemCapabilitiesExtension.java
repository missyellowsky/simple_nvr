
package org.onvif.ver10.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.w3c.dom.Element;


/**
 * <p>SystemCapabilitiesExtension complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SystemCapabilitiesExtension"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="HttpFirmwareUpgrade" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="HttpSystemBackup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="HttpSystemLogging" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="HttpSupportInformation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Extension" type="{http://www.onvif.org/ver10/schema}SystemCapabilitiesExtension2" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemCapabilitiesExtension", propOrder = {
    "any",
    "httpFirmwareUpgrade",
    "httpSystemBackup",
    "httpSystemLogging",
    "httpSupportInformation",
    "extension"
})
public class SystemCapabilitiesExtension {

    @XmlAnyElement(lax = true)
    protected List<java.lang.Object> any;
    @XmlElement(name = "HttpFirmwareUpgrade")
    protected Boolean httpFirmwareUpgrade;
    @XmlElement(name = "HttpSystemBackup")
    protected Boolean httpSystemBackup;
    @XmlElement(name = "HttpSystemLogging")
    protected Boolean httpSystemLogging;
    @XmlElement(name = "HttpSupportInformation")
    protected Boolean httpSupportInformation;
    @XmlElement(name = "Extension")
    protected SystemCapabilitiesExtension2 extension;

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link java.lang.Object }
     * 
     * 
     */
    public List<java.lang.Object> getAny() {
        if (any == null) {
            any = new ArrayList<java.lang.Object>();
        }
        return this.any;
    }

    /**
     * 获取httpFirmwareUpgrade属性的值。
     * This getter has been renamed from isHttpFirmwareUpgrade() to getHttpFirmwareUpgrade() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getHttpFirmwareUpgrade() {
        return httpFirmwareUpgrade;
    }

    /**
     * 设置httpFirmwareUpgrade属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHttpFirmwareUpgrade(Boolean value) {
        this.httpFirmwareUpgrade = value;
    }

    /**
     * 获取httpSystemBackup属性的值。
     * This getter has been renamed from isHttpSystemBackup() to getHttpSystemBackup() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getHttpSystemBackup() {
        return httpSystemBackup;
    }

    /**
     * 设置httpSystemBackup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHttpSystemBackup(Boolean value) {
        this.httpSystemBackup = value;
    }

    /**
     * 获取httpSystemLogging属性的值。
     * This getter has been renamed from isHttpSystemLogging() to getHttpSystemLogging() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getHttpSystemLogging() {
        return httpSystemLogging;
    }

    /**
     * 设置httpSystemLogging属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHttpSystemLogging(Boolean value) {
        this.httpSystemLogging = value;
    }

    /**
     * 获取httpSupportInformation属性的值。
     * This getter has been renamed from isHttpSupportInformation() to getHttpSupportInformation() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getHttpSupportInformation() {
        return httpSupportInformation;
    }

    /**
     * 设置httpSupportInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHttpSupportInformation(Boolean value) {
        this.httpSupportInformation = value;
    }

    /**
     * 获取extension属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemCapabilitiesExtension2 }
     *     
     */
    public SystemCapabilitiesExtension2 getExtension() {
        return extension;
    }

    /**
     * 设置extension属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemCapabilitiesExtension2 }
     *     
     */
    public void setExtension(SystemCapabilitiesExtension2 value) {
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
