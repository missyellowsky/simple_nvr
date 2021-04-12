
package org.onvif.ver10.search.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.onvif.ver10.schema.MetadataFilter;
import org.onvif.ver10.schema.SearchScope;


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
 *         &lt;element name="StartPoint" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="EndPoint" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Scope" type="{http://www.onvif.org/ver10/schema}SearchScope"/&gt;
 *         &lt;element name="MetadataFilter" type="{http://www.onvif.org/ver10/schema}MetadataFilter"/&gt;
 *         &lt;element name="MaxMatches" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="KeepAliveTime" type="{http://www.w3.org/2001/XMLSchema}duration"/&gt;
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
    "startPoint",
    "endPoint",
    "scope",
    "metadataFilter",
    "maxMatches",
    "keepAliveTime"
})
@XmlRootElement(name = "FindMetadata")
public class FindMetadata {

    @XmlElement(name = "StartPoint", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startPoint;
    @XmlElement(name = "EndPoint")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endPoint;
    @XmlElement(name = "Scope", required = true)
    protected SearchScope scope;
    @XmlElement(name = "MetadataFilter", required = true)
    protected MetadataFilter metadataFilter;
    @XmlElement(name = "MaxMatches")
    protected Integer maxMatches;
    @XmlElement(name = "KeepAliveTime", required = true)
    protected Duration keepAliveTime;

    /**
     * 获取startPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartPoint() {
        return startPoint;
    }

    /**
     * 设置startPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartPoint(XMLGregorianCalendar value) {
        this.startPoint = value;
    }

    /**
     * 获取endPoint属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndPoint() {
        return endPoint;
    }

    /**
     * 设置endPoint属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndPoint(XMLGregorianCalendar value) {
        this.endPoint = value;
    }

    /**
     * 获取scope属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SearchScope }
     *     
     */
    public SearchScope getScope() {
        return scope;
    }

    /**
     * 设置scope属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SearchScope }
     *     
     */
    public void setScope(SearchScope value) {
        this.scope = value;
    }

    /**
     * 获取metadataFilter属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MetadataFilter }
     *     
     */
    public MetadataFilter getMetadataFilter() {
        return metadataFilter;
    }

    /**
     * 设置metadataFilter属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MetadataFilter }
     *     
     */
    public void setMetadataFilter(MetadataFilter value) {
        this.metadataFilter = value;
    }

    /**
     * 获取maxMatches属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxMatches() {
        return maxMatches;
    }

    /**
     * 设置maxMatches属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxMatches(Integer value) {
        this.maxMatches = value;
    }

    /**
     * 获取keepAliveTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getKeepAliveTime() {
        return keepAliveTime;
    }

    /**
     * 设置keepAliveTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setKeepAliveTime(Duration value) {
        this.keepAliveTime = value;
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
