//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.06 at 03:25:36 PM EDT 
//


package org.immregistries.dqa.hl7util.parser.profile.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for component complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="component">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Predicate" type="{}predicate"/>
 *         &lt;element name="ConformanceStatement" type="{}conformanceStatement"/>
 *         &lt;element name="SubComponent" type="{}subComponent" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="Datatype" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaxLength" type="{http://www.w3.org/2001/XMLSchema}short" />
 *       &lt;attribute name="MinLength" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PredicateFalseUsage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PredicateTrueUsage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Table" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Usage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "component", propOrder = {
    "predicate",
    "conformanceStatement",
    "subComponent"
})
public class Component {

    @XmlElement(name = "Predicate")
    protected Predicate predicate;
    @XmlElement(name = "ConformanceStatement")
    protected ConformanceStatement conformanceStatement;
    @XmlElement(name = "SubComponent")
    protected List<SubComponent> subComponent;
    @XmlAttribute(name = "Datatype")
    protected String datatype;
    @XmlAttribute(name = "MaxLength")
    protected Short maxLength;
    @XmlAttribute(name = "MinLength")
    protected Byte minLength;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "PredicateFalseUsage")
    protected String predicateFalseUsage;
    @XmlAttribute(name = "PredicateTrueUsage")
    protected String predicateTrueUsage;
    @XmlAttribute(name = "Table")
    protected String table;
    @XmlAttribute(name = "Usage")
    protected String usage;

    /**
     * Gets the value of the predicate property.
     * 
     * @return
     *     possible object is
     *     {@link Predicate }
     *     
     */
    public Predicate getPredicate() {
        return predicate;
    }

    /**
     * Sets the value of the predicate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Predicate }
     *     
     */
    public void setPredicate(Predicate value) {
        this.predicate = value;
    }

    /**
     * Gets the value of the conformanceStatement property.
     * 
     * @return
     *     possible object is
     *     {@link ConformanceStatement }
     *     
     */
    public ConformanceStatement getConformanceStatement() {
        return conformanceStatement;
    }

    /**
     * Sets the value of the conformanceStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConformanceStatement }
     *     
     */
    public void setConformanceStatement(ConformanceStatement value) {
        this.conformanceStatement = value;
    }

    /**
     * Gets the value of the subComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubComponent }
     * 
     * 
     */
    public List<SubComponent> getSubComponent() {
        if (subComponent == null) {
            subComponent = new ArrayList<SubComponent>();
        }
        return this.subComponent;
    }

    /**
     * Gets the value of the datatype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatatype() {
        return datatype;
    }

    /**
     * Sets the value of the datatype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatatype(String value) {
        this.datatype = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setMaxLength(Short value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setMinLength(Byte value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the predicateFalseUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredicateFalseUsage() {
        return predicateFalseUsage;
    }

    /**
     * Sets the value of the predicateFalseUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredicateFalseUsage(String value) {
        this.predicateFalseUsage = value;
    }

    /**
     * Gets the value of the predicateTrueUsage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredicateTrueUsage() {
        return predicateTrueUsage;
    }

    /**
     * Sets the value of the predicateTrueUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredicateTrueUsage(String value) {
        this.predicateTrueUsage = value;
    }

    /**
     * Gets the value of the table property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTable(String value) {
        this.table = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsage(String value) {
        this.usage = value;
    }

}
