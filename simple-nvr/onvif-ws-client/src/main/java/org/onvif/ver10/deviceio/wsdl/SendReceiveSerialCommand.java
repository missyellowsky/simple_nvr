
package org.onvif.ver10.deviceio.wsdl;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
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
 *         &lt;element name="SerialData" type="{http://www.onvif.org/ver10/deviceIO/wsdl}SerialData" minOccurs="0"/&gt;
 *         &lt;element name="TimeOut" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="DataLength" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="Delimiter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "serialData",
    "timeOut",
    "dataLength",
    "delimiter"
})
@XmlRootElement(name = "SendReceiveSerialCommand")
public class SendReceiveSerialCommand {

    @XmlElement(name = "SerialData")
    protected SerialData serialData;
    @XmlElement(name = "TimeOut")
    protected Duration timeOut;
    @XmlElement(name = "DataLength")
    protected BigInteger dataLength;
    @XmlElement(name = "Delimiter")
    protected String delimiter;

    /**
     * 获取serialData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SerialData }
     *     
     */
    public SerialData getSerialData() {
        return serialData;
    }

    /**
     * 设置serialData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SerialData }
     *     
     */
    public void setSerialData(SerialData value) {
        this.serialData = value;
    }

    /**
     * 获取timeOut属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getTimeOut() {
        return timeOut;
    }

    /**
     * 设置timeOut属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setTimeOut(Duration value) {
        this.timeOut = value;
    }

    /**
     * 获取dataLength属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDataLength() {
        return dataLength;
    }

    /**
     * 设置dataLength属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDataLength(BigInteger value) {
        this.dataLength = value;
    }

    /**
     * 获取delimiter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * 设置delimiter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelimiter(String value) {
        this.delimiter = value;
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
