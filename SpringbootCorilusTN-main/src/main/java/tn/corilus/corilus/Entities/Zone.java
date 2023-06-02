package tn.corilus.corilus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

//@Embeddable
@Entity
@Data
@NoArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int longueur;
    private int position;
    private int scoreZoneErr;
    private String desription;
    private String nom_zone;

    public Zone(int longueur, String desription, String nom_zone) {
        this.longueur = longueur;

        this.desription = desription;
        this.nom_zone = nom_zone;
    }

    public Zone(int longueur, String nom_zone) {
        this.longueur = longueur;
        this.nom_zone = nom_zone;
    }

    @Lob
    @Column( length = 5000 )
    private String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Enregistrement enregistrement;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private File file;

     @OneToMany(mappedBy = "zone")

 private List<Erreur> erreurs = new ArrayList<>();
   /* @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "zone_erreur",
            joinColumns = @JoinColumn(name = "zone_id"),
            inverseJoinColumns = @JoinColumn(name = "erreur_id")
    )
    private List<Erreur> erreurs;*/
}
