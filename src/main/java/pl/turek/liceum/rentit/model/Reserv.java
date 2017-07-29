/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miszcz
 */
@Entity
@Table(name = "RESERV", catalog = "", schema = "RENTIT")
@TableGenerator(name = "ReservIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Reserv", allocationSize = 1, initialValue=10)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserv.findAll", query = "SELECT r FROM Reserv r")
    , @NamedQuery(name = "Reserv.findById", query = "SELECT r FROM Reserv r WHERE r.id = :id")
    , @NamedQuery(name = "Reserv.findByDescription", query = "SELECT r FROM Reserv r WHERE r.description = :description")
    , @NamedQuery(name = "Reserv.findByReservationEnd", query = "SELECT r FROM Reserv r WHERE r.reservationEnd = :reservationEnd")
    , @NamedQuery(name = "Reserv.findByReservationMadeDate", query = "SELECT r FROM Reserv r WHERE r.reservationMadeDate = :reservationMadeDate")
    , @NamedQuery(name = "Reserv.findByReservationStart", query = "SELECT r FROM Reserv r WHERE r.reservationStart = :reservationStart")})
public class Reserv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ReservIdGen")
    private Integer id;
    @Size(max = 255)
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    @Column(name = "RESERVATION_END", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationEnd;
    @Column(name = "RESERVATION_MADE_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationMadeDate;
    @Column(name = "RESERVATION_START", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date reservationStart;
    @Version
    @Column(name = "VERSION")
    private Long version;
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Account accountId;
    @JoinColumn(name = "EQUIPMENT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Equipment equipmentId;
    @JoinColumn(name = "RESERV_STATUS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private ReservStatus reservStatusId;

    public Reserv() {
    }

    public Reserv(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(Date reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public Date getReservationMadeDate() {
        return reservationMadeDate;
    }

    public void setReservationMadeDate(Date reservationMadeDate) {
        this.reservationMadeDate = reservationMadeDate;
    }

    public Date getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(Date reservationStart) {
        this.reservationStart = reservationStart;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public Equipment getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Equipment equipmentId) {
        this.equipmentId = equipmentId;
    }

    public ReservStatus getReservStatusId() {
        return reservStatusId;
    }

    public void setReservStatusId(ReservStatus reservStatusId) {
        this.reservStatusId = reservStatusId;
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
        if (!(object instanceof Reserv)) {
            return false;
        }
        Reserv other = (Reserv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.turek.liceum.rentit.model.Reserv[ id=" + id + " ]";
    }
    
}
