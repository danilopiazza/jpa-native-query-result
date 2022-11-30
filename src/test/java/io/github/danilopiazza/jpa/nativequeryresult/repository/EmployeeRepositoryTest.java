package io.github.danilopiazza.jpa.nativequeryresult.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.danilopiazza.jpa.nativequeryresult.entity.Employee;
import io.github.danilopiazza.jpa.nativequeryresult.faker.EmployeeFaker;
import io.github.danilopiazza.jpa.nativequeryresult.persistence.PersistenceTestSupport;

class EmployeeRepositoryTest {
    EmployeeFaker faker = new EmployeeFaker();
    List<Employee> employees = LongStream.rangeClosed(1, 10)
            .mapToObj(faker.employee()::employee)
            .collect(Collectors.toList());

    @ParameterizedTest
    @ValueSource(strings = { "test-eclipselink-unit", "test-hibernate-unit", "test-openjpa-unit" })
    void testFindAll(String persistenceUnitName) {
        PersistenceTestSupport.forName(persistenceUnitName).doInPersistenceUnit((em, tx) -> {
            tx.begin();
            employees.forEach(em::persist);
            EmployeeRepository repository = new EmployeeRepository(em);

            assertThat(repository.findAll()).containsExactlyElementsOf(employees);
        });
    }
}
