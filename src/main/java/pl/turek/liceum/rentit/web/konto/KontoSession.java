package pl.turek.liceum.rentit.web.konto;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.turek.liceum.rentit.ejb.endpoints.KontoEndpoint;
import pl.turek.liceum.rentit.exception.AppBaseException;
import pl.turek.liceum.rentit.exception.KontoException;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.web.utils.ContextUtils;

/**
 *
 * @author java
 */
@ManagedBean(name = "kontoSession")
@SessionScoped
public class KontoSession implements Serializable {

    @EJB
    private KontoEndpoint kontoEndpoint;

    public String resetujSesje() {
        ContextUtils.invalidateSession();
        /* Poprawne zakończenie sesji wymaga wymuszenia nowego żądania na przeglądarce, stąd metoda ta
         * prowadzi do przypadku nawigacji z elementem <redirect />.
         * UWAGA: integracja logowania typu BASIC z przeglądarką powoduje, że czasem mimo to "wylogowanie" jest nieskuteczne - 
         * powstaje nowa sesja już zalogowanego użytkownika. Dlatego bezpieczniej jest stosować uwierzytelnianie przez formularz (FORM).
         */
        return "cancelAction";
    }

    public String getMojLogin() {
        return ContextUtils.getUserName();
    }
    private Account kontoEdytuj;
    private Account kontoZmienHaslo;

    public Account getKontoZmienHaslo() {
        return kontoZmienHaslo;
    }

    public Account getKontoEdytuj() {
        return kontoEdytuj;
    }

    public KontoSession() {
    }

    public String rozpocznijZmianeHasla(Account konto) {
        this.kontoZmienHaslo = konto;
        return "changePassword";
    }

    public void aktywujKonto(Account Konto) {
        kontoEndpoint.aktywujKonto(Konto);
        ContextUtils.emitSuccessMessage(ListaKontPageBean.GENERAL_MSG_ID);
    }

    public void deaktywujKonto(Account Konto) {
        kontoEndpoint.deaktywujKonto(Konto);
        ContextUtils.emitSuccessMessage(ListaKontPageBean.GENERAL_MSG_ID);
    }

    public String pobierzKontoDoEdycji(Account Konto) {
        kontoEdytuj = kontoEndpoint.pobierzKontoDoEdycji(Konto);
        return "editAccount";
    }

    public String zapiszKontoPoEdycji(Account Konto) throws AppBaseException {
        kontoEndpoint.zapiszKontoPoEdycji(Konto);
        return "success";
    }

    public String zmienHasloKonta(String haslo) {
        kontoEndpoint.zmienHaslo(kontoZmienHaslo, haslo);
        return "success";
    }

    public String zmienMojeHaslo(String stare, String nowe) {
        kontoEndpoint.zmienMojeHaslo(stare, nowe);
        return "success";
    }

    public List<Account> pobierzWszystkieKonta() {
        return kontoEndpoint.pobierzWszystkieKonta();
    }

    public List<Account> dopasujKonta(String loginWzor, String imieWzor, String nazwiskoWzor, String emailWzor) {
        return kontoEndpoint.dopasujKonta(loginWzor, imieWzor, nazwiskoWzor, emailWzor);
    }

    public Account pobierzMojeKonto() {
        return kontoEndpoint.pobierzMojeKonto();
    }
}
