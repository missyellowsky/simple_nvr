
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Transport complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Transport"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Protocol" type="{http://www.onvif.org/ver10/schema}TransportProtocol"/&gt;
 *         &lt;element name="Tunnel" type="{http://www.onvif.org/ver10/schema}Transport" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Transport", propOrder = {
    "protocol",
    "tunnel"
})
public class Transport {

    @XmlElement(name = "Protocol", required = true)
    @XmlSchemaType(name = "string")
    protected TransportProtocol protocol;
    @XmlElement(name = "Tunnel")
    protected Transport tunnel;

    /**
     * 获取protocol属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TransportProtocol }
     *     
     */
    public TransportProtocol getProtocol() {
        return protocol;
    }

    /**
     * 设置protocol属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TransportProtocol }
     *     
     */
    public void setProtocol(TransportProtocol value) {
        this.protocol = value;
    }

    /**
     * 获取tunnel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Transport }
     *     
     */
    public Transport getTunnel() {
        return tunnel;
    }

    /**
     * 设置tunnel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Transport }
     *     
     */
    public void setTunnel(Transport value) {
        this.tunnel = value;
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
