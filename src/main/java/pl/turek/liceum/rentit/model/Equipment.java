/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miszcz
 */
@Entity
@Table(name = "EQUIPMENT", catalog = "", schema = "RENTIT")
@TableGenerator(name = "EquipmentIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Equipment",allocationSize = 1, initialValue=10)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipment.findAll", query = "SELECT e FROM Equipment e")
    , @NamedQuery(name = "Equipment.findById", query = "SELECT e FROM Equipment e WHERE e.id = :id")
    , @NamedQuery(name = "Equipment.findByName", query = "SELECT e FROM Equipment e WHERE e.name = :name")
    , @NamedQuery(name = "Equipment.findByRentPermission", query = "SELECT e FROM Equipment e WHERE e.rentPermission = :rentPermission")
    , @NamedQuery(name = "Equipment.findByType", query = "SELECT e FROM Equipment e WHERE e.type = :type")})
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EquipmentIdGen")
    private Integer id;
    @Size(max = 255)
    @Column(name = "NAME", length = 255, nullable = false)
    private String name;
    @Column(name = "RENT_PERMISSION", nullable = false)
    private boolean rentPermission;
    @Size(max = 255)
    @Column(name = "TYPE", length = 255, nullable = false)
    private String type;
    @JoinColumn(name = "LICENSE_TYPE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private LicenseType licenseTypeId;
    @JoinColumn(name = "USE_PLACE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private UsePlace usePlaceId;
    @Version
    @Column(name = "VERSION")
    private Long version;
    @OneToMany(mappedBy = "equipmentId")
    private Collection<Reserv> reservCollection;

    public Equipment() {
    }

    public Equipment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRentPermission() {
        return rentPermission;
    }

    public void setRentPermission(boolean rentPermission) {
        this.rentPermission = rentPermission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LicenseType getLicenseTypeId() {
        return licenseTypeId;
    }

    public void setLicenseTypeId(LicenseType licenseTypeId) {
        this.licenseTypeId = licenseTypeId;
    }

    public UsePlace getUsePlaceId() {
        return usePlaceId;
    }

    public void setUsePlaceId(UsePlace usePlaceId) {
        this.usePlaceId = usePlaceId;
    }

    @XmlTransient
    public Collection<Reserv> getReservCollection() {
        return reservCollection;
    }

    public void setReservCollection(Collection<Reserv> reservCollection) {
        this.reservCollection = reservCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.turek.liceum.rentit.model.Equipment[ id=" + id + " ]";
    }
    
}
