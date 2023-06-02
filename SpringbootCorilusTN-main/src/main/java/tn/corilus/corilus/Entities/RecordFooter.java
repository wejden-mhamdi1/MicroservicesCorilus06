package tn.corilus.corilus.Entities;

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
public class RecordFooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String type;

    private String description;

    @Lob

    @Column( length = 5000 )
    private String data;
    @ManyToOne
    private File file;

    public RecordFooter() {

    }

    public RecordFooter(int id, String type, String data, File file, SortedMap<Integer, ZoneEmbeded> type90, SortedMap<Integer, ZoneEmbeded> type95, SortedMap<Integer, ZoneEmbeded> type96, SortedMap<Integer, ZoneEmbeded> type91, SortedMap<Integer, ZoneEmbeded> type92) {
        this.id = id;
        this.type = type;
        this.data = data;
        this.file = file;
        this.type90 = type90;
        this.type95 = type95;
        this.type96 = type96;
        this.type91 = type91;
        this.type92 = type92;
    }

    @ElementCollection
    SortedMap<Integer, ZoneEmbeded> type90 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"numero d'ordre de l'enregistrement","zone 2")),
                    entry(17, new ZoneEmbeded(12,"n compte financier a","zone 5 - 6a")),
                    entry(33, new ZoneEmbeded(3,"numero de l'envoi","zone 7")),

                    entry(56, new ZoneEmbeded(12,"n tiers payant","zone 14")),

                    entry(88, new ZoneEmbeded(12,"signe + montant total numero compte financier A","zone 19")),
                    entry(108, new ZoneEmbeded(5,"annee facture","zone 22 ")),
                    entry(113, new ZoneEmbeded(2,"mois facture","zone 23 ")),
                    entry(128, new ZoneEmbeded(10,"numero BCE","zone 27 ")),
                    entry(138, new ZoneEmbeded(25,"reference de l'etablissement","zone 28 ")),
                    entry(167, new ZoneEmbeded(11,"bic compte dinancier A","zone 31-34")),
                    entry(179, new ZoneEmbeded(34,"iban compte financier A","zone 36-41"))




                    ));
    @ElementCollection
    SortedMap<Integer, ZoneEmbeded> type90MSGOA = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "zone1")),
                    entry(3, new ZoneEmbeded(6,"numero d'ordre de l'enregistrement","zone 2")),
                    entry(17, new ZoneEmbeded(12,"n compte financier a","zone 5 - 6a")),
                    entry(33, new ZoneEmbeded(3,"numero de l'envoi","zone 7")),

                    entry(56, new ZoneEmbeded(12,"n tiers payant","zone 14")),

                    entry(88, new ZoneEmbeded(12,"signe + montant total numero compte financier A","zone 19")),
                    entry(108, new ZoneEmbeded(5,"annee facture","zone 22 ")),
                    entry(113, new ZoneEmbeded(2,"mois facture","zone 23 ")),
                    entry(128, new ZoneEmbeded(10,"numero BCE","zone 27 ")),
                    entry(138, new ZoneEmbeded(25,"reference de l'etablissement","zone 28 ")),
                    entry(167, new ZoneEmbeded(11,"bic compte dinancier A","zone 31-34")),
                    entry(179, new ZoneEmbeded(34,"iban compte financier A","zone 36-41")),
/*

                    entry(447, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(448, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),

                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b"))
                    */
                    entry(457, new ZoneEmbeded(1,"LETTRE DE REJET 1","111a")),
                    entry(458, new ZoneEmbeded(6,"CODE DE REJET 1","111b")),
                    entry(464, new ZoneEmbeded(1,"LETTRE DE REJET 2","112a")),
                    entry(465, new ZoneEmbeded(6,"CODE DE REJET 2","112b")),

                    entry(471, new ZoneEmbeded(1,"CODE DE REJET 3","113a")),
                    entry(472, new ZoneEmbeded(6,"LETTRE DE REJET 3","113b"))


            ));
    @ElementCollection
            //95ou96
    SortedMap<Integer, ZoneEmbeded> type95 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "type de record","400")),
                    entry(3, new ZoneEmbeded(2,"code err","4001")),
                    entry(5, new ZoneEmbeded(3,"numero de mutualalite","401")),
                    entry(8, new ZoneEmbeded(2,"code err","4011")),
                    entry(10, new ZoneEmbeded(12,  "numero de facture recapitulative","402")),
                    entry(22, new ZoneEmbeded(2,"code err","4021")),
                    entry(24, new ZoneEmbeded(1,  "signe montant demandé compte A","403")),
                    entry(25, new ZoneEmbeded(11,"montant demandé compte A","404")),
                    entry(36, new ZoneEmbeded(2,"code err","4041")),

                    entry(52, new ZoneEmbeded(1,"signe montant demandé compte B A C","407")),
                    entry(53, new ZoneEmbeded(11,"montant demandé compte B A C","408")),
                    entry(64, new ZoneEmbeded(2,  "code err","4081")),
                    entry(66, new ZoneEmbeded(8,  "nbr d'enregistrement","409")),
                    entry(74, new ZoneEmbeded(2,  "code err","4091")),
                    entry(76, new ZoneEmbeded(2,  "numero de control par mutualite","410")),
                    entry(78, new ZoneEmbeded(2,  "code err","4101"))




                    ));
    @ElementCollection
    //95ou96
    SortedMap<Integer, ZoneEmbeded> type95MSGOA = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "type de record","400")),
                    entry(3, new ZoneEmbeded(2,"code err","4001")),
                    entry(5, new ZoneEmbeded(3,"numero de mutualalite","401")),
                    entry(8, new ZoneEmbeded(2,"code err","4011")),
                    entry(10, new ZoneEmbeded(12,  "numero de facture recapitulative","402")),
                    entry(22, new ZoneEmbeded(2,"code err","4021")),
                    entry(24, new ZoneEmbeded(1,  "signe montant demandé compte A","403")),
                    entry(25, new ZoneEmbeded(11,"montant demandé compte A","404")),
                    entry(36, new ZoneEmbeded(2,"code err","4041")),

                    entry(52, new ZoneEmbeded(1,"signe montant demandé compte B A C","407")),
                    entry(53, new ZoneEmbeded(11,"montant demandé compte B A C","408")),
                    entry(64, new ZoneEmbeded(2,  "code err","4081")),
                    entry(66, new ZoneEmbeded(8,  "nbr d'enregistrement","409")),
                    entry(74, new ZoneEmbeded(2,  "code err","4091")),
                    entry(76, new ZoneEmbeded(2,  "numero de control par mutualite","410")),
                    entry(78, new ZoneEmbeded(2,  "code err","4101")),
//
                    entry(80, new ZoneEmbeded(1,  "Signe montant demandé compte C ","411")),
                    entry(81, new ZoneEmbeded(11,  "Montant demandé compte C","412")),
                    entry(92, new ZoneEmbeded(2,  "code err","4121")),
                    entry(94, new ZoneEmbeded(257,  "reserve","413"))




            ));
    @ElementCollection
    SortedMap<Integer, ZoneEmbeded> type96 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "type de record","500")),
                    entry(3, new ZoneEmbeded(2,"code err","5001")),
                    entry(5, new ZoneEmbeded(3,"numero de mutualalite","501")),
                    entry(8, new ZoneEmbeded(2,"code err","5011")),

                    entry(24, new ZoneEmbeded(1,  "signe montant demandé compte A","503")),
                    entry(25, new ZoneEmbeded(11,"montant demandé compte A","504")),
                    entry(36, new ZoneEmbeded(2,"code err","5041")),

                    entry(52, new ZoneEmbeded(1,"signe montant demandé compte B A C","507")),
                    entry(53, new ZoneEmbeded(11,"montant demandé compte B A C","508")),
                    entry(64, new ZoneEmbeded(2,  "code err","5081")),
                    entry(66, new ZoneEmbeded(8,  "nbr d'enregistrement","509")),
                    entry(74, new ZoneEmbeded(2,  "code err","5091")),
                    entry(76, new ZoneEmbeded(2,  "numero de control par mutualite","510")),
                    entry(78, new ZoneEmbeded(2,  "code err","5101"))



                    ));
    @ElementCollection
    SortedMap<Integer, ZoneEmbeded> type96MSGOA = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "type de record","500")),
                    entry(3, new ZoneEmbeded(2,"code err","5001")),
                    entry(5, new ZoneEmbeded(3,"numero de mutualalite","501")),
                    entry(8, new ZoneEmbeded(2,"code err","5011")),

                    entry(24, new ZoneEmbeded(1,  "signe montant demandé compte A","503")),
                    entry(25, new ZoneEmbeded(11,"montant demandé compte A","504")),
                    entry(36, new ZoneEmbeded(2,"code err","5041")),

                    entry(52, new ZoneEmbeded(1,"signe montant demandé compte B A C","507")),
                    entry(53, new ZoneEmbeded(11,"montant demandé compte B A C","508")),
                    entry(64, new ZoneEmbeded(2,  "code err","5081")),
                    entry(66, new ZoneEmbeded(8,  "nbr d'enregistrement","509")),
                    entry(74, new ZoneEmbeded(2,  "code err","5091")),
                    entry(76, new ZoneEmbeded(2,  "numero de control par mutualite","510")),
                    entry(78, new ZoneEmbeded(2,  "code err","5101")),

                    entry(80, new ZoneEmbeded(1,  "Signe montant demandé compte C","511")),
                    entry(81, new ZoneEmbeded(11,  "Montant demandé compte C","512")),
                    entry(92, new ZoneEmbeded(2,  "code err","5121")),
                    entry(94, new ZoneEmbeded(271,  "Reserve","513"))



            ));

    @ElementCollection
    SortedMap<Integer, ZoneEmbeded> type91 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "type de record","400")),
                    entry(3, new ZoneEmbeded(2,"code err","4001")),
                    entry(5, new ZoneEmbeded(3,"numero de mutualalite","401")),
                    entry(8, new ZoneEmbeded(2,"code err","4011")),
                    entry(10, new ZoneEmbeded(12,  "numero de facture recapitulative","402")),
                    entry(22, new ZoneEmbeded(2,"code err","4021")),
                    entry(24, new ZoneEmbeded(1,  "signe montant demandé compte A","403")),
                    entry(25, new ZoneEmbeded(11,"montant demandé compte A","404")),
                    entry(36, new ZoneEmbeded(2,"code err","4041")),
                    entry(38, new ZoneEmbeded(1,"signe montant demandé compte B","405 ")),
                    entry(39, new ZoneEmbeded(11,"montant demandé compte B","406 ")),
                    entry(50, new ZoneEmbeded(2,"code err","4061 ")),
                    entry(52, new ZoneEmbeded(1,"signe montant demandé compte B A C","407")),
                    entry(53, new ZoneEmbeded(11,"montant demandé compte B A C","408")),
                    entry(64, new ZoneEmbeded(2,  "code err","4081")),
                    entry(66, new ZoneEmbeded(8,  "nbr d'enregistrement","409")),
                    entry(74, new ZoneEmbeded(2,  "code err","4091")),
                    entry(76, new ZoneEmbeded(2,  "numero de control par mutualite","410")),
                    entry(78, new ZoneEmbeded(2,  "code err","4101")),
                    entry(80, new ZoneEmbeded(1,  "signe montant demandé compte A","411")),
                    entry(81, new ZoneEmbeded(11,  "montant demandé Compte A","412")),
                    entry(92, new ZoneEmbeded(2,  "code err","4121")),
                    entry(94, new ZoneEmbeded(1,  "signe montant refusé compte A","413")),
                    entry(95, new ZoneEmbeded(11,  "montant refusé compte A","414")),
                    entry(106, new ZoneEmbeded(2,  "code err","4141")),


                    entry(136, new ZoneEmbeded(1,  "signe total montants acceptés compte A B C","419")),
                    entry(137, new ZoneEmbeded(11,  "total montants acceptés compte A B C","420")),
                    entry(148, new ZoneEmbeded(2,  "code err","4201")),
                    entry(150, new ZoneEmbeded(1,  "Signe total montants refusés compte A + compte B + compte C ","421")),
                    entry(151, new ZoneEmbeded(11,  " total montants refusés compte A + compte B + compte C","422")),
                    entry(162, new ZoneEmbeded(2,  "code err","4221")),
                    entry(164, new ZoneEmbeded(22,  "Référence paiement  compte A OA ou mutualité","423")),
                    entry(186, new ZoneEmbeded(2,  "code err","4231"))





            ));

    @ElementCollection
    SortedMap<Integer, ZoneEmbeded> type92 = new TreeMap<>(
            Map.ofEntries(
                    entry(1, new ZoneEmbeded(2,  "type de record","500")),
                    entry(3, new ZoneEmbeded(2,"code err","5001")),
                    entry(5, new ZoneEmbeded(3,"numero de mutualalite","501")),
                    entry(8, new ZoneEmbeded(2,"code err","5011")),
                    entry(10, new ZoneEmbeded(12,  "mot used","502")),
                    entry(22, new ZoneEmbeded(2,"code err","5021")),
                    entry(24, new ZoneEmbeded(1,  "signe montant demandé compte A","503")),
                    entry(25, new ZoneEmbeded(11,"montant demandé compte A","504")),
                    entry(36, new ZoneEmbeded(2,"code err","5041")),
                    entry(38, new ZoneEmbeded(1,"signe montant demandé compte B","505 ")),
                    entry(39, new ZoneEmbeded(11,"montant demandé compte B","506 ")),
                    entry(50, new ZoneEmbeded(2,"code err","5061 ")),
                    entry(52, new ZoneEmbeded(1,"signe montant demandé compte B A C","507")),
                    entry(53, new ZoneEmbeded(11,"montant demandé compte B A C","508")),
                    entry(64, new ZoneEmbeded(2,  "code err","5081")),
                    entry(66, new ZoneEmbeded(8,  "nbr d'enregistrement","509")),
                    entry(74, new ZoneEmbeded(2,  "code err","5091")),
                    entry(76, new ZoneEmbeded(2,  "numero de control par mutualite","510")),
                    entry(78, new ZoneEmbeded(2,  "code err","5101")),
                    entry(80, new ZoneEmbeded(1,  "signe montant demandé compte C","511")),
                    entry(81, new ZoneEmbeded(11,  "montant demandé Compte C","512")),
                    entry(92, new ZoneEmbeded(2,  "code err","5121")),
                    entry(94, new ZoneEmbeded(257,  "signe total montants REFUSé Compte A","513")) ,
                    entry(95, new ZoneEmbeded(11,  "total montant refusé compte A","514")),
                    entry(106, new ZoneEmbeded(2,  "code err","5141")),
                    entry(108, new ZoneEmbeded(1,  "signe montant accépté compte B","515")),//de 94 to 271
                    entry(109, new ZoneEmbeded(11,  "montant accépté compte B","516")),
                    entry(120, new ZoneEmbeded(2,  "CODE err","5161")),
                    entry(122, new ZoneEmbeded(1,  "signe montant refusé compte B","517")),
                    entry(123, new ZoneEmbeded(11,  "montant refusé compte B","518")),
                    entry(134, new ZoneEmbeded(2,  "Code err","5181")),
                    entry(136, new ZoneEmbeded(1,  "signe total montants acceptés compte A B C","519")),
                    entry(137, new ZoneEmbeded(11,  "total montants acceptés compte A B C","520")),
                    entry(148, new ZoneEmbeded(2,  "code err","5201")),
                    entry(150, new ZoneEmbeded(1,  "Signe total montants refusés compte A + compte B + compte C ","521")),
                    entry(151, new ZoneEmbeded(11,  " total montants refusés compte A + compte B + compte C","522")),
                    entry(162, new ZoneEmbeded(2,  "code err","5221")),

                    entry(164, new ZoneEmbeded(1,  "signe total montant demandés compte C","523")),
                    entry(165, new ZoneEmbeded(11,  "total montant refusés compte C","5231")),
                    entry(176, new ZoneEmbeded(2,  " code err","524")),
                    entry(178, new ZoneEmbeded(1,  "signe total montant accepté compte C","5241")),
                    entry(179, new ZoneEmbeded(11,  "total montant accéptés compte C","525")),
                    entry(190, new ZoneEmbeded(2,  "code err","526")),
                    entry(192, new ZoneEmbeded(1,  "signe total montant refusés compte C","5261")),
                    entry(193, new ZoneEmbeded(11,  "total montant refusés compte C","527")),
                    entry(204, new ZoneEmbeded(2,  "code err","528")),
                    entry(595, new ZoneEmbeded(192 ,  " reservé","5281"))




            ));


}
