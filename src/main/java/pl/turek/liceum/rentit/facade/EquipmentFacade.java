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
import pl.turek.liceum.rentit.model.Equipment;
import pl.turek.liceum.rentit.model.Equipment_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.turek.liceum.rentit.model.LicenseType;
import pl.turek.liceum.rentit.model.UsePlace;
import pl.turek.liceum.rentit.model.Reserv;

/**
 *
 * @author miszcz
 */
@Stateless
public class EquipmentFacade extends AbstractFacade<Equipment> {

    @PersistenceContext(unitName = "pl.turek.liceum.rentit_RentIt_war_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipmentFacade() {
        super(Equipment.class);
    }

    public boolean isLicenseTypeIdEmpty(Equipment entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipment> equipment = cq.from(Equipment.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipment, entity), cb.isNotNull(equipment.get(Equipment_.licenseTypeId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public LicenseType findLicenseTypeId(Equipment entity) {
        return this.getMergedEntity(entity).getLicenseTypeId();
    }

    public boolean isUsePlaceIdEmpty(Equipment entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipment> equipment = cq.from(Equipment.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipment, entity), cb.isNotNull(equipment.get(Equipment_.usePlaceId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public UsePlace findUsePlaceId(Equipment entity) {
        return this.getMergedEntity(entity).getUsePlaceId();
    }

    public boolean isReservCollectionEmpty(Equipment entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Equipment> equipment = cq.from(Equipment.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(equipment, entity), cb.isNotEmpty(equipment.get(Equipment_.reservCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Reserv> findReservCollection(Equipment entity) {
        Equipment mergedEntity = this.getMergedEntity(entity);
        Collection<Reserv> reservCollection = mergedEntity.getReservCollection();
        reservCollection.size();
        return reservCollection;
    }
    
}
