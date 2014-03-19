package ca.ulaval.ift6002.m2.factory.hibernate;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.drug.Din;
import ca.ulaval.ift6002.m2.domain.drug.Drug;
import ca.ulaval.ift6002.m2.domain.drug.DrugFactory;
import ca.ulaval.ift6002.m2.infrastructure.persistence.hibernate.entities.DrugHibernate;

public class DrugHibernateFactory implements DrugFactory {

    @Override
    public Drug create(Din din, String brandName) {
        return new DrugHibernate(din, brandName);
    }

    @Override
    public Drug create(Din din, String brandName, String descriptor) {
        return new DrugHibernate(din, brandName, descriptor);
    }

    @Override
    public Drug create(Din din, String brandName, String descriptor, List<Din> interactingDins) {
        // TODO
        return new DrugHibernate(din, brandName, descriptor);
    }

    @Override
    public Drug create(String brandName) {
        // TODO
        return new DrugHibernate(null, brandName, null);
    }

}
