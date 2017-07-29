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
@ManagedBean(name = "zmienMojeHasloPageBean")
@RequestScoped
public class ZmienMojeHasloPageBean {
    
    public ZmienMojeHasloPageBean() {
    }
    
    @ManagedProperty(value="#{kontoSession}")
    private KontoSession kontoSession;

    public void setKontoSession(KontoSession kontoSession) {
        this.kontoSession = kontoSession;
    }
    
    private Account konto = new Account();

    public Account getKonto() {
        return konto;
    }
        
    private String hasloPowtorz = "";

    public String getHasloPowtorz() {
        return hasloPowtorz;
    }

    public void setHasloPowtorz(String hasloPowtorz) {
        this.hasloPowtorz = hasloPowtorz;
    }
    
    public String stareHaslo = "";

    public String getStareHaslo() {
        return stareHaslo;
    }

    public void setStareHaslo(String stareHaslo) {
        this.stareHaslo = stareHaslo;
    }
    
    public String zmienHaslo() {
        if (!(hasloPowtorz.equals(konto.getPassword()))){
            ContextUtils.emitInternationalizedMessage("zmienMojeHasloForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
            
        return kontoSession.zmienMojeHaslo(stareHaslo, konto.getPassword());
    }
    
}
