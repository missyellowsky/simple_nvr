
package org.onvif.ver10.advancedsecurity.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *         &lt;element name="CertWithPrivateKey" type="{http://www.onvif.org/ver10/advancedsecurity/wsdl}Base64DERencodedASN1Value"/&gt;
 *         &lt;element name="CertificationPathAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="KeyAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IgnoreAdditionalCertificates" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="IntegrityPassphraseID" type="{http://www.onvif.org/ver10/advancedsecurity/wsdl}PassphraseID" minOccurs="0"/&gt;
 *         &lt;element name="EncryptionPassphraseID" type="{http://www.onvif.org/ver10/advancedsecurity/wsdl}PassphraseID" minOccurs="0"/&gt;
 *         &lt;element name="Passphrase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "certWithPrivateKey",
    "certificationPathAlias",
    "keyAlias",
    "ignoreAdditionalCertificates",
    "integrityPassphraseID",
    "encryptionPassphraseID",
    "passphrase"
})
@XmlRootElement(name = "UploadCertificateWithPrivateKeyInPKCS12")
public class UploadCertificateWithPrivateKeyInPKCS12 {

    @XmlElement(name = "CertWithPrivateKey", required = true)
    protected byte[] certWithPrivateKey;
    @XmlElement(name = "CertificationPathAlias")
    protected String certificationPathAlias;
    @XmlElement(name = "KeyAlias")
    protected String keyAlias;
    @XmlElement(name = "IgnoreAdditionalCertificates", defaultValue = "false")
    protected Boolean ignoreAdditionalCertificates;
    @XmlElement(name = "IntegrityPassphraseID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String integrityPassphraseID;
    @XmlElement(name = "EncryptionPassphraseID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String encryptionPassphraseID;
    @XmlElement(name = "Passphrase")
    protected String passphrase;

    /**
     * 获取certWithPrivateKey属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCertWithPrivateKey() {
        return certWithPrivateKey;
    }

    /**
     * 设置certWithPrivateKey属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCertWithPrivateKey(byte[] value) {
        this.certWithPrivateKey = value;
    }

    /**
     * 获取certificationPathAlias属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificationPathAlias() {
        return certificationPathAlias;
    }

    /**
     * 设置certificationPathAlias属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificationPathAlias(String value) {
        this.certificationPathAlias = value;
    }

    /**
     * 获取keyAlias属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyAlias() {
        return keyAlias;
    }

    /**
     * 设置keyAlias属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyAlias(String value) {
        this.keyAlias = value;
    }

    /**
     * 获取ignoreAdditionalCertificates属性的值。
     * This getter has been renamed from isIgnoreAdditionalCertificates() to getIgnoreAdditionalCertificates() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getIgnoreAdditionalCertificates() {
        return ignoreAdditionalCertificates;
    }

    /**
     * 设置ignoreAdditionalCertificates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreAdditionalCertificates(Boolean value) {
        this.ignoreAdditionalCertificates = value;
    }

    /**
     * 获取integrityPassphraseID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntegrityPassphraseID() {
        return integrityPassphraseID;
    }

    /**
     * 设置integrityPassphraseID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntegrityPassphraseID(String value) {
        this.integrityPassphraseID = value;
    }

    /**
     * 获取encryptionPassphraseID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptionPassphraseID() {
        return encryptionPassphraseID;
    }

    /**
     * 设置encryptionPassphraseID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptionPassphraseID(String value) {
        this.encryptionPassphraseID = value;
    }

    /**
     * 获取passphrase属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassphrase() {
        return passphrase;
    }

    /**
     * 设置passphrase属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassphrase(String value) {
        this.passphrase = value;
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
