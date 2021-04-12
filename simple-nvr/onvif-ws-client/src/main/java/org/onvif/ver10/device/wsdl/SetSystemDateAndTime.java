
package org.onvif.ver10.device.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.onvif.ver10.schema.DateTime;
import org.onvif.ver10.schema.SetDateTimeType;
import org.onvif.ver10.schema.TimeZone;


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
 *         &lt;element name="DateTimeType" type="{http://www.onvif.org/ver10/schema}SetDateTimeType"/&gt;
 *         &lt;element name="DaylightSavings" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="TimeZone" type="{http://www.onvif.org/ver10/schema}TimeZone" minOccurs="0"/&gt;
 *         &lt;element name="UTCDateTime" type="{http://www.onvif.org/ver10/schema}DateTime" minOccurs="0"/&gt;
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
    "dateTimeType",
    "daylightSavings",
    "timeZone",
    "utcDateTime"
})
@XmlRootElement(name = "SetSystemDateAndTime")
public class SetSystemDateAndTime {

    @XmlElement(name = "DateTimeType", required = true)
    @XmlSchemaType(name = "string")
    protected SetDateTimeType dateTimeType;
    @XmlElement(name = "DaylightSavings")
    protected boolean daylightSavings;
    @XmlElement(name = "TimeZone")
    protected TimeZone timeZone;
    @XmlElement(name = "UTCDateTime")
    protected DateTime utcDateTime;

    /**
     * 获取dateTimeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SetDateTimeType }
     *     
     */
    public SetDateTimeType getDateTimeType() {
        return dateTimeType;
    }

    /**
     * 设置dateTimeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SetDateTimeType }
     *     
     */
    public void setDateTimeType(SetDateTimeType value) {
        this.dateTimeType = value;
    }

    /**
     * 获取daylightSavings属性的值。
     * This getter has been renamed from isDaylightSavings() to getDaylightSavings() by cxf-xjc-boolean plugin.
     * 
     */
    public boolean getDaylightSavings() {
        return daylightSavings;
    }

    /**
     * 设置daylightSavings属性的值。
     * 
     */
    public void setDaylightSavings(boolean value) {
        this.daylightSavings = value;
    }

    /**
     * 获取timeZone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TimeZone }
     *     
     */
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * 设置timeZone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TimeZone }
     *     
     */
    public void setTimeZone(TimeZone value) {
        this.timeZone = value;
    }

    /**
     * 获取utcDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DateTime }
     *     
     */
    public DateTime getUTCDateTime() {
        return utcDateTime;
    }

    /**
     * 设置utcDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DateTime }
     *     
     */
    public void setUTCDateTime(DateTime value) {
        this.utcDateTime = value;
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
