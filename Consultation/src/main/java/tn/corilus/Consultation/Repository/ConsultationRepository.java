package tn.corilus.Consultation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.corilus.Consultation.Entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
