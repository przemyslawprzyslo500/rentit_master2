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
@Table(name = "ACCOUNT", catalog = "", schema = "RENTIT")
@TableGenerator(name = "AccountIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Account",allocationSize = 1, initialValue=10)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id")
    , @NamedQuery(name = "Account.findByAccountFunction", query = "SELECT a FROM Account a WHERE a.accountFunction = :accountFunction")
//    , @NamedQuery(name = "Account.findByActive", query = "SELECT a FROM Account a WHERE a.active = :active")
    , @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
    , @NamedQuery(name = "Account.findByLogin", query = "SELECT a FROM Account a WHERE a.login = :login")
    , @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByPhone", query = "SELECT a FROM Account a WHERE a.phone = :phone")
    , @NamedQuery(name = "Account.findBySurname", query = "SELECT a FROM Account a WHERE a.surname = :surname")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountIdGen")
    private Integer id;
    @Column(name = "ACCOUNT_FUNCTION", nullable = false, length = 20)
    private String accountFunction;
//    @Column(name = "ACTIVE")
//    private boolean active;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL", length = 255)
    private String email;
    @Size(max = 255)
    @Column(name = "LOGIN", length = 255, nullable = false, unique = true)
    private String login;
    @Size(max = 255)
    @Column(name = "NAME", length = 255, nullable = false)
    private String name;
    @Size(max = 255)
    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "PHONE", length = 255, nullable = false)
    private String phone;
    @Size(max = 255)
    @Column(name = "SURNAME", length = 255, nullable = false)
    private String surname;
    @Version
    @Column(name = "VERSION")
    private Long version;
    @OneToMany(mappedBy = "accountId")
    private Collection<Reserv> reservCollection;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountFunction() {
        return accountFunction;
    }

    public void setAccountFunction(String accountFunction) {
        this.accountFunction = accountFunction;
    }

//    public boolean getActive() {
//        return active;
//    }

//    public void setActive(boolean active) {
//        this.active = active;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.turek.liceum.rentit.model.Account[ id=" + id + " ]";
    }
    
}
