
package org.onvif.ver10.recording.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;
import org.onvif.ver10.schema.SearchScope;
import org.onvif.ver10.schema.StorageReferencePath;


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
 *         &lt;element name="SearchScope" type="{http://www.onvif.org/ver10/schema}SearchScope"/&gt;
 *         &lt;element name="FileFormat" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="StorageDestination" type="{http://www.onvif.org/ver10/schema}StorageReferencePath"/&gt;
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
    "searchScope",
    "fileFormat",
    "storageDestination"
})
@XmlRootElement(name = "ExportRecordedData")
public class ExportRecordedData {

    @XmlElement(name = "SearchScope", required = true)
    protected SearchScope searchScope;
    @XmlElement(name = "FileFormat", required = true)
    protected String fileFormat;
    @XmlElement(name = "StorageDestination", required = true)
    protected StorageReferencePath storageDestination;

    /**
     * 获取searchScope属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SearchScope }
     *     
     */
    public SearchScope getSearchScope() {
        return searchScope;
    }

    /**
     * 设置searchScope属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SearchScope }
     *     
     */
    public void setSearchScope(SearchScope value) {
        this.searchScope = value;
    }

    /**
     * 获取fileFormat属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileFormat() {
        return fileFormat;
    }

    /**
     * 设置fileFormat属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileFormat(String value) {
        this.fileFormat = value;
    }

    /**
     * 获取storageDestination属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StorageReferencePath }
     *     
     */
    public StorageReferencePath getStorageDestination() {
        return storageDestination;
    }

    /**
     * 设置storageDestination属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StorageReferencePath }
     *     
     */
    public void setStorageDestination(StorageReferencePath value) {
        this.storageDestination = value;
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
