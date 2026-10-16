package it.test.amarchino.jpafunctiontesthibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Testcontainers
public class JpaFunctionRepositoryPostgresqlTest {

    @Container
    static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:alpine")
        .withDatabaseName("hqltest")
        .withUsername("test")
        .withPassword("test");

    @Test
    void testGenerateSeries() {
        try (SessionFactory sessionFactory = HibernateTestSupport.createSessionFactory(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword())) {

            List<Integer> series = HibernateTestSupport.generateSeries(sessionFactory, 0, 10);
            assertThat(series).isNotEmpty();
            assertThat(series).contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        }
    }

    @Test
    void testGenerateSeriesOffset() {
        try (SessionFactory sessionFactory = HibernateTestSupport.createSessionFactory(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword())) {

            List<Integer> series = HibernateTestSupport.generateSeries(sessionFactory, 5, 7);
            assertThat(series).isNotEmpty();
            assertThat(series).contains(5, 6, 7);
            assertThat(series).doesNotContain(1, 2, 3, 8);
        }
    }

}
