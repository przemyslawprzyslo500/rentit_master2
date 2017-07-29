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
import pl.turek.liceum.rentit.model.LicenseType;
import pl.turek.liceum.rentit.model.LicenseType_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pl.turek.liceum.rentit.model.Equipment;

/**
 *
 * @author miszcz
 */
@Stateless
public class LicenseTypeFacade extends AbstractFacade<LicenseType> {

    @PersistenceContext(unitName = "pl.turek.liceum.rentit_RentIt_war_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenseTypeFacade() {
        super(LicenseType.class);
    }

    public boolean isEquipmentCollectionEmpty(LicenseType entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LicenseType> licenseType = cq.from(LicenseType.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(licenseType, entity), cb.isNotEmpty(licenseType.get(LicenseType_.equipmentCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Equipment> findEquipmentCollection(LicenseType entity) {
        LicenseType mergedEntity = this.getMergedEntity(entity);
        Collection<Equipment> equipmentCollection = mergedEntity.getEquipmentCollection();
        equipmentCollection.size();
        return equipmentCollection;
    }
    
}
