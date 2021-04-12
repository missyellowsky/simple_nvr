
package org.onvif.ver10.deviceio.wsdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.w3c.dom.Element;


/**
 * The parameters for configuring the serial port.
 * 
 * <p>SerialPortConfiguration complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SerialPortConfiguration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BaudRate" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ParityBit" type="{http://www.onvif.org/ver10/deviceIO/wsdl}ParityBit"/&gt;
 *         &lt;element name="CharacterLength" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="StopBit" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="token" use="required" type="{http://www.onvif.org/ver10/schema}ReferenceToken" /&gt;
 *       &lt;attribute name="type" use="required" type="{http://www.onvif.org/ver10/deviceIO/wsdl}SerialPortType" /&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SerialPortConfiguration", propOrder = {
    "baudRate",
    "parityBit",
    "characterLength",
    "stopBit",
    "any"
})
public class SerialPortConfiguration {

    @XmlElement(name = "BaudRate")
    protected int baudRate;
    @XmlElement(name = "ParityBit", required = true)
    @XmlSchemaType(name = "string")
    protected ParityBit parityBit;
    @XmlElement(name = "CharacterLength")
    protected int characterLength;
    @XmlElement(name = "StopBit")
    protected float stopBit;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "token", required = true)
    protected String token;
    @XmlAttribute(name = "type", required = true)
    protected SerialPortType type;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * 获取baudRate属性的值。
     * 
     */
    public int getBaudRate() {
        return baudRate;
    }

    /**
     * 设置baudRate属性的值。
     * 
     */
    public void setBaudRate(int value) {
        this.baudRate = value;
    }

    /**
     * 获取parityBit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ParityBit }
     *     
     */
    public ParityBit getParityBit() {
        return parityBit;
    }

    /**
     * 设置parityBit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ParityBit }
     *     
     */
    public void setParityBit(ParityBit value) {
        this.parityBit = value;
    }

    /**
     * 获取characterLength属性的值。
     * 
     */
    public int getCharacterLength() {
        return characterLength;
    }

    /**
     * 设置characterLength属性的值。
     * 
     */
    public void setCharacterLength(int value) {
        this.characterLength = value;
    }

    /**
     * 获取stopBit属性的值。
     * 
     */
    public float getStopBit() {
        return stopBit;
    }

    /**
     * 设置stopBit属性的值。
     * 
     */
    public void setStopBit(float value) {
        this.stopBit = value;
    }

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
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * 获取token属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SerialPortType }
     *     
     */
    public SerialPortType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SerialPortType }
     *     
     */
    public void setType(SerialPortType value) {
        this.type = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
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
