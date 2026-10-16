package it.test.amarchino.jpafunctiontesthibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class JpaFunctionRepositoryTests {
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
