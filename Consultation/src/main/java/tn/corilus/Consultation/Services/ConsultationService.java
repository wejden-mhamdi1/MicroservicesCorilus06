package tn.corilus.Consultation.Services;

import tn.corilus.Consultation.Entities.Consultation;

import java.util.List;

public interface ConsultationService {
    void deleteConsultation( Long id);
    Consultation updateConsultation(Consultation consultation);
    Consultation getConsultationById( Long id);
    List<Consultation> getAllConsultations();
    Consultation createConsultation( Consultation consultation);
}
