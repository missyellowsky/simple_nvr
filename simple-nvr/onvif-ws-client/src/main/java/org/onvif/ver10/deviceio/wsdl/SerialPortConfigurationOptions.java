
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
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.onvif.ver10.schema.FloatList;
import org.onvif.ver10.schema.IntList;
import org.w3c.dom.Element;


/**
 * The configuration options that relates to serial port.
 *           
 * 
 * <p>SerialPortConfigurationOptions complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SerialPortConfigurationOptions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BaudRateList" type="{http://www.onvif.org/ver10/schema}IntList"/&gt;
 *         &lt;element name="ParityBitList" type="{http://www.onvif.org/ver10/deviceIO/wsdl}ParityBitList"/&gt;
 *         &lt;element name="CharacterLengthList" type="{http://www.onvif.org/ver10/schema}IntList"/&gt;
 *         &lt;element name="StopBitList" type="{http://www.onvif.org/ver10/schema}FloatList"/&gt;
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="token" use="required" type="{http://www.onvif.org/ver10/schema}ReferenceToken" /&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SerialPortConfigurationOptions", propOrder = {
    "baudRateList",
    "parityBitList",
    "characterLengthList",
    "stopBitList",
    "any"
})
public class SerialPortConfigurationOptions {

    @XmlElement(name = "BaudRateList", required = true)
    protected IntList baudRateList;
    @XmlElement(name = "ParityBitList", required = true)
    protected ParityBitList parityBitList;
    @XmlElement(name = "CharacterLengthList", required = true)
    protected IntList characterLengthList;
    @XmlElement(name = "StopBitList", required = true)
    protected FloatList stopBitList;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "token", required = true)
    protected String token;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * 获取baudRateList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IntList }
     *     
     */
    public IntList getBaudRateList() {
        return baudRateList;
    }

    /**
     * 设置baudRateList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IntList }
     *     
     */
    public void setBaudRateList(IntList value) {
        this.baudRateList = value;
    }

    /**
     * 获取parityBitList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ParityBitList }
     *     
     */
    public ParityBitList getParityBitList() {
        return parityBitList;
    }

    /**
     * 设置parityBitList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ParityBitList }
     *     
     */
    public void setParityBitList(ParityBitList value) {
        this.parityBitList = value;
    }

    /**
     * 获取characterLengthList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IntList }
     *     
     */
    public IntList getCharacterLengthList() {
        return characterLengthList;
    }

    /**
     * 设置characterLengthList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IntList }
     *     
     */
    public void setCharacterLengthList(IntList value) {
        this.characterLengthList = value;
    }

    /**
     * 获取stopBitList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FloatList }
     *     
     */
    public FloatList getStopBitList() {
        return stopBitList;
    }

    /**
     * 设置stopBitList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FloatList }
     *     
     */
    public void setStopBitList(FloatList value) {
        this.stopBitList = value;
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
