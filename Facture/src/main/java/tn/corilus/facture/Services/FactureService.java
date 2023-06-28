package tn.corilus.facture.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.corilus.facture.Repository.FactureRepository;
import tn.corilus.facture.Entities.Facture;
import tn.corilus.facture.Services.FactureServiceIMPL;
import java.util.List;
@Service
public class FactureService implements FactureServiceIMPL{
    @Autowired
    FactureRepository factureRepository;
    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);

    }

    @Override
    public Facture updateFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facture> getAllFacture() {
        return factureRepository.findAll();
    }

    @Override
    public Facture createFacture(Facture facture) {
        return factureRepository.save(facture);
    }
}