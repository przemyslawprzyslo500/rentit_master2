package pl.turek.liceum.rentit.ejb.facades;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.exceptions.DatabaseException;
import pl.turek.liceum.rentit.ejb.facades.AbstractFacade;
import pl.turek.liceum.rentit.exception.AppBaseException;
import pl.turek.liceum.rentit.exception.KontoException;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.model.Account_;

@Stateless
@LocalBean
//@Interceptors({LoggingInterceptor.class, PerformanceInterceptor.class})
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class KontoFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "pl.turek.liceum.rentit_RentIt_war_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KontoFacade() {
        super(Account.class);
    }

    @Override
    public void create(Account entity) throws AppBaseException {
        try {
            super.create(entity);
            em.flush();
        } catch (PersistenceException ex) {
            if (ex.getCause() instanceof DatabaseException && ex.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw KontoException.createWithDbCheckConstraintKey(entity, ex);
            } else {
                throw ex;
            }
        }
    }

    public Account znajdzLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get(Account_.login), login));
        TypedQuery<Account> tq = em.createQuery(query);
        return tq.getSingleResult();
    }

    public List<Account> dopasujKonta(String loginWzor, String imieWzor, String nazwiskoWzor, String emailWzor) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        Predicate criteria = cb.conjunction();
        if (null != loginWzor && !(loginWzor.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.login), '%' + loginWzor + '%'));
        }
        if (null != imieWzor && !(imieWzor.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.name), '%' + imieWzor + '%'));
        }
        if (null != nazwiskoWzor && !(nazwiskoWzor.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.surname), '%' + nazwiskoWzor + '%'));
        }
        if (null != emailWzor && !(emailWzor.isEmpty())) {
            criteria = cb.and(criteria, cb.like(from.get(Account_.email), '%' + emailWzor + '%'));
        }

        query = query.where(criteria);
        TypedQuery<Account> tq = em.createQuery(query);
        return tq.getResultList();
    }
}
