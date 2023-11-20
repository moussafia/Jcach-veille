package ma.youcode.cach;

import javax.cache.configuration.Factory;

public class EmployeesCacheLoaderFactory implements Factory<EmployeesCacheLoader> {
    @Override
    public EmployeesCacheLoader create() {
        return ApplicationContextSingleton
                .getApplicationContext()
                .getBean(EmployeesCacheLoader.class);
    }
}
