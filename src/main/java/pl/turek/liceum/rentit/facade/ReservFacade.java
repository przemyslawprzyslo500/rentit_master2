/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.turek.liceum.rentit.model.Reserv;
import pl.turek.liceum.rentit.model.Reserv_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.turek.liceum.rentit.model.Account;
import pl.turek.liceum.rentit.model.Equipment;
import pl.turek.liceum.rentit.model.ReservStatus;

/**
 *
 * @author miszcz
 */
@Stateless
public class ReservFacade extends AbstractFacade<Reserv> {

    @PersistenceContext(unitName = "pl.turek.liceum.rentit_RentIt_war_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservFacade() {
        super(Reserv.class);
    }

    public boolean isAccountIdEmpty(Reserv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Reserv> reserv = cq.from(Reserv.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(reserv, entity), cb.isNotNull(reserv.get(Reserv_.accountId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Account findAccountId(Reserv entity) {
        return this.getMergedEntity(entity).getAccountId();
    }

    public boolean isEquipmentIdEmpty(Reserv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Reserv> reserv = cq.from(Reserv.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(reserv, entity), cb.isNotNull(reserv.get(Reserv_.equipmentId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Equipment findEquipmentId(Reserv entity) {
        return this.getMergedEntity(entity).getEquipmentId();
    }

    public boolean isReservStatusIdEmpty(Reserv entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Reserv> reserv = cq.from(Reserv.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(reserv, entity), cb.isNotNull(reserv.get(Reserv_.reservStatusId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ReservStatus findReservStatusId(Reserv entity) {
        return this.getMergedEntity(entity).getReservStatusId();
    }
    
}
