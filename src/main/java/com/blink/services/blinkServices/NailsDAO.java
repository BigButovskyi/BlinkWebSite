package com.blink.services.blinkServices;

import com.blink.Entities.Nails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NailsDAO implements NailsDAOInterface{

    @PersistenceContext
    private EntityManager entityManager;

    //Adding new nails service
    public void addService(Nails nails) {
        entityManager.persist(nails);
    }
}
