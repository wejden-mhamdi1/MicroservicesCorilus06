package tn.corilus.corilus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.corilus.corilus.Entities.RecordFooter;
import tn.corilus.corilus.Entities.Segment;

public interface RecordFooterRepository extends JpaRepository<RecordFooter,Integer> {
}
