package ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public abstract class HibernateRepository<T> {

    private final EntityManagerProvider entityManagerProvider;
    private final Class<T> classType;

    public HibernateRepository(Class<T> classType) {
        this(new EntityManagerProvider(), classType);
    }

    public HibernateRepository(EntityManagerProvider entityManagerProvider, Class<T> classType) {
        this.entityManagerProvider = entityManagerProvider;
        this.classType = classType;
    }

    protected T find(Object value) {
        T element = getEntityManager().find(classType, value);

        if (element == null) {
            throw new NoSuchElementException("There is no element with value: " + value);
        }

        return element;
    }

    protected Collection<T> merge(Collection<T> elements) {
        Collection<T> mergeElements = new ArrayList<T>();

        beginTransaction();

        for (T element : elements) {
            T mergeElement = getEntityManager().merge(element);
            mergeElements.add(mergeElement);
        }

        commitTransaction();

        return mergeElements;
    }

    protected T merge(T element) {
        beginTransaction();

        T mergeElement = getEntityManager().merge(element);

        commitTransaction();

        return mergeElement;
    }

    protected void persist(Collection<T> elements) {
        beginTransaction();

        for (T element : elements) {
            getEntityManager().persist(element);
        }

        commitTransaction();
    }

    protected TypedQuery<T> createQuery(String query) {
        return getEntityManager().createQuery(query, classType);
    }

    private void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    private void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }

    private EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }

}
