package ma.youcode.annotation;

import ma.youcode.annotation.Repository.EmployeInter;
import ma.youcode.annotation.entities.Employes;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.cache.integration.CacheLoader;
import javax.cache.integration.CacheLoaderException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class EmployeesCacheLoader implements CacheLoader<Integer, Employes> {
    private EmployeInter employeInter;
    private static final Log LOG = LogFactory.getLog(EmployeesCacheLoader.class);
    public EmployeesCacheLoader(EmployeInter employeInter) {
        this.employeInter = employeInter;
    }

    @Override
    public Employes load(Integer integer) throws CacheLoaderException {
        LOG.info("load key "+ integer);
        return employeInter.findById(integer).get();
    }

    @Override
    public Map<Integer, Employes> loadAll(Iterable<? extends Integer> iterable) throws CacheLoaderException {
        LOG.info("load key "+ iterable);
        Iterable<Employes> employes = employeInter.findAllById((Iterable<Integer>) iterable);
        return StreamSupport.stream(employes.spliterator(),false)
                .collect(Collectors.toMap(Employes::getId, Function.identity()));
    }
}
