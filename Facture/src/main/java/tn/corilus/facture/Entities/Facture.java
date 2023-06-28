package tn.corilus.facture.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Temporal(TemporalType.DATE)
    private Date date_facture;
    private String nom_Cabinet;
    private String nom_patient;
    private float frais_consultation;
    private float frais_supplimentaire;
    private float frais_suivi;
    private float montant_assurance;
    private float montant_mutualite;
    private float montant_total;
    private String mode_payement;
    private String mutualite;
}
