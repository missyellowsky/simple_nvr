
package org.onvif.ver10.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Rectangle complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Rectangle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="bottom" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="top" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="right" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="left" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rectangle")
public class Rectangle {

    @XmlAttribute(name = "bottom")
    protected Float bottom;
    @XmlAttribute(name = "top")
    protected Float top;
    @XmlAttribute(name = "right")
    protected Float right;
    @XmlAttribute(name = "left")
    protected Float left;

    /**
     * 获取bottom属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getBottom() {
        return bottom;
    }

    /**
     * 设置bottom属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setBottom(Float value) {
        this.bottom = value;
    }

    /**
     * 获取top属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTop() {
        return top;
    }

    /**
     * 设置top属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTop(Float value) {
        this.top = value;
    }

    /**
     * 获取right属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRight() {
        return right;
    }

    /**
     * 设置right属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRight(Float value) {
        this.right = value;
    }

    /**
     * 获取left属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLeft() {
        return left;
    }

    /**
     * 设置left属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLeft(Float value) {
        this.left = value;
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
