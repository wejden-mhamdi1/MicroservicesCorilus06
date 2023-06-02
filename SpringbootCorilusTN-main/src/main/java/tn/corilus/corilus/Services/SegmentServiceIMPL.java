package tn.corilus.corilus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.corilus.corilus.Entities.*;
import tn.corilus.corilus.Repository.SegmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SegmentServiceIMPL implements SegmentService {
    @Autowired
    SegmentRepository segmentRepository;

    @Override
    public Segment AffSegment(int longeur, int debut, String nomzone, String description, Segment segment2) {
        Segment segment = new Segment();
        String sub = segment2.getData().substring(debut - 1, longeur + debut - 1);

        //  if (sub == null || sub.trim().isEmpty()) {return null;}

        //  if (sub == null || sub.trim().isEmpty()) {return null;}
        segment.setFile(segment2.getFile());

        segment.setData(sub);
        segment.setDescription(description);
        segment.setNom_zone(nomzone);
        return segmentRepository.save(segment);
    }

    @Override
    public void segmentsave(File file) throws Exception {
        Segment segment2 = new Segment();
        List<String> zones = new ArrayList<>();
        segment2.setData(new String(file.getData(), "UTF-8"));
        segment2.setDataSegment200(new String(file.getData(), "UTF-8").substring(0, 67));
        segment2.setDataSegment300(new String(file.getData(), "UTF-8").substring(67, 227));
        segment2.setFile(file);
        for (int j = 0; j <= segment2.getDataSegment200().length(); j++) { //0-->66 --> 66-0+1=67 sera exécuté 67 fois
            for (Map.Entry<Integer, ZoneEmbeded> entry : segment2.getSegment200().entrySet()) {
                ZoneEmbeded value = entry.getValue();
                Segment segment = AffSegment(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), segment2);

                zones.add(segment.getDataSegment200());
            }
            for (int i = segment2.getDataSegment200().length() - 1; i <= 226; i++) {

                for (Map.Entry<Integer, ZoneEmbeded> entry2 : segment2.getSegment300().entrySet()) {
                    ZoneEmbeded value2 = entry2.getValue();
                    Segment s2 = AffSegment(value2.getLongueur(), entry2.getKey(), value2.getNom_zone(), value2.getDesription(), segment2);

                    zones.add(s2.getDataSegment300());


                }

            }
                   /* case "920900":
                        for (int j = 107; j < (121 + 450); j++) {
                            for (Map.Entry<Integer, ZoneEmbeded> entry : segment2.getSegment300_920999().entrySet()) {


                                ZoneEmbeded value = entry.getValue();
                                zones.add(AffSegment(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), segment2).getData());

                            }
                            break;
                        }*/


        }
    }

    @Override
    public List<Segment> getall() {
        return segmentRepository.findAll();
    }

    @Override
    public Segment getSegment(int id) {
        return segmentRepository.findById(id).orElse(null);
    }

    public    List<Segment>  getSegmentByFileId(int fileId) {
        // Implémentez la logique pour récupérer le segment en fonction de l'id du fichier
        // Par exemple, vous pouvez utiliser un repository ou un autre moyen d'accéder à la base de données

        // Exemple de récupération du segment à partir d'un repository
        return segmentRepository.findByFileId(fileId);
    }
}
