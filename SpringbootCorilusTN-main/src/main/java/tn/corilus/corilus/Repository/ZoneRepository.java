package tn.corilus.corilus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.corilus.corilus.Entities.Segment;
import tn.corilus.corilus.Entities.Zone;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone,Integer> {
    List<Zone> findByFileId(int fileId);
}
