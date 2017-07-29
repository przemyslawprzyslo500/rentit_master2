package pl.turek.liceum.rentit.web.konto;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.turek.liceum.rentit.model.Account;

/**
 *
 * @author java
 */
@ManagedBean(name = "listaKontPageBean")
@RequestScoped
public class ListaKontPageBean {

    public static final String GENERAL_MSG_ID = "listaKontForm:listaKont";

    public ListaKontPageBean() {
    }
    private String szukanyLogin = "";
    private String szukaneImie = "";
    private String szukaneNazwisko = "";
    private String szukanyEmail = "";

    public String getSzukanyEmail() {
        return szukanyEmail;
    }

    public void setSzukanyEmail(String szukanyEmail) {
        this.szukanyEmail = szukanyEmail;
    }

    public String getSzukaneImie() {
        return szukaneImie;
    }

    public void setSzukaneImie(String szukaneImie) {
        this.szukaneImie = szukaneImie;
    }

    public String getSzukaneNazwisko() {
        return szukaneNazwisko;
    }

    public void setSzukaneNazwisko(String szukaneNazwisko) {
        this.szukaneNazwisko = szukaneNazwisko;
    }

    public String getSzukanyLogin() {
        return szukanyLogin;
    }

    public void setSzukanyLogin(String szukanyLogin) {
        this.szukanyLogin = szukanyLogin;
    }

    public void odswiez() {
        initModel();
    }

    public void wyczysc() {
        szukanyLogin = "";
        szukaneImie = "";
        szukaneNazwisko = "";
        szukanyEmail = "";
    }

    @PostConstruct
    private void initModel() {
        konta = kontoSession.dopasujKonta(szukanyLogin, szukaneImie, szukaneNazwisko, szukanyEmail);
        kontaDataModel = new ListDataModel<Account>(konta);
    }
    @ManagedProperty(value = "#{kontoSession}")
    private KontoSession kontoSession;

    public void setKontoSession(KontoSession kontoSession) {
        this.kontoSession = kontoSession;
    }
    private List<Account> konta;
    private DataModel<Account> kontaDataModel;

    public DataModel<Account> getKontaDataModel() {
        return kontaDataModel;
    }

        public String edytujKonto() {
        return kontoSession.pobierzKontoDoEdycji(kontaDataModel.getRowData());
    }

    public String rozpocznijZmianeHasla() {
        return kontoSession.rozpocznijZmianeHasla(kontaDataModel.getRowData());
    }
}
