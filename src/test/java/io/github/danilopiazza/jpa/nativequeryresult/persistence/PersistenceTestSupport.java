package io.github.danilopiazza.jpa.nativequeryresult.persistence;

import java.util.function.BiConsumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceTestSupport implements AutoCloseable {
    private final EntityManagerFactory emf;

    public static PersistenceTestSupport forName(String persistenceUnitName) {
        return new PersistenceTestSupport(persistenceUnitName);
    }

    private PersistenceTestSupport(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public void doInPersistenceUnit(BiConsumer<EntityManager, EntityTransaction> callback) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                callback.accept(em, tx);
            } finally {
                if (tx.isActive()) tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
