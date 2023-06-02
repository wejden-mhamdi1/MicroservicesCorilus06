package tn.corilus.corilus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SortNatural;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Map.entry;

@Entity
@Data

public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String nom_zone;
    private String description;

    @Lob
    @Column( length = 5000 )
    private String data;


    @Column( length = 5000 )
    private String dataSegment300;


    @Column( length = 5000 )
    private String dataSegment200;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private File file;
    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> segment200 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(6,  "Nom du message"," 200")),
                    entry(7, new ZoneEmbeded(2,"code err ","2001")),
                    entry(9, new ZoneEmbeded(2,  "N version du format du message"," 201")),
                    entry(11, new ZoneEmbeded(2,  "code err"," 2011")),
                    entry(13, new ZoneEmbeded(2,  "Type du message"," 202")),
                    entry(15, new ZoneEmbeded(2,  "Code err"," 2021")),
                    entry(17, new ZoneEmbeded(2,  "Status du message"," 203")),
                    entry(19, new ZoneEmbeded(2,  "code err"," 2031")),
                    entry(21, new ZoneEmbeded(14,  "Ref du message"," 204")),
                    entry(35, new ZoneEmbeded(2,  "Code err"," 2041")),
                    entry(37, new ZoneEmbeded(14,  "Ref message OA"," 205")),
                    entry(51, new ZoneEmbeded(2,  "Code err"," 2051")),
                    entry(53, new ZoneEmbeded(15,  "Reserve"," 206"))));

    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> segment300 = new TreeMap<>(
            Map.ofEntries(
                    entry(66, new ZoneEmbeded(6,  "Année et moi de facturation"," 300")),
                    entry(72, new ZoneEmbeded(2,"code err ","3001")),
                    entry(74, new ZoneEmbeded(3,  "N d'envoi"," 301")),
                    entry(77, new ZoneEmbeded(2,  "code err"," 3011")),
                    entry(79, new ZoneEmbeded(8,  "Date de creation de la facture"," 302")),
                    entry(87, new ZoneEmbeded(2,  "Code err"," 3021")),
                    entry(89, new ZoneEmbeded(13,  "Reference facture"," 303")),
                    entry(102, new ZoneEmbeded(2,  "code err"," 3031")),
                    entry(104, new ZoneEmbeded(7,  "Numero de version des instructions"," 304")),
                    entry(111, new ZoneEmbeded(2,  "Code err"," 3041")),
                    entry(113, new ZoneEmbeded(45,  "Nom de la personne de contact"," 305")),
                    entry(158, new ZoneEmbeded(2,  "Code err"," 3051")),
                    entry(160, new ZoneEmbeded(24,  "Prenom de la personne de contact"," 306")),
                    entry(184, new ZoneEmbeded(2,  "code err"," 3061")),
                    entry(186, new ZoneEmbeded(10,  "Numero de telephone de la personne de contact","307")),
                    entry(196, new ZoneEmbeded(2,  "code err"," 3071")),
                    entry(198, new ZoneEmbeded(2,  "Type de facture"," 308")),
                    entry(200, new ZoneEmbeded(2,  "code err"," 3081")),
                    entry(202, new ZoneEmbeded(2,  "Type facturation"," 309")),
                    entry(204, new ZoneEmbeded(2,  "code err"," 3091")),
                    entry(206, new ZoneEmbeded(20,  "Reserve"," 310"))));



   /* @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> segment300_920999 = new TreeMap<>(
            Map.ofEntries(
                    entry(191, new ZoneEmbeded(6,  "Année et moi de facturation"," 300")),

                    entry(331, new ZoneEmbeded(20,  "Reserve"," 310"))));*/








    public SortedMap<Integer, ZoneEmbeded> copySegment300(SortedMap<Integer, ZoneEmbeded> segment300) {
        SortedMap<Integer, ZoneEmbeded> segment300_920999 = new TreeMap<>();
        segment300_920999.putAll(segment300);
        return segment300_920999;
    }

    public SortedMap<Integer, ZoneEmbeded> addSegment(SortedMap<Integer, ZoneEmbeded> segment, int key, ZoneEmbeded value) {
        segment.put(key, value);
        return segment;
    }
    @ElementCollection(fetch = FetchType.LAZY)

    private SortedMap<Integer, ZoneEmbeded> segment300_920999 = new TreeMap<>();




    public Segment() {
        this.segment300_920999 = copySegment300(this.segment300);
        this.segment300_920999 = addSegment(this.segment300_920999, 336, new ZoneEmbeded(2, "code err", "310"));
        this.segment300_920999 = addSegment(this.segment300_920999, 338, new ZoneEmbeded(2, "type refus facturation", "310"));
        this.segment300_920999 = addSegment(this.segment300_920999, 340, new ZoneEmbeded(2, "code err", "310"));
        this.segment300_920999 = addSegment(this.segment300_920999, 342, new ZoneEmbeded(459, "Reserve", "310"));
    }

    public Segment(int id, String nom_zone, String description, String data) {
        this.id = id;
        this.nom_zone = nom_zone;
        this.description = description;
        this.data = data;
    }
}
