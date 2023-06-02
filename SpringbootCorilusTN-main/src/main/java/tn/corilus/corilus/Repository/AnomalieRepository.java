package tn.corilus.corilus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.corilus.corilus.Entities.Anomalie;
import tn.corilus.corilus.Entities.Zone;

import java.util.List;

@Repository
public interface AnomalieRepository extends JpaRepository<Anomalie,Long> {
       // List<Anomalie> findByDonneeIn(List<String> donnees);
        @Query("SELECT a FROM Anomalie a JOIN Zone z ON a.codeError = z.data WHERE z = :zone")
        List<Anomalie> findByZone(@Param("zone") Zone zone);

        @Query("SELECT a FROM Anomalie a WHERE a.codeError = :codeError")
        Anomalie findByCode( String codeError);
        @Query("SELECT e FROM Anomalie e WHERE e.frenshDescription = :frenshDescription")
        Anomalie findByFrenchDescription(String frenshDescription);

//    @Query("SELECT e FROM ErrorCode e WHERE e.code = :code")
//    ErrorCode findByCode(String code);
//
//}


}
