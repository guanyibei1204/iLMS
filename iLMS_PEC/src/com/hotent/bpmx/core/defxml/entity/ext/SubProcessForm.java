//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.10.26 at 07:21:56 下午 CST 
//


package com.hotent.bpmx.core.defxml.entity.ext;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * 子流程表单
 * 
 * <p>Java class for subProcessForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subProcessForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="form" type="{http://www.jee-soft.cn/bpm}form" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mobileForm" type="{http://www.jee-soft.cn/bpm}mobileForm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subProcessForm", propOrder = {
    "formOrMobileForm"
})
public class SubProcessForm {

    @XmlElements({
        @XmlElement(name = "form"),
        @XmlElement(name = "mobileForm", type = MobileForm.class)
    })
    protected List<Form> formOrMobileForm;

    /**
     * Gets the value of the formOrMobileForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formOrMobileForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormOrMobileForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Form }
     * {@link MobileForm }
     * 
     * 
     */
    public List<Form> getFormOrMobileForm() {
        if (formOrMobileForm == null) {
            formOrMobileForm = new ArrayList<Form>();
        }
        return this.formOrMobileForm;
    }

}