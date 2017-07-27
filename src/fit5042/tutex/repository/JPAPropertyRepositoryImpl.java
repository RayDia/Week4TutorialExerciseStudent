/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.tutex.repository;

import fit5042.tutex.repository.entities.Property;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author BladeRay
 */

public class JPAPropertyRepositoryImpl implements PropertyRepository {
    private static final String PERSISTENCE_UNIT = "RealEstateAgencyPU";
    
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    
    protected JPAPropertyRepositoryImpl()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @Override
    public void addProperty(Property property) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.persist(property);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Property searchPropertyById(int id) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Property property = this.entityManager.find(Property.class, id);       
        return property;
    }

    @Override
    public List<Property> getAllProperties() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.entityManager.createNamedQuery(Property.FIND_ALL).getResultList();
    }

    @Override
    public void removeProperty(int propertyId) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.remove(this.searchPropertyById(propertyId));
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void editProperty(Property property) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        EntityTransaction transaction = this.entityManager.getTransaction();
        
        try {
            transaction.begin();
            this.entityManager.merge(property);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void close() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.exit(0);
    }
    
}
