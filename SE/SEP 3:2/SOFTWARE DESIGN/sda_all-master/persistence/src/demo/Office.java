/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nickytan
 */
@Entity
@Table(name = "OFFICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Office.findAll", query = "SELECT o FROM Office o"),
    @NamedQuery(name = "Office.findByIdOffice", query = "SELECT o FROM Office o WHERE o.idOffice = :idOffice"),
    @NamedQuery(name = "Office.findByCreateddateOffice", query = "SELECT o FROM Office o WHERE o.createddateOffice = :createddateOffice"),
    @NamedQuery(name = "Office.findByModifieddateOffice", query = "SELECT o FROM Office o WHERE o.modifieddateOffice = :modifieddateOffice"),
    @NamedQuery(name = "Office.findByVersionOffice", query = "SELECT o FROM Office o WHERE o.versionOffice = :versionOffice"),
    @NamedQuery(name = "Office.findByName", query = "SELECT o FROM Office o WHERE o.name = :name"),
    @NamedQuery(name = "Office.findByAddressone", query = "SELECT o FROM Office o WHERE o.addressone = :addressone"),
    @NamedQuery(name = "Office.findByAddresstwo", query = "SELECT o FROM Office o WHERE o.addresstwo = :addresstwo"),
    @NamedQuery(name = "Office.findByCity", query = "SELECT o FROM Office o WHERE o.city = :city"),
    @NamedQuery(name = "Office.findByState", query = "SELECT o FROM Office o WHERE o.state = :state"),
    @NamedQuery(name = "Office.findByZipcode", query = "SELECT o FROM Office o WHERE o.zipcode = :zipcode")})
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OFFICE")
    private Long idOffice;
    @Column(name = "CREATEDDATE_OFFICE")
    @Temporal(TemporalType.DATE)
    private Date createddateOffice;
    @Column(name = "MODIFIEDDATE_OFFICE")
    @Temporal(TemporalType.DATE)
    private Date modifieddateOffice;
    @Column(name = "VERSION_OFFICE")
    private Integer versionOffice;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESSONE")
    private String addressone;
    @Column(name = "ADDRESSTWO")
    private String addresstwo;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIPCODE")
    private String zipcode;

    public Office() {
    }

    public Office(Long idOffice) {
        this.idOffice = idOffice;
    }

    public Long getIdOffice() {
        return idOffice;
    }

    public void setIdOffice(Long idOffice) {
        this.idOffice = idOffice;
    }

    public Date getCreateddateOffice() {
        return createddateOffice;
    }

    public void setCreateddateOffice(Date createddateOffice) {
        this.createddateOffice = createddateOffice;
    }

    public Date getModifieddateOffice() {
        return modifieddateOffice;
    }

    public void setModifieddateOffice(Date modifieddateOffice) {
        this.modifieddateOffice = modifieddateOffice;
    }

    public Integer getVersionOffice() {
        return versionOffice;
    }

    public void setVersionOffice(Integer versionOffice) {
        this.versionOffice = versionOffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressone() {
        return addressone;
    }

    public void setAddressone(String addressone) {
        this.addressone = addressone;
    }

    public String getAddresstwo() {
        return addresstwo;
    }

    public void setAddresstwo(String addresstwo) {
        this.addresstwo = addresstwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOffice != null ? idOffice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Office)) {
            return false;
        }
        Office other = (Office) object;
        if ((this.idOffice == null && other.idOffice != null) || (this.idOffice != null && !this.idOffice.equals(other.idOffice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.Office[ idOffice=" + idOffice + " ]";
    }
    
}
