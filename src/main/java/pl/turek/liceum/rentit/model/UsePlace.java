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
@Table(name = "USE_PLACE", catalog = "", schema = "RENTIT")
@TableGenerator(name = "UsePlaceIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "UsePlace", allocationSize = 1 ,initialValue=10)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsePlace.findAll", query = "SELECT u FROM UsePlace u")
    , @NamedQuery(name = "UsePlace.findById", query = "SELECT u FROM UsePlace u WHERE u.id = :id")
    , @NamedQuery(name = "UsePlace.findByBuilding", query = "SELECT u FROM UsePlace u WHERE u.building = :building")
    , @NamedQuery(name = "UsePlace.findByFloor", query = "SELECT u FROM UsePlace u WHERE u.floor = :floor")
    , @NamedQuery(name = "UsePlace.findByPlace", query = "SELECT u FROM UsePlace u WHERE u.place = :place")})
public class UsePlace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "UsePlaceIdGen")
    private Integer id;
    @Size(max = 255)
    @Column(name = "BUILDING", length = 255, nullable = false)
    private String building;
    @Column(name = "FLOOR", nullable = false)
    private Integer floor;
    @Size(max = 255)
    @Column(name = "PLACE", length = 255)
    private String place;
    @OneToMany(mappedBy = "usePlaceId")
    private Collection<Equipment> equipmentCollection;
    @Version
    @Column(name = "VERSION")
    private Long version;

    public UsePlace() {
    }

    public UsePlace(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @XmlTransient
    public Collection<Equipment> getEquipmentCollection() {
        return equipmentCollection;
    }

    public void setEquipmentCollection(Collection<Equipment> equipmentCollection) {
        this.equipmentCollection = equipmentCollection;
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
        if (!(object instanceof UsePlace)) {
            return false;
        }
        UsePlace other = (UsePlace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.turek.liceum.rentit.model.UsePlace[ id=" + id + " ]";
    }
    
}
