package io.github.danilopiazza.jpa.nativequeryresult.faker.provider;

import net.datafaker.providers.base.BaseProviders;
import net.datafaker.providers.base.ProviderRegistration;

public interface EmployeeProviders extends BaseProviders {
    default EmployeeProvider employee() {
        return getProvider(EmployeeProvider.class, EmployeeProvider::new);
    }
}
