package pl.turek.liceum.rentit.exception;

import pl.turek.liceum.rentit.model.Account;


/**
 *
 */
public class KontoException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.konto.db.constraint.uniq.login";

    private KontoException(String message) {
        super(message);
    }

    private KontoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Account konto;

    public Account getKonto() {
        return konto;
    }

    static public KontoException createWithDbCheckConstraintKey(Account konto, Throwable cause) {
        KontoException ze = new KontoException(KEY_DB_CONSTRAINT, cause);
        ze.konto=konto;
        return ze;
    }
}
