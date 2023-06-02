package tn.corilus.corilus.Services;

import org.springframework.stereotype.Service;
import tn.corilus.corilus.Entities.Erreur;

import java.util.List;
@Service
public interface ErreurService {


    public void rechercherAnomalies();
    public List<Erreur> getall();
    Erreur getErreurById(Long id);
    public    List<Erreur>  getErreurByFileId(int fileId);
    public List<Erreur> getErreurByZoneId(int zoneId);
}
