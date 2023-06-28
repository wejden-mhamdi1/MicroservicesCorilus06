package tn.corilus.facture.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.corilus.facture.Entities.Facture;

public interface FactureRepository extends JpaRepository<Facture,Long> {
}
