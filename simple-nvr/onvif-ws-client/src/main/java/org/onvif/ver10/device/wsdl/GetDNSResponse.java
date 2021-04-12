
package org.onvif.ver10.device.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.onvif.ver10.schema.DNSInformation;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DNSInformation" type="{http://www.onvif.org/ver10/schema}DNSInformation"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dnsInformation"
})
@XmlRootElement(name = "GetDNSResponse")
public class GetDNSResponse {

    @XmlElement(name = "DNSInformation", required = true)
    protected DNSInformation dnsInformation;

    /**
     * 获取dnsInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DNSInformation }
     *     
     */
    public DNSInformation getDNSInformation() {
        return dnsInformation;
    }

    /**
     * 设置dnsInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DNSInformation }
     *     
     */
    public void setDNSInformation(DNSInformation value) {
        this.dnsInformation = value;
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
