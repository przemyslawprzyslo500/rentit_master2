
package pl.turek.liceum.rentit.web.utils;

import pl.turek.liceum.rentit.model.Account;
/**
 *
 */
public class KontoUtils {
    
    /** Przepisuje do przekazanej encji dane z formularza edycji konta.
     * Uwzględnione są klasy rozszerzające Konto (Administrator, Pracownik, Klient), przy czym tylko dane występujące na formularzu sa przepisywane. Pomijane są: login, hasło, id, wersja.
     * 
     * @param zrodlo encja zawierająca dane z formularza edycji
     * @param cel encja docelowa
     */
    public static void przepiszDanePoEdycji(Account zrodlo, Account cel) {
        
        if (null == zrodlo || null == cel) return;
        
        cel.setName(zrodlo.getName());
        cel.setSurname(zrodlo.getSurname());
        cel.setEmail(zrodlo.getEmail());
        cel.setPhone(zrodlo.getPhone());
        
    }
    
    public static String wyliczSkrotHasla(String hasloJawne){
        //TODO: wstawić algorytm skrótu hasła
//        String output = Hashing.sha256().hashString(hasloJawne, Charsets.UTF_8)
        return hasloJawne;
    }

}
