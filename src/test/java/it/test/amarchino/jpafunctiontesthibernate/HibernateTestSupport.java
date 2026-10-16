package it.test.amarchino.jpafunctiontesthibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibernateTestSupport {

    private HibernateTestSupport() {
    }

    public static SessionFactory createSessionFactory(
            String jdbcUrl,
            String username,
            String password) {

        Configuration configuration = new Configuration();

        configuration.setProperties(new java.util.Properties());
        Map<String, String> settings = Map.of(
            "hibernate.connection.driver_class",
                jdbcUrl.startsWith("jdbc:postgresql:")
                    ? "org.postgresql.Driver"
                    : "org.h2.Driver",
            "hibernate.connection.url", jdbcUrl,
            "hibernate.connection.username", username,
            "hibernate.connection.password", password,
            "hibernate.dialect", jdbcUrl.startsWith("jdbc:postgresql:")
                ? "org.hibernate.dialect.PostgreSQLDialect"
                : "org.hibernate.dialect.H2Dialect",
            "hibernate.hbm2ddl.auto", "create-drop",
            "hibernate.show_sql", "true",
            "hibernate.format_sql", "true"
        );

        settings.forEach(configuration::setProperty);

        return configuration.buildSessionFactory();
    }

    public static List<Integer> generateSeries(SessionFactory sessionFactory, int min, int max) {
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery("SELECT a FROM generate_series(CAST(:min AS INTEGER), CAST(:max AS INTEGER)) a", Integer.class);
            query.setParameter("min", min);
            query.setParameter("max", max);
            return query.getResultList();
        }
    }
}
