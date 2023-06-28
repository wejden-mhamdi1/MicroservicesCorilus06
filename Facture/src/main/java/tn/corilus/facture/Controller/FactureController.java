package tn.corilus.facture.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.corilus.facture.Entities.Facture;
import tn.corilus.facture.Repository.FactureRepository;
import tn.corilus.facture.Services.FactureServiceIMPL;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("facture")
public class FactureController {
    @Autowired
    FactureServiceIMPL factureService;
    @Autowired
    FactureRepository factureRepository;

    @DeleteMapping("/delete/{id}")
    public void deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
    }

    @GetMapping("/get/{id}")
    public Facture getConsultationById(@PathVariable Long id) {
        return factureService.getFactureById(id);
    }

    @GetMapping("/all")
    public List<Facture> getAllfacture() {
        return factureService.getAllFacture();
    }

    @PutMapping("/put/{id}")
    public Facture updatefacture(@RequestBody Facture facture, @PathVariable Long id) {
        Optional<Facture> existingfacturation = factureRepository.findById(id);
        if (existingfacturation.isPresent()) {
            facture.setId(id);
            return factureService.updateFacture(facture);
        } else {
            return null;
        }
    }

    @PostMapping("/add")
    public Facture createConsultation(@RequestBody Facture facture) {
        return factureService.createFacture(facture);
    }
}
