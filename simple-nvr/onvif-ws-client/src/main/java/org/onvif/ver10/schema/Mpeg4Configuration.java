
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Mpeg4Configuration complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Mpeg4Configuration"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GovLength" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Mpeg4Profile" type="{http://www.onvif.org/ver10/schema}Mpeg4Profile"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Mpeg4Configuration", propOrder = {
    "govLength",
    "mpeg4Profile"
})
public class Mpeg4Configuration {

    @XmlElement(name = "GovLength")
    protected int govLength;
    @XmlElement(name = "Mpeg4Profile", required = true)
    @XmlSchemaType(name = "string")
    protected Mpeg4Profile mpeg4Profile;

    /**
     * 获取govLength属性的值。
     * 
     */
    public int getGovLength() {
        return govLength;
    }

    /**
     * 设置govLength属性的值。
     * 
     */
    public void setGovLength(int value) {
        this.govLength = value;
    }

    /**
     * 获取mpeg4Profile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Mpeg4Profile }
     *     
     */
    public Mpeg4Profile getMpeg4Profile() {
        return mpeg4Profile;
    }

    /**
     * 设置mpeg4Profile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Mpeg4Profile }
     *     
     */
    public void setMpeg4Profile(Mpeg4Profile value) {
        this.mpeg4Profile = value;
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
