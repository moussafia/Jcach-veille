package ma.youcode.cach;

import ma.youcode.cach.entities.Employes;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.cache.Cache;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employe", produces = "application/Json")
public class EmployeController {

    private static final Log log = LogFactory.getLog(EmployeController.class);

    private Cache<Integer, Employes> cacheEmploye;

    public EmployeController(Cache<Integer, Employes> cacheEmploye) {

        this.cacheEmploye = cacheEmploye;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employes get(@PathVariable("id") Integer id){
        log.info("get for id" + id);
        return cacheEmploye.get(id);
    }
    @RequestMapping(method =RequestMethod.POST , consumes = "application/json")
    public boolean post(@RequestBody List<Employes> employes){
//        log.info("get for id" + employes);
//        cacheEmploye.put(employes.getId(), employes);
      Map<Integer, Employes> map = employes
              .stream()
              .collect(Collectors.toMap(Employes::getId,entry->entry));
      cacheEmploye.putAll(map);
        return true;
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer, Employes> getAll(@RequestParam("keys") Set<Integer> key){
        return cacheEmploye.getAll(key);
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

