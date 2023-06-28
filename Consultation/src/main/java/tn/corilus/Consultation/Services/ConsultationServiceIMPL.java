package tn.corilus.Consultation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.corilus.Consultation.Entities.Consultation;
import tn.corilus.Consultation.Repository.ConsultationRepository;

import java.util.List;

@Service
public class ConsultationServiceIMPL  implements ConsultationService{
    @Autowired
    ConsultationRepository consultationRepository;
    @Override
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);

    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation createConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
