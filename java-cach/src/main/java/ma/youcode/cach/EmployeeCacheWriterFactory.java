package ma.youcode.cach;

import ma.youcode.cach.entities.Employes;

import javax.cache.configuration.Factory;

public class EmployeeCacheWriterFactory implements Factory<EmployesCachesWriter> {
    @Override
    public EmployesCachesWriter create() {
        return ApplicationContextSingleton
                .getApplicationContext()
                .getBean(EmployesCachesWriter.class);
    }
}
