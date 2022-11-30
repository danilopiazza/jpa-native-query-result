package io.github.danilopiazza.jpa.nativequeryresult.faker.provider;

import java.math.BigDecimal;

import io.github.danilopiazza.jpa.nativequeryresult.entity.Employee;
import net.datafaker.providers.base.AbstractProvider;

public class EmployeeProvider extends AbstractProvider<EmployeeProviders> {
    public EmployeeProvider(EmployeeProviders faker) {
        super(faker);
    }

    public Employee employee(long id) {
        return Employee.builder()
                .id(id)
                .username(possiblyNull(faker.name().username()))
                .firstName(possiblyNull(faker.name().firstName()))
                .lastName(possiblyNull(faker.name().lastName()))
                .email(possiblyNull(faker.internet().safeEmailAddress()))
                .birthDate(possiblyNull(faker.date().birthday()))
                .salary(possiblyNull(BigDecimal.valueOf(faker.number().randomDouble(2, 1000, 5000))))
                .build();
    }

    private <T> T possiblyNull(T value) {
        return faker.random().nextInt(100) < 25 ? null : value;
    }
}
