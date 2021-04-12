
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
 * <p>IPv6NetworkInterfaceSetConfiguration complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="IPv6NetworkInterfaceSetConfiguration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AcceptRouterAdvert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Manual" type="{http://www.onvif.org/ver10/schema}PrefixedIPv6Address" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DHCP" type="{http://www.onvif.org/ver10/schema}IPv6DHCPConfiguration" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPv6NetworkInterfaceSetConfiguration", propOrder = {
    "enabled",
    "acceptRouterAdvert",
    "manual",
    "dhcp"
})
public class IPv6NetworkInterfaceSetConfiguration {

    @XmlElement(name = "Enabled")
    protected Boolean enabled;
    @XmlElement(name = "AcceptRouterAdvert")
    protected Boolean acceptRouterAdvert;
    @XmlElement(name = "Manual")
    protected List<PrefixedIPv6Address> manual;
    @XmlElement(name = "DHCP")
    @XmlSchemaType(name = "string")
    protected IPv6DHCPConfiguration dhcp;

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
     * 获取acceptRouterAdvert属性的值。
     * This getter has been renamed from isAcceptRouterAdvert() to getAcceptRouterAdvert() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getAcceptRouterAdvert() {
        return acceptRouterAdvert;
    }

    /**
     * 设置acceptRouterAdvert属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAcceptRouterAdvert(Boolean value) {
        this.acceptRouterAdvert = value;
    }

    /**
     * Gets the value of the manual property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the manual property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManual().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrefixedIPv6Address }
     * 
     * 
     */
    public List<PrefixedIPv6Address> getManual() {
        if (manual == null) {
            manual = new ArrayList<PrefixedIPv6Address>();
        }
        return this.manual;
    }

    /**
     * 获取dhcp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IPv6DHCPConfiguration }
     *     
     */
    public IPv6DHCPConfiguration getDHCP() {
        return dhcp;
    }

    /**
     * 设置dhcp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IPv6DHCPConfiguration }
     *     
     */
    public void setDHCP(IPv6DHCPConfiguration value) {
        this.dhcp = value;
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
