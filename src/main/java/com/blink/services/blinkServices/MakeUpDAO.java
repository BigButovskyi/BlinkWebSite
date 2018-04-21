package com.blink.services.blinkServices;

import com.blink.Entities.MakeUp;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MakeUpDAO implements MakeUpDAOInterface{
    @PersistenceContext
    private EntityManager entityManager;

    //Adding new makeUp service
    public void addService(MakeUp makeUp) {
        entityManager.persist(makeUp);
    }
}
