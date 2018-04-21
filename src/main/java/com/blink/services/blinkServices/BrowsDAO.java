package com.blink.services.blinkServices;

import com.blink.Entities.Brows;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BrowsDAO implements BrowsDAOInterface{

    @PersistenceContext
    private EntityManager entityManager;

    //Adding new nails service
    public void addService(Brows brows) {
        entityManager.persist(brows);
    }
}