package tn.corilus.corilus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SortNatural;

import static java.util.Map.entry;
import java.util.*;
import java.util.SortedMap;
import java.util.TreeMap;
//parse et split
@Entity
@Data
@NoArgsConstructor
public class Enregistrement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String description;

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("ID: " + this.id + ", ");
        sb.append("Nom: " + this.type + ", ");

        return sb.toString();
    }

    @Lob

    @Column( length = 50000)
    private String data;

    private String entete;
    @Column( length = 50000 )
    private String body;
    @Column( length = 50000 )
    private String footer;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private File fichier;


    @OneToMany(mappedBy = "enregistrement",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Zone> zoness = new ArrayList<>();



    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "enregistrement_zones")
    @MapKeyColumn(name = "zone_key")

    private List<ZoneEmbeded> zones = new ArrayList<>();


    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type10 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                            entry(3, new ZoneEmbeded(6,"Numero d'ordre de l'enregistrement","Zone2")), //zone2
                            entry(10, new ZoneEmbeded(7,"Version fichier","zone4")), //zone4

                            entry(33, new ZoneEmbeded(3,"Numero de l'envoie","zone7")),//zone7

                            entry(53, new ZoneEmbeded(3,"Contenue facturation","zone13")),//zone13
                            entry(56, new ZoneEmbeded(12,"n tiers payant INAMI","zone14")),//zone14

                            entry(108, new ZoneEmbeded(5,"annee facturee","zone22")),//zone22
                            entry(113, new ZoneEmbeded(2,"date de creation","zone23")),//zone23
                            entry(120, new ZoneEmbeded(8,"Reference de l'etablissement","zone25-26")),//zone25-26
                            entry(128, new ZoneEmbeded(10,"BIC compte financier A","zone27")),//zone27
                            entry(138, new ZoneEmbeded(25,"numero BCE","zone28")),//zone28
                            entry(167, new ZoneEmbeded(11,"BIC compte financier A","zone 31-32-33-34")),//zone 31-32-33-34
                            entry(179, new ZoneEmbeded(34, "BIC compte financier B","zone 36-37-38-39-40-41"))

            ));







    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type20MSGOA= new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"Numero d'ordre de l'enregistrement","zone2")),
                    entry(9, new ZoneEmbeded(1,"autorisation tiers payant","zone3")),
                    entry(10, new ZoneEmbeded(7,  "zone4")),
                    entry(17, new ZoneEmbeded(8,  "zone5")),

                    entry(33, new ZoneEmbeded(3,"Numero mutualite affiliation","zone7")),
                    entry(36, new ZoneEmbeded(13,"identification du beneficiaire N NISS","zone8a-8b")),
                    entry(49, new ZoneEmbeded(1,"sexe beneficiaire","zone 9")),
                    entry(50, new ZoneEmbeded(1,"type facture","zone 10")),
                    entry(51, new ZoneEmbeded(1,"type facturation","zone 11")),
                    entry(53, new ZoneEmbeded(3,"service 721bis","zone 13")),
                    entry(56, new ZoneEmbeded(12,"n de l'etablissement qui facture","zone 14")),
                    entry(68, new ZoneEmbeded(12,"n de l'etablissement de sejour","zone 15")),
                    entry(80, new ZoneEmbeded(1,"code levee delai de prescription","zone 16")),
                    entry(81, new ZoneEmbeded(4,"causes du traitement","zone 17")),
                    entry(85, new ZoneEmbeded(3,"n mutualite destination","zone 18")),

                    entry(100, new ZoneEmbeded(8,"date de l'accord traitement de reducation","zone 20-21")),

                    entry(115, new ZoneEmbeded(12,"n de facture individuelle","zone 24-25")),

                    entry(128, new ZoneEmbeded(10,"code titulaire 1+2","zone 27")),
                    entry(138, new ZoneEmbeded(25,"reference de l'etablissement","zone 28")),
                    entry(163, new ZoneEmbeded(12,"numero de facture precedente","zone 29-30-31")),
                    entry(175, new ZoneEmbeded(1,"flag identification du beneficiaire","zone 32")),
                    entry(177, new ZoneEmbeded(3,"numero de l'envoi precedent","zone 34-35-36")),
                    entry(180, new ZoneEmbeded(3,"n mutualite fact precedent","zone 37")),
                    entry(183, new ZoneEmbeded(22,"reference mutualite n de compte financiaire A","zone 38-39")),
                    entry(207, new ZoneEmbeded(6,"annee et mois precedemment factures","zone 41")),
                    entry(213, new ZoneEmbeded(48,"donnees de reference reseau","zone 42-45")),
                    entry(262, new ZoneEmbeded(8,"date de facturation","zone 47a-47b")),

                   /* entry(447, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(448, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
*/
                    entry(457, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(458, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b"))



            ));




    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type50 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"Numero d'ordre de l'enregistrement","zone2")),
                    entry(9, new ZoneEmbeded(1,"norme prestation","zone3")),
                    entry(10, new ZoneEmbeded(7,"pseudo code","zone4")),
                    entry(17, new ZoneEmbeded(8,"pseudo code","zone5")),
                    entry(25, new ZoneEmbeded(8,"date derniere prestation effectue","zone6a-6b")),
                    entry(33, new ZoneEmbeded(3,"n mutualite affiliation","zone7")),
                    entry(36, new ZoneEmbeded(13,"identification de beneficiaire","zone8a-8b")),
                    entry(49, new ZoneEmbeded(1,"sexe beneficiaire","zone9")),
                    entry(52, new ZoneEmbeded(1,"jour ferie","zone12")),
                    entry(53, new ZoneEmbeded(3,"code service","zone13")),
                    entry(56, new ZoneEmbeded(12,"lieu de prestation","zone14")),
                    entry(68, new ZoneEmbeded(12,"identification di dispensateur","zone15")),
                    entry(80, new ZoneEmbeded(1,"norme dispensateur","zone16")),
                    entry(81, new ZoneEmbeded(7,"prestation relative","zone17-18")),
                    entry(88, new ZoneEmbeded(12,"signe+montant intervention de l'assurance","zone19")),
                    entry(100, new ZoneEmbeded(8,"date de la prescription","zone20-21")),
                    entry(108, new ZoneEmbeded(5,"signe + nombre d'unites","zone22")),
                    entry(113, new ZoneEmbeded(2,"derogation nombre maximal ou prestation identique","zone23")),
                    entry(115, new ZoneEmbeded(12,"identification du prescripteur","zone24-25")),
                    entry(127, new ZoneEmbeded(1,"norme prescript","zone26")),
                    entry(128, new ZoneEmbeded(10,"signe+intervention personnelle","zone27")),
                    entry(138, new ZoneEmbeded(25,"ref etablissement","zone28")),
                    entry(163, new ZoneEmbeded(2,"dent traitee cas dentist","zone29")),
                    entry(165, new ZoneEmbeded(10,"signe +montant suppliment ou montant produit","zone30-31")),
                    entry(175, new ZoneEmbeded(1,"exception tiers payant","zone32")),
                    entry(176, new ZoneEmbeded(1,"code facturation intervention personelle ou supplement","zone33")),
                    entry(177, new ZoneEmbeded(1,"membre traite","zone34")),
                    entry(178, new ZoneEmbeded(1,"prestataire conventionne","zone35")),

                    entry(271, new ZoneEmbeded(12,"disponsateur auxiliaire","zone49"))




                    ));
    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type50MSGOA = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"Numero d'ordre de l'enregistrement","zone2")),
                    entry(9, new ZoneEmbeded(1,"norme prestation","zone3")),
                    entry(10, new ZoneEmbeded(7,"pseudo code","zone4")),
                    entry(17, new ZoneEmbeded(8,"pseudo code","zone5")),
                    entry(25, new ZoneEmbeded(8,"date derniere prestation effectue","zone6a-6b")),
                    entry(33, new ZoneEmbeded(3,"n mutualite affiliation","zone7")),
                    entry(36, new ZoneEmbeded(13,"identification de beneficiaire","zone8a-8b")),
                    entry(49, new ZoneEmbeded(1,"sexe beneficiaire","zone9")),
                    entry(52, new ZoneEmbeded(1,"jour ferie","zone12")),
                    entry(53, new ZoneEmbeded(3,"code service","zone13")),
                    entry(56, new ZoneEmbeded(12,"lieu de prestation","zone14")),
                    entry(68, new ZoneEmbeded(12,"identification di dispensateur","zone15")),
                    entry(80, new ZoneEmbeded(1,"norme dispensateur","zone16")),
                    entry(81, new ZoneEmbeded(7,"prestation relative","zone17-18")),
                    entry(88, new ZoneEmbeded(12,"signe+montant intervention de l'assurance","zone19")),
                    entry(100, new ZoneEmbeded(8,"date de la prescription","zone20-21")),
                    entry(108, new ZoneEmbeded(5,"signe + nombre d'unites","zone22")),
                    entry(113, new ZoneEmbeded(2,"derogation nombre maximal ou prestation identique","zone23")),
                    entry(115, new ZoneEmbeded(12,"identification du prescripteur","zone24-25")),
                    entry(127, new ZoneEmbeded(1,"norme prescript","zone26")),
                    entry(128, new ZoneEmbeded(10,"signe+intervention personnelle","zone27")),
                    entry(138, new ZoneEmbeded(25,"ref etablissement","zone28")),
                    entry(163, new ZoneEmbeded(2,"dent traitee cas dentist","zone29")),
                    entry(165, new ZoneEmbeded(10,"signe +montant suppliment ou montant produit","zone30-31")),
                    entry(175, new ZoneEmbeded(1,"exception tiers payant","zone32")),
                    entry(176, new ZoneEmbeded(1,"code facturation intervention personelle ou supplement","zone33")),
                    entry(177, new ZoneEmbeded(1,"membre traite","zone34")),
                    entry(178, new ZoneEmbeded(1,"prestataire conventionne","zone35")),

                    entry(271, new ZoneEmbeded(12,"disponsateur auxiliaire","zone49")),


                 /*   entry(447, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(448, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
*/
                    entry(457, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(458, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b")),
                    entry(478, new ZoneEmbeded(12,"montant tarifie compte A"," 114")),
                   /* entry(490, new ZoneEmbeded(12,"montant tarifie patient"," 116")),
                    entry(502, new ZoneEmbeded(12,"code nomenclature corrigé","117")),
                    entry(514, new ZoneEmbeded(7,"resultat OA","119")),
                    entry(521, new ZoneEmbeded(1,"commentaire du code d'erreur","149"))*/
                    entry(502, new ZoneEmbeded(12,"montant tarifie patient"," 116")),
                    entry(514, new ZoneEmbeded(7,"code nomenclature corrigé","117")),
                    entry(522, new ZoneEmbeded(12,"resultat OA","119")),
                    entry(534, new ZoneEmbeded(200,"commentaire du code d'erreur","149"))


            ));





    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type51 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"numero d'ordre de l'enregistrement","zone 2")),
                    entry(10, new ZoneEmbeded(7,"pseudo","zone 4")),

                    entry(17, new ZoneEmbeded(8,"date prestation","zone 5")),
                    entry(36, new ZoneEmbeded(13,"ID beneficiaire","zone 8a - 8b")),
                    entry(68, new ZoneEmbeded(12,"ID dispensateur","zone 15")),
                    entry(81, new ZoneEmbeded(7,"prestation relatif","zone 17-18")),
                    entry(88, new ZoneEmbeded(12,"signe+montant intervention de l'assurance","zone 19")),
                    entry(128, new ZoneEmbeded(10,"code titulaire 1+2","zone 27")),
                    entry(213, new ZoneEmbeded(48,"numero engagement tarif","zone 42-45")),
                    entry(321, new ZoneEmbeded(8,"date communication info","zone 55"))


                    ));
    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type51MSGOA = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"numero d'ordre de l'enregistrement","zone 2")),
                    entry(10, new ZoneEmbeded(7,"pseudo","zone 4")),

                    entry(17, new ZoneEmbeded(8,"date prestation","zone 5")),
                    entry(36, new ZoneEmbeded(13,"ID beneficiaire","zone 8a - 8b")),
                    entry(68, new ZoneEmbeded(12,"ID dispensateur","zone 15")),
                    entry(81, new ZoneEmbeded(7,"prestation relatif","zone 17-18")),
                    entry(88, new ZoneEmbeded(12,"signe+montant intervention de l'assurance","zone 19")),
                    entry(128, new ZoneEmbeded(10,"code titulaire 1+2","zone 27")),
                    entry(213, new ZoneEmbeded(48,"numero engagement tarif","zone 42-45")),
                    entry(321, new ZoneEmbeded(8,"date communication info","zone 55")),
/*
                    entry(447, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(448, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),

                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b")),
                    entry(478, new ZoneEmbeded(12,"montant tarifie compte A"," 114")),
                    entry(490, new ZoneEmbeded(12,"montant tarifie patient"," 116")),
                    entry(502, new ZoneEmbeded(12,"code nomenclature corrigé","117")),
                    entry(514, new ZoneEmbeded(7,"resultat OA","119")),
                    entry(521, new ZoneEmbeded(1,"commentaire du code d'erreur","149"))
                    */

                    //
                    entry(457, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(458, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b")),
                    entry(478, new ZoneEmbeded(12,"montant tarifie compte A"," 114")),
                    entry(502, new ZoneEmbeded(12,"montant tarifie patient"," 116")),
                    entry(514, new ZoneEmbeded(7,"code nomenclature corrigé","117")),
                    entry(522, new ZoneEmbeded(12,"resultat OA","119")),
                    entry(534, new ZoneEmbeded(200,"commentaire du code d'erreur","149"))

            ));



    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type80 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"numero d'ordre de l'enregistrement","zone 2")),

                    entry(33, new ZoneEmbeded(3,"n mutualite affiliation","zone 7")),
                    entry(36, new ZoneEmbeded(13,"identification du beneficiaire","zone 8a - 8b")),
                    entry(49, new ZoneEmbeded(1,"sexe beneficiaire","zone 9")),
                    entry(50, new ZoneEmbeded(1,"type facture ","zone 10")),
                    entry(53, new ZoneEmbeded(3,"service 721 bis","zone 13")),
                    entry(56, new ZoneEmbeded(12,"numero de l'etablissement qui facture","zone 14")),

                    entry(81, new ZoneEmbeded(4,"causes de traitement","zone 17")),
                    entry(85, new ZoneEmbeded(3,"numero de mutualite de destination","zone 18")),
                    entry(88, new ZoneEmbeded(12,"signe+montant numero de compte financiera","zone 19")),
                    entry(100, new ZoneEmbeded(8,"date de la facture","zone 20-21")),

                    entry(115, new ZoneEmbeded(12,"numero de la facture individuelle","zone 24-25")),
                    entry(128, new ZoneEmbeded(10,"signe+intervention personnelle patient ","zone 27")),
                    entry(138, new ZoneEmbeded(25,"reference de l'etablissement","zone 28")),
                    entry(165, new ZoneEmbeded(10,"signe+ montant supplement","zone 30-31")),
                    entry(175, new ZoneEmbeded(1,"flag identification du beneficiare","zone 32")),
                    entry(183, new ZoneEmbeded(12,"signe +acompte numero de compte financiera","zone 38"))


            ));

    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type80MSGOA = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"numero d'ordre de l'enregistrement","zone 2")),

                    entry(33, new ZoneEmbeded(3,"n mutualite affiliation","zone 7")),
                    entry(36, new ZoneEmbeded(13,"identification du beneficiaire","zone 8a - 8b")),
                    entry(49, new ZoneEmbeded(1,"sexe beneficiaire","zone 9")),
                    entry(50, new ZoneEmbeded(1,"type facture ","zone 10")),
                    entry(53, new ZoneEmbeded(3,"service 721 bis","zone 13")),
                    entry(56, new ZoneEmbeded(12,"numero de l'etablissement qui facture","zone 14")),

                    entry(81, new ZoneEmbeded(4,"causes de traitement","zone 17")),
                    entry(85, new ZoneEmbeded(3,"numero de mutualite de destination","zone 18")),
                    entry(88, new ZoneEmbeded(12,"signe+montant numero de compte financiera","zone 19")),
                    entry(100, new ZoneEmbeded(8,"date de la facture","zone 20-21")),

                    entry(115, new ZoneEmbeded(12,"numero de la facture individuelle","zone 24-25")),
                    entry(128, new ZoneEmbeded(10,"signe+intervention personnelle patient ","zone 27")),
                    entry(138, new ZoneEmbeded(25,"reference de l'etablissement","zone 28")),
                    entry(165, new ZoneEmbeded(10,"signe+ montant supplement","zone 30-31")),
                    entry(175, new ZoneEmbeded(1,"flag identification du beneficiare","zone 32")),
                    entry(183, new ZoneEmbeded(12,"signe +acompte numero de compte financiera","zone 38")),
/*
                    entry(447, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(448, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),

                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b")),
                    entry(478, new ZoneEmbeded(12,"montant tarifie compte A"," 114")),
                    entry(490, new ZoneEmbeded(12,"montant tarifie patient"," 116")),
                    entry(502, new ZoneEmbeded(12,"code nomenclature corrigé","117")),
                    entry(514, new ZoneEmbeded(7,"resultat OA","119")),
                    entry(521, new ZoneEmbeded(1,"commentaire du code d'erreur","149"))
         */
                    entry(457, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(458, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b")),
                    entry(478, new ZoneEmbeded(12,"montant tarifie compte A"," 114")),
                    entry(502, new ZoneEmbeded(12,"montant tarifie patient"," 116")),
                    entry(514, new ZoneEmbeded(7,"code nomenclature corrigé","117")),
                    entry(522, new ZoneEmbeded(12,"resultat OA","119")),
                    entry(534, new ZoneEmbeded(200,"commentaire du code d'erreur","149"))
            ));




    @ElementCollection(fetch = FetchType.LAZY)
    SortedMap<Integer, ZoneEmbeded> type20= new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"Numero d'ordre de l'enregistrement","zone2")),
                    entry(9, new ZoneEmbeded(1,"autorisation tiers payant","zone3")),


                    entry(33, new ZoneEmbeded(3,"Numero mutualite affiliation","zone7")),
                    entry(36, new ZoneEmbeded(13,"identification du beneficiaire N NISS","zone8a-8b")),
                    entry(49, new ZoneEmbeded(1,"sexe beneficiaire","zone 9")),
                    entry(50, new ZoneEmbeded(1,"type facture","zone 10")),
                    entry(51, new ZoneEmbeded(1,"type facturation","zone 11")),
                    entry(53, new ZoneEmbeded(3,"service 721bis","zone 13")),
                    entry(56, new ZoneEmbeded(12,"n de l'etablissement qui facture","zone 14")),
                    entry(68, new ZoneEmbeded(12,"n de l'etablissement de sejour","zone 15")),
                    entry(80, new ZoneEmbeded(1,"code levee delai de prescription","zone 16")),
                    entry(81, new ZoneEmbeded(4,"causes du traitement","zone 17")),
                    entry(85, new ZoneEmbeded(3,"n mutualite destination","zone 18")),

                    entry(100, new ZoneEmbeded(8,"date de l'accord traitement de reducation","zone 20-21")),

                    entry(115, new ZoneEmbeded(12,"n de facture individuelle","zone 24-25")),

                    entry(128, new ZoneEmbeded(10,"code titulaire 1+2","zone 27")),
                    entry(138, new ZoneEmbeded(25,"reference de l'etablissement","zone 28")),
                    entry(163, new ZoneEmbeded(12,"numero de facture precedente","zone 29-30-31")),
                    entry(175, new ZoneEmbeded(1,"flag identification du beneficiaire","zone 32")),
                    entry(177, new ZoneEmbeded(3,"numero de l'envoi precedent","zone 34-35-36")),
                    entry(180, new ZoneEmbeded(3,"n mutualite fact precedent","zone 37")),
                    entry(183, new ZoneEmbeded(22,"reference mutualite n de compte financiaire A","zone 38-39")),
                    entry(207, new ZoneEmbeded(6,"annee et mois precedemment factures","zone 41")),
                    entry(213, new ZoneEmbeded(48,"donnees de reference reseau","zone 42-45")),
                    entry(262, new ZoneEmbeded(8,"date de facturation","zone 47a-47b"))

            ));
















}

