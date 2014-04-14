package ca.ulaval.ift6002.m2.acceptance.runners;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;

import ca.ulaval.ift6002.m2.JettyServer;
import ca.ulaval.ift6002.m2.configuration.factory.HibernateFactoryConfiguration;
import ca.ulaval.ift6002.m2.configuration.persistence.HibernatePersistanceConfiguration;
import ca.ulaval.ift6002.m2.contexts.DemoDrugRepositoryFiller;
import ca.ulaval.ift6002.m2.contexts.DemoPatientRepositoryFiller;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerFactoryProvider;
import ca.ulaval.ift6002.m2.infrastructure.persistence.provider.EntityManagerProvider;

public class JettyTestRunner {

    public static final int JETTY_TEST_PORT = 8181;
    private JettyServer server;
    private EntityManager entityManager;

    @BeforeStories
    public void startJetty() throws Exception {
        new HibernateFactoryConfiguration().configure();
        new HibernatePersistanceConfiguration().configure();

        entityManager = setUpEntityManager();

        entityManager.getTransaction().begin();
        // fillDrugRepository();
        // fillPatientRepository();
        entityManager.getTransaction().commit();

        server = new JettyServer(JETTY_TEST_PORT);
        server.start();
    }

    @AfterStories
    public void stopJetty() throws Exception {
        closeEntityManager(entityManager);
    }

    private EntityManager setUpEntityManager() {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManagerProvider.setEntityManager(entityManager);

        return entityManager;
    }

    private void fillDrugRepository() {
        new DemoDrugRepositoryFiller().fill();
    }

    private void fillPatientRepository() {
        new DemoPatientRepositoryFiller().fill();
    }

    private void closeEntityManager(EntityManager entityManager) {
        EntityManagerProvider.clearEntityManager();
        entityManager.close();
        EntityManagerFactoryProvider.closeFactory();
    }
}
