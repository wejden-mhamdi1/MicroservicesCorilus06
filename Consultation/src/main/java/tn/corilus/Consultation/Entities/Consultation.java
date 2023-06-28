package tn.corilus.Consultation.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Consultation {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String motif; //raison de patient pour passé une consultation
    private String type_consultation;//telephonique,enligne,en personne
    private int frais;
    private String traitement;
    private String allergies;
    private String nom_patient;
}

