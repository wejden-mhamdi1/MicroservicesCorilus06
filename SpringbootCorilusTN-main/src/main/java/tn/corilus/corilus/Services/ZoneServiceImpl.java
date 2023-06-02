package tn.corilus.corilus.Services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.corilus.corilus.Entities.Enregistrement;
import tn.corilus.corilus.Entities.RecordFooter;
import tn.corilus.corilus.Entities.Segment;
import tn.corilus.corilus.Entities.Zone;
import tn.corilus.corilus.Repository.ZoneRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

@Service
public class ZoneServiceImpl implements ZoneService {
    private ZoneRepository zoneRepository;


    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone saveAndAffectZone(int longeur, int debut,String nomzone,String description, Enregistrement enregistrement) {//,String i

        Zone zone = new Zone();
        zone.setData(enregistrement.getData().substring(debut - 1, longeur + debut - 1));
        zone.setEnregistrement(enregistrement);
        zone.setDesription(description);
        zone.setNom_zone(nomzone);

       zone.setFile(enregistrement.getFichier());
                // segment.setFile(segment2.getFile());
        return zoneRepository.save(zone);
    }


    @Override
    public Zone saveAndAffectZonesans(int longeur, int debut, Enregistrement enregistrement) {//,String i

        Zone zone = new Zone();
        zone.setData(enregistrement.getData().substring(debut - 1, longeur + debut - 1));
        zone.setEnregistrement(enregistrement);

        return zoneRepository.save(zone);
    }



    @Override
    public List<String> generateZoneTxt(List<String> zones, Enregistrement enregistrement) throws IOException {
        String dateSt= LocalDate.now().toString();

        Path filePath = Paths.get("./SpringbootCorilusTN-main/uploadsSplited/"  +"Splited.txt");


        Files.deleteIfExists(filePath);
        Files.createFile(filePath);
        for (String str : zones) {
            Files.writeString(filePath, str + System.lineSeparator(),
                    StandardOpenOption.APPEND);
        }
        return zones;
    }


    @Override
    public List<Zone> Getall() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone getFile(int fileId)  {
        return zoneRepository
                .findById(fileId).orElse(null);
    }
    public    List<Zone>  getzoneByFileId(int fileId) {
        // Implémentez la logique pour récupérer le segment en fonction de l'id du fichier
        // Par exemple, vous pouvez utiliser un repository ou un autre moyen d'accéder à la base de données

        // Exemple de récupération du segment à partir d'un repository
        return zoneRepository.findByFileId(fileId);
    }

}