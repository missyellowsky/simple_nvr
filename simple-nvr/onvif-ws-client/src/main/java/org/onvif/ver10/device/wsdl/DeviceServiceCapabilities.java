
package org.onvif.ver10.device.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>DeviceServiceCapabilities complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DeviceServiceCapabilities"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Network" type="{http://www.onvif.org/ver10/device/wsdl}NetworkCapabilities"/&gt;
 *         &lt;element name="Security" type="{http://www.onvif.org/ver10/device/wsdl}SecurityCapabilities"/&gt;
 *         &lt;element name="System" type="{http://www.onvif.org/ver10/device/wsdl}SystemCapabilities"/&gt;
 *         &lt;element name="Misc" type="{http://www.onvif.org/ver10/device/wsdl}MiscCapabilities" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceServiceCapabilities", propOrder = {
    "network",
    "security",
    "system",
    "misc"
})
public class DeviceServiceCapabilities {

    @XmlElement(name = "Network", required = true)
    protected NetworkCapabilities network;
    @XmlElement(name = "Security", required = true)
    protected SecurityCapabilities security;
    @XmlElement(name = "System", required = true)
    protected SystemCapabilities system;
    @XmlElement(name = "Misc")
    protected MiscCapabilities misc;

    /**
     * 获取network属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NetworkCapabilities }
     *     
     */
    public NetworkCapabilities getNetwork() {
        return network;
    }

    /**
     * 设置network属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkCapabilities }
     *     
     */
    public void setNetwork(NetworkCapabilities value) {
        this.network = value;
    }

    /**
     * 获取security属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SecurityCapabilities }
     *     
     */
    public SecurityCapabilities getSecurity() {
        return security;
    }

    /**
     * 设置security属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityCapabilities }
     *     
     */
    public void setSecurity(SecurityCapabilities value) {
        this.security = value;
    }

    /**
     * 获取system属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemCapabilities }
     *     
     */
    public SystemCapabilities getSystem() {
        return system;
    }

    /**
     * 设置system属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemCapabilities }
     *     
     */
    public void setSystem(SystemCapabilities value) {
        this.system = value;
    }

    /**
     * 获取misc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MiscCapabilities }
     *     
     */
    public MiscCapabilities getMisc() {
        return misc;
    }

    /**
     * 设置misc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MiscCapabilities }
     *     
     */
    public void setMisc(MiscCapabilities value) {
        this.misc = value;
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
