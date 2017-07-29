package pl.turek.liceum.rentit.ejb.endpoints;

import java.rmi.RemoteException;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import pl.turek.liceum.rentit.ejb.facades.KontoFacade;
import pl.turek.liceum.rentit.ejb.interceptor.LoggingInterceptor;
import pl.turek.liceum.rentit.ejb.managers.KontaManager;
import pl.turek.liceum.rentit.exception.AppBaseException;
import pl.turek.liceum.rentit.facade.AccountFacade;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.web.utils.KontoUtils;

/**
 *
 * @author java
 */
@Stateful
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class KontoEndpoint extends AbstractEndpoint implements SessionSynchronization {

//    @EJB
//    private KontaManager kontaManager;
    @EJB
//    private KontoFacade kontoFacade;
    private AccountFacade kontoFacade;

    @Resource
    SessionContext sctx;

//    @Inject
//    private AccountFacade accountFacade;
    private Account kontoStan;

//    public Account pobierzMojeKonto() {
//        return znajdzLogin(sctx.getCallerPrincipal().getName());
//        return accountFacade.findByName(sctx.getCallerPrincipal().getName());
//        return accountFacade.findByName(context.getCallerPrincipal().getName());
//    }

    public Account pobierzMojeKonto() {
//        return kontoFacade.findByName(sctx.getCallerPrincipal().getName());
        return kontoFacade.znajdzLogin(sctx.getCallerPrincipal().getName());
//        return accountFacade.findByName(context.getCallerPrincipal().getName());
    }

    public void utworzKonto(Account konto) throws AppBaseException {
        // Konto ma hasło jawne podane w formularzu, należy je przeliczyć na skrót
        konto.setPassword(KontoUtils.wyliczSkrotHasla(konto.getPassword()));
        kontoFacade.create(konto);
    }

    public void aktywujKonto(Account konto) {
        Account k = kontoFacade.find(konto.getId());
    }

    public void deaktywujKonto(Account konto) {
        Account k = kontoFacade.find(konto.getId());
    }

    public List<Account> pobierzWszystkieKonta() {
        return kontoFacade.findAll();
    }

    public List<Account> dopasujKonta(String loginWzor, String imieWzor, String nazwiskoWzor, String emailWzor) {
        return kontoFacade.dopasujKonta(loginWzor, imieWzor, nazwiskoWzor, emailWzor);
    }

    public Account znajdzLogin(String login) {
        return kontoFacade.znajdzLogin(login);
    }

    public Account pobierzKontoDoEdycji(Account konto) {
        kontoStan = znajdzLogin(konto.getLogin());
        return kontoStan;
    }

    public void zapiszKontoPoEdycji(Account konto) throws AppBaseException {
        if (null == kontoStan) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        if (!kontoStan.equals(konto)) {
            throw new IllegalArgumentException("Modyfikowane konto niezgodne z wczytanym");
        }
        // Przepisz te dane konta, które podlegają edycji (sa dostepne na formularzu)
        KontoUtils.przepiszDanePoEdycji(konto, kontoStan);
        //wykonej merge() na encji Konto, aby weszła ona w stan zarządzany przez obecny kontekst trwalości
        kontoFacade.edit(kontoStan);
        //usuń stan konta po zakończonej operacji - unika błędnego wielokrotnego wykonania
        kontoStan = null;
    }

    public void zmienMojeHaslo(String stare, String nowe) {
        Account mojeKonto = pobierzMojeKonto();
        if (!mojeKonto.getPassword().equals(KontoUtils.wyliczSkrotHasla(stare))) {
            throw new IllegalArgumentException("Podane dotychczasowe hasło nie zgadza się");
        }
        mojeKonto.setPassword(KontoUtils.wyliczSkrotHasla(nowe));
    }

    public void zmienHaslo(Account konto, String haslo) {
        Account k = kontoFacade.find(konto.getId());
        k.setPassword(KontoUtils.wyliczSkrotHasla(haslo));
    }

}
