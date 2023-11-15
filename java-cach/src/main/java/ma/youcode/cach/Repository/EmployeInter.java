package ma.youcode.cach.Repository;

import ma.youcode.cach.entities.Employes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Repository
public interface EmployeInter extends  CrudRepository<Employes, Integer> {

    @Query("delete from Employes e where e.id IN :keys")
    void deleteAll(@Param("keys") Set<Integer> keys);
}
