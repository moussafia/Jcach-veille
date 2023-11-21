package ma.youcode.annotation.Repository;

import ma.youcode.annotation.entities.Employes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeInter extends JpaRepository<Employes, Integer> {
    @Query(value = "DELETE FROM Employes WHERE id IN :keys")
    void deleteAll(@Param("keys") Set<Integer> keys);
}
