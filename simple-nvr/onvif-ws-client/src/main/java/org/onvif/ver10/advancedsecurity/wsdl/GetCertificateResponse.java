
package org.onvif.ver10.advancedsecurity.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


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
 *         &lt;element name="Certificate" type="{http://www.onvif.org/ver10/advancedsecurity/wsdl}X509Certificate"/&gt;
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
    "certificate"
})
@XmlRootElement(name = "GetCertificateResponse")
public class GetCertificateResponse {

    @XmlElement(name = "Certificate", required = true)
    protected X509Certificate certificate;

    /**
     * 获取certificate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link X509Certificate }
     *     
     */
    public X509Certificate getCertificate() {
        return certificate;
    }

    /**
     * 设置certificate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link X509Certificate }
     *     
     */
    public void setCertificate(X509Certificate value) {
        this.certificate = value;
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
