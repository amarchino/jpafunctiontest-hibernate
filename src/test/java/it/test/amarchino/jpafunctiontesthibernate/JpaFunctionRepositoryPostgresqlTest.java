package it.test.amarchino.jpafunctiontesthibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
public class JpaFunctionRepositoryPostgresqlTest {

    @Container
    @ServiceConnection
    @SuppressWarnings("resource")
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:alpine")
        .withDatabaseName("integration-tests-db")
        .withUsername("sa")
        .withPassword("sa");

    @Autowired JpaFunctionRepository repository;

    @Test
    void testGenerateSeries() {
        List<Integer> series = repository.generateSeries(0, 10);
        assertThat(series).isNotEmpty();
        assertThat(series).contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    void testGenerateSeriesOffset() {
        List<Integer> series = repository.generateSeries(5, 7);
        assertThat(series).isNotEmpty();
        assertThat(series).contains(5, 6, 7);
        assertThat(series).doesNotContain(1, 2, 3, 8);
    }
}
