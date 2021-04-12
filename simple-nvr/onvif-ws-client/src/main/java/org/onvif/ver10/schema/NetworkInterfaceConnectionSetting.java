
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>NetworkInterfaceConnectionSetting complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="NetworkInterfaceConnectionSetting"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AutoNegotiation" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Speed" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Duplex" type="{http://www.onvif.org/ver10/schema}Duplex"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkInterfaceConnectionSetting", propOrder = {
    "autoNegotiation",
    "speed",
    "duplex"
})
public class NetworkInterfaceConnectionSetting {

    @XmlElement(name = "AutoNegotiation")
    protected boolean autoNegotiation;
    @XmlElement(name = "Speed")
    protected int speed;
    @XmlElement(name = "Duplex", required = true)
    @XmlSchemaType(name = "string")
    protected Duplex duplex;

    /**
     * 获取autoNegotiation属性的值。
     * This getter has been renamed from isAutoNegotiation() to getAutoNegotiation() by cxf-xjc-boolean plugin.
     * 
     */
    public boolean getAutoNegotiation() {
        return autoNegotiation;
    }

    /**
     * 设置autoNegotiation属性的值。
     * 
     */
    public void setAutoNegotiation(boolean value) {
        this.autoNegotiation = value;
    }

    /**
     * 获取speed属性的值。
     * 
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * 设置speed属性的值。
     * 
     */
    public void setSpeed(int value) {
        this.speed = value;
    }

    /**
     * 获取duplex属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duplex }
     *     
     */
    public Duplex getDuplex() {
        return duplex;
    }

    /**
     * 设置duplex属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duplex }
     *     
     */
    public void setDuplex(Duplex value) {
        this.duplex = value;
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
