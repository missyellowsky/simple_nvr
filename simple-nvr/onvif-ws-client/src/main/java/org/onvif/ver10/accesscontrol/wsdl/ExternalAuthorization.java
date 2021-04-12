
package org.onvif.ver10.accesscontrol.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="AccessPointToken" type="{http://www.onvif.org/ver10/pacs}ReferenceToken"/&gt;
 *         &lt;element name="CredentialToken" type="{http://www.onvif.org/ver10/pacs}ReferenceToken" minOccurs="0"/&gt;
 *         &lt;element name="Reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Decision" type="{http://www.onvif.org/ver10/accesscontrol/wsdl}Decision"/&gt;
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
    "accessPointToken",
    "credentialToken",
    "reason",
    "decision"
})
@XmlRootElement(name = "ExternalAuthorization")
public class ExternalAuthorization {

    @XmlElement(name = "AccessPointToken", required = true)
    protected String accessPointToken;
    @XmlElement(name = "CredentialToken")
    protected String credentialToken;
    @XmlElement(name = "Reason")
    protected String reason;
    @XmlElement(name = "Decision", required = true)
    @XmlSchemaType(name = "string")
    protected Decision decision;

    /**
     * 获取accessPointToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessPointToken() {
        return accessPointToken;
    }

    /**
     * 设置accessPointToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessPointToken(String value) {
        this.accessPointToken = value;
    }

    /**
     * 获取credentialToken属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCredentialToken() {
        return credentialToken;
    }

    /**
     * 设置credentialToken属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCredentialToken(String value) {
        this.credentialToken = value;
    }

    /**
     * 获取reason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置reason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * 获取decision属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Decision }
     *     
     */
    public Decision getDecision() {
        return decision;
    }

    /**
     * 设置decision属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Decision }
     *     
     */
    public void setDecision(Decision value) {
        this.decision = value;
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
