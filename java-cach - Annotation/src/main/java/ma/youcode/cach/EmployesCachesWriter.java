package ma.youcode.cach;

import ma.youcode.cach.Repository.EmployeInter;
import ma.youcode.cach.entities.Employes;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.integration.CacheWriter;
import javax.cache.integration.CacheWriterException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployesCachesWriter implements CacheWriter<Integer, Employes> {
    private EmployeInter employeInter;
    private static final Log log = LogFactory.getLog(EmployesCachesWriter.class);

    public EmployesCachesWriter(EmployeInter employeInter) {
        this.employeInter = employeInter;
    }

    @Override
    public void write(Cache.Entry<? extends Integer, ? extends Employes> entry) throws CacheWriterException {
        log.info("write called with entry "+ entry);
        employeInter.save(entry.getValue());
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Integer, ? extends Employes>> collection) throws CacheWriterException {
        log.info("writeAll called with entry "+ collection);
        Set<Employes> employes = collection
                .stream()
                .map(Cache.Entry::getValue)
                .collect(Collectors.toSet());
        employeInter.saveAll(employes);
    }
    @Override
    public void delete(Object o) throws CacheWriterException {
        log.info("delete called with entry "+ o);
        employeInter.deleteById((Integer) o);
    }

    @Override
    public void deleteAll(Collection<?> collection) throws CacheWriterException {
        log.info("deleteAll called with entry "+ collection);
        Set<Integer> keys = collection
                .stream()
                .map(Object::toString)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        employeInter.deleteAll(keys);
    }
}
