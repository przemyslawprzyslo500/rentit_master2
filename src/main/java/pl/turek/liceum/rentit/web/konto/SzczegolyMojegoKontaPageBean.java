package pl.turek.liceum.rentit.web.konto;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.web.utils.KontoUtils;

/**
 *
 * @author java
 */
@ManagedBean(name = "szczegolyMojegoKontaPageBean")
@RequestScoped
//@SessionScoped
public class SzczegolyMojegoKontaPageBean implements Serializable{
    
    public SzczegolyMojegoKontaPageBean() {
    }
    
    @ManagedProperty(value="#{kontoSession}")
    private KontoSession kontoSession;

    public void setKontoSession(KontoSession kontoSession) {
        this.kontoSession = kontoSession;
    }
    
    @PostConstruct
    private void init() {
        konto = kontoSession.pobierzMojeKonto();
    }

    private Account konto =  new Account();

    public Account getKonto() {
        return konto;
    }
    
}
