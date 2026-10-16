package it.test.amarchino.jpafunctiontesthibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class JpaFunctionRepositoryH2Tests {

    @Test
    void testGenerateSeries() {
        try (SessionFactory sessionFactory = HibernateTestSupport.createSessionFactory(
                "jdbc:h2:mem:hqltest;DB_CLOSE_DELAY=-1",
                "sa",
                "")) {

            List<Integer> series = HibernateTestSupport.generateSeries(sessionFactory, 0, 10);
            assertThat(series).isNotEmpty();
            assertThat(series).contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        }
    }

    @Test
    void testGenerateSeriesOffset() {
        try (SessionFactory sessionFactory = HibernateTestSupport.createSessionFactory(
                     "jdbc:h2:mem:hqltest;DB_CLOSE_DELAY=-1",
                     "sa",
                     "")) {

            List<Integer> series = HibernateTestSupport.generateSeries(sessionFactory, 5, 7);
            assertThat(series).isNotEmpty();
            assertThat(series).contains(5, 6, 7);
            assertThat(series).doesNotContain(1, 2, 3, 8);
        }
    }
}
