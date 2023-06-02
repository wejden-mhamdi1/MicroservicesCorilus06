package tn.corilus.corilus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.corilus.corilus.Entities.Erreur;
import tn.corilus.corilus.Entities.Segment;

import java.util.List;

@Repository
public interface ErreurRepository extends JpaRepository<Erreur,Long> {
    List<Erreur> findByFileId(int fileId);
    List<Erreur> findByZoneId(int zoneId);
}
