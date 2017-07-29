package pl.turek.liceum.rentit.web.konto;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.web.utils.ContextUtils;
import pl.turek.liceum.rentit.web.utils.KontoUtils;

/**
 *
 * @author java
 */
@ManagedBean(name = "zmienHasloKontaPageBean")
@RequestScoped
public class ZmienHasloKontaPageBean {
    
    public ZmienHasloKontaPageBean() {
    }
    
    @ManagedProperty(value="#{kontoSession}")
    private KontoSession kontoSession;

    public void setKontoSession(KontoSession kontoSession) {
        this.kontoSession = kontoSession;
    }
    
    private Account konto;

    public Account getKonto() {
        return konto;
    }
    
    @PostConstruct
    private void init(){
        konto = kontoSession.getKontoZmienHaslo();
        konto.setPassword(new String());
    }
    
    private String hasloPowtorz = "";

    public String getHasloPowtorz() {
        return hasloPowtorz;
    }

    public void setHasloPowtorz(String hasloPowtorz) {
        this.hasloPowtorz = hasloPowtorz;
    }
    
    public String zmienHaslo() {
        if (!(hasloPowtorz.equals(konto.getPassword()))){
            ContextUtils.emitInternationalizedMessage("zmienHasloKontaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
            
        return kontoSession.zmienHasloKonta(konto.getPassword());
    }
    
}
