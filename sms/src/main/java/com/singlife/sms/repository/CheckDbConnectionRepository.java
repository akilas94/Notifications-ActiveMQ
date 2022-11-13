package com.singlife.sms.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CheckDbConnectionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckDbConnectionRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public boolean checkDbConnection() {
        try {
            entityManager.createNativeQuery("select 1 from dual").getSingleResult();
            return true;
        } catch (final Exception ex) {
            LOGGER.error("CheckDbConnectionRepository > Database is down", ex);
            return false;
        }
    }
}
