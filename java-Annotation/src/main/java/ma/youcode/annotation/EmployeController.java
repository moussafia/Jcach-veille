package ma.youcode.annotation;

import ma.youcode.annotation.Repository.EmployeInter;
import ma.youcode.annotation.entities.Employes;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import javax.cache.Cache;
import javax.cache.annotation.*;
@RestController
@RequestMapping(value = "/employe", produces = "application/Json")
@EnableCaching
@CacheDefaults(cacheName = "employe")
public class EmployeController {

    private static final Log log = LogFactory.getLog(EmployeController.class);

    private Cache<Integer, Employes> cacheEmploye;
    private final EmployeInter employeInter;

    public EmployeController(Cache<Integer, Employes> cacheEmploye,
                             EmployeInter employeInter) {

        this.cacheEmploye = cacheEmploye;
        this.employeInter = employeInter;
    }

    @RequestMapping(value = "/{id}", method =RequestMethod.POST , consumes = "application/json")
    @CachePut
    public boolean post(@PathVariable
                        @CacheKey
                        Integer id,
                        @RequestBody
                        @CacheValue
                        Employes employes){
        employes.setId(id);
        employeInter.save(employes);
        return true;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CacheResult
    public Employes get(@PathVariable("id") Integer id){
        log.info("get for id" + id);
        return employeInter.findById(id).get();
    }
    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    @CacheRemoveAll
    public void deleteAll() throws Exception{
        employeInter.deleteAll();
    }
}
//putAll accept map as a parameter and return void
//getAll accept set as parameter and return map
//remove accept key as parameter and return boolean
//clear no parameter and return boolean
//Pessimistic looking : most method on the cache interface take a pessimistic approach
// => this guarantees consistency when the cache is accessed by multiple thread
//pessimistic approach : Lock key + mutate value + unlock key

//cache.close() not mean data in cache is deleted
//destroy cache and delete the data : cache.destroy()

//Life cycle of cache: event and listeners
//cache entry listeners => created + modified + removed + expired
//put, putAll etc some method are blocked all threads until any mutation of the data have been completed

//we shut down app + caches lost =>store in memory in same JVM

