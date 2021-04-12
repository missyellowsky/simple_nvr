
package org.onvif.ver10.doorcontrol.wsdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.w3c.dom.Element;


/**
 * 
 *             DoorCapabilities reflect optional functionality of a particular physical entity.
 *             Different door instances may have different set of capabilities.
 *             This information may change during device operation, e.g. if hardware settings are
 *             changed.
 *             The following capabilities are available:
 *           
 * 
 * <p>DoorCapabilities complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DoorCapabilities"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Access" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="AccessTimingOverride" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Lock" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Unlock" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Block" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="DoubleLock" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="LockDown" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="LockOpen" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="DoorMonitor" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="LockMonitor" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="DoubleLockMonitor" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Alarm" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Tamper" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Fault" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;anyAttribute processContents='lax'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoorCapabilities", propOrder = {
    "any"
})
public class DoorCapabilities {

    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "Access")
    protected Boolean access;
    @XmlAttribute(name = "AccessTimingOverride")
    protected Boolean accessTimingOverride;
    @XmlAttribute(name = "Lock")
    protected Boolean lock;
    @XmlAttribute(name = "Unlock")
    protected Boolean unlock;
    @XmlAttribute(name = "Block")
    protected Boolean block;
    @XmlAttribute(name = "DoubleLock")
    protected Boolean doubleLock;
    @XmlAttribute(name = "LockDown")
    protected Boolean lockDown;
    @XmlAttribute(name = "LockOpen")
    protected Boolean lockOpen;
    @XmlAttribute(name = "DoorMonitor")
    protected Boolean doorMonitor;
    @XmlAttribute(name = "LockMonitor")
    protected Boolean lockMonitor;
    @XmlAttribute(name = "DoubleLockMonitor")
    protected Boolean doubleLockMonitor;
    @XmlAttribute(name = "Alarm")
    protected Boolean alarm;
    @XmlAttribute(name = "Tamper")
    protected Boolean tamper;
    @XmlAttribute(name = "Fault")
    protected Boolean fault;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * 获取access属性的值。
     * This getter has been renamed from isAccess() to getAccess() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getAccess() {
        return access;
    }

    /**
     * 设置access属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccess(Boolean value) {
        this.access = value;
    }

    /**
     * 获取accessTimingOverride属性的值。
     * This getter has been renamed from isAccessTimingOverride() to getAccessTimingOverride() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getAccessTimingOverride() {
        return accessTimingOverride;
    }

    /**
     * 设置accessTimingOverride属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAccessTimingOverride(Boolean value) {
        this.accessTimingOverride = value;
    }

    /**
     * 获取lock属性的值。
     * This getter has been renamed from isLock() to getLock() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLock() {
        return lock;
    }

    /**
     * 设置lock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLock(Boolean value) {
        this.lock = value;
    }

    /**
     * 获取unlock属性的值。
     * This getter has been renamed from isUnlock() to getUnlock() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getUnlock() {
        return unlock;
    }

    /**
     * 设置unlock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUnlock(Boolean value) {
        this.unlock = value;
    }

    /**
     * 获取block属性的值。
     * This getter has been renamed from isBlock() to getBlock() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getBlock() {
        return block;
    }

    /**
     * 设置block属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBlock(Boolean value) {
        this.block = value;
    }

    /**
     * 获取doubleLock属性的值。
     * This getter has been renamed from isDoubleLock() to getDoubleLock() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getDoubleLock() {
        return doubleLock;
    }

    /**
     * 设置doubleLock属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoubleLock(Boolean value) {
        this.doubleLock = value;
    }

    /**
     * 获取lockDown属性的值。
     * This getter has been renamed from isLockDown() to getLockDown() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLockDown() {
        return lockDown;
    }

    /**
     * 设置lockDown属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockDown(Boolean value) {
        this.lockDown = value;
    }

    /**
     * 获取lockOpen属性的值。
     * This getter has been renamed from isLockOpen() to getLockOpen() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLockOpen() {
        return lockOpen;
    }

    /**
     * 设置lockOpen属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockOpen(Boolean value) {
        this.lockOpen = value;
    }

    /**
     * 获取doorMonitor属性的值。
     * This getter has been renamed from isDoorMonitor() to getDoorMonitor() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getDoorMonitor() {
        return doorMonitor;
    }

    /**
     * 设置doorMonitor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoorMonitor(Boolean value) {
        this.doorMonitor = value;
    }

    /**
     * 获取lockMonitor属性的值。
     * This getter has been renamed from isLockMonitor() to getLockMonitor() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getLockMonitor() {
        return lockMonitor;
    }

    /**
     * 设置lockMonitor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockMonitor(Boolean value) {
        this.lockMonitor = value;
    }

    /**
     * 获取doubleLockMonitor属性的值。
     * This getter has been renamed from isDoubleLockMonitor() to getDoubleLockMonitor() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getDoubleLockMonitor() {
        return doubleLockMonitor;
    }

    /**
     * 设置doubleLockMonitor属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoubleLockMonitor(Boolean value) {
        this.doubleLockMonitor = value;
    }

    /**
     * 获取alarm属性的值。
     * This getter has been renamed from isAlarm() to getAlarm() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getAlarm() {
        return alarm;
    }

    /**
     * 设置alarm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAlarm(Boolean value) {
        this.alarm = value;
    }

    /**
     * 获取tamper属性的值。
     * This getter has been renamed from isTamper() to getTamper() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getTamper() {
        return tamper;
    }

    /**
     * 设置tamper属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTamper(Boolean value) {
        this.tamper = value;
    }

    /**
     * 获取fault属性的值。
     * This getter has been renamed from isFault() to getFault() by cxf-xjc-boolean plugin.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean getFault() {
        return fault;
    }

    /**
     * 设置fault属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFault(Boolean value) {
        this.fault = value;
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
