package tn.corilus.corilus.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data

public class ZoneEmbeded {
    public ZoneEmbeded() {
        // Constructeur vide requis pour les types embarqués
    }
    private int longueur;
    private int position;
    private String desription;
    private String nom_zone;

    public ZoneEmbeded(int longueur, String desription, String nom_zone) {
        this.longueur = longueur;

        this.desription = desription;
        this.nom_zone = nom_zone;
    }

    public ZoneEmbeded(int longueur, String nom_zone) {
        this.longueur = longueur;
        this.nom_zone = nom_zone;
    }

}
