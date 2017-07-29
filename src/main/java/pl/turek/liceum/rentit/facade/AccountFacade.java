/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.facade;

import java.util.Collection;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.model.Account_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.turek.liceum.rentit.model.Reserv;

/**
 *
 * @author miszcz
 */
@Stateless
//@RolesAllowed("admin")
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "pl.turek.liceum.rentit_RentIt_war_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Account findByName(String name) {
        return (Account) em.createQuery("SELECT a FROM ACCOUNT a where a.login = :login")
                .setParameter("login", name).getSingleResult();
    }
    
    public AccountFacade() {
        super(Account.class);
    }

    public boolean isReservCollectionEmpty(Account entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Account> account = cq.from(Account.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(account, entity), cb.isNotEmpty(account.get(Account_.reservCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Reserv> findReservCollection(Account entity) {
        Account mergedEntity = this.getMergedEntity(entity);
        Collection<Reserv> reservCollection = mergedEntity.getReservCollection();
        reservCollection.size();
        return reservCollection;
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

    public Account znajdzLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> from = query.from(Account.class);
        query = query.select(from);
        query = query.where(cb.equal(from.get(Account_.login), login));
        TypedQuery<Account> tq = em.createQuery(query);
        return tq.getSingleResult();
    }
}
