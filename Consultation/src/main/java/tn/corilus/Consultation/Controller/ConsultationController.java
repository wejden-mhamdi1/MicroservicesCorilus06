package tn.corilus.Consultation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.corilus.Consultation.Entities.Consultation;
import tn.corilus.Consultation.Repository.ConsultationRepository;
import tn.corilus.Consultation.Services.ConsultationService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConsultationController {
    @Autowired
    ConsultationService consultationService;
    @Autowired
    ConsultationRepository consultationRepository;
    @DeleteMapping("/delete/{id}")
    public void deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);    }
    @GetMapping("/get/{id}")
    public Consultation getConsultationById(@PathVariable Long id) {
        return consultationService.getConsultationById(id);
    }
    @GetMapping("/all")
    public List<Consultation> getAllConsultations() {
        return consultationService.getAllConsultations();
    }
    @PutMapping("/put/{id}")
    public Consultation updateConsultation(@RequestBody Consultation consultation, @PathVariable Long id) {
        Optional<Consultation> existingConsultation = consultationRepository.findById(id);
        if (existingConsultation.isPresent()) {
            consultation.setId(id);
            return consultationService.updateConsultation(consultation);
        } else {
            return null;
        }
    }
    @PostMapping("/add")
    public Consultation createConsultation(@RequestBody Consultation consultation) {
        return consultationService.createConsultation(consultation);
    }
}
