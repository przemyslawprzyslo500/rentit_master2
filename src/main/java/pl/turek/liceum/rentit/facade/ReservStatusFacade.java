/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.turek.liceum.rentit.model.ReservStatus;
import pl.turek.liceum.rentit.model.ReservStatus_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.turek.liceum.rentit.model.Reserv;

/**
 *
 * @author miszcz
 */
@Stateless
public class ReservStatusFacade extends AbstractFacade<ReservStatus> {

    @PersistenceContext(unitName = "pl.turek.liceum.rentit_RentIt_war_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservStatusFacade() {
        super(ReservStatus.class);
    }

    public boolean isReservCollectionEmpty(ReservStatus entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ReservStatus> reservStatus = cq.from(ReservStatus.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(reservStatus, entity), cb.isNotEmpty(reservStatus.get(ReservStatus_.reservCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Reserv> findReservCollection(ReservStatus entity) {
        ReservStatus mergedEntity = this.getMergedEntity(entity);
        Collection<Reserv> reservCollection = mergedEntity.getReservCollection();
        reservCollection.size();
        return reservCollection;
    }
    
}
