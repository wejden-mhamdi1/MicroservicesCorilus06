package tn.corilus.corilus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.corilus.corilus.Entities.Segment;

import java.util.List;

@Repository
public interface SegmentRepository extends JpaRepository<Segment,Integer> {
    List<Segment> findByFileId(int fileId);
}
