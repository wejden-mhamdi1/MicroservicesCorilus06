package tn.corilus.corilus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.corilus.corilus.Entities.*;
import tn.corilus.corilus.Repository.AnomalieRepository;
import tn.corilus.corilus.Repository.ErreurRepository;
import tn.corilus.corilus.Repository.ZoneRepository;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ErreurServiceIMPL implements ErreurService {

    @Autowired
    ZoneRepository zoneRepository;
    @Autowired
    ErreurRepository erreurRepository;
    @Autowired
    AnomalieRepository anomalieRepository;
    @Override
    public List<Erreur> getall() {
        return erreurRepository.findAll();
    }

    @Override
    public Erreur getErreurById(Long id) {
        return erreurRepository.findById(id).orElse(null);
    }

    @Override
    public List<Erreur> getErreurByFileId(int fileId) {
        return erreurRepository.findByFileId(fileId);
    }
    @Override
    public List<Erreur> getErreurByZoneId(int zoneId) {
        return erreurRepository.findByZoneId(zoneId);
    }


    @Override

    public void rechercherAnomalies() {
        List<Zone> zones = zoneRepository.findAll();
        List<Anomalie> anomalies = anomalieRepository.findAll();

        for (Zone zone : zones) {
            for (Anomalie anomalie : anomalies) {
                String codeError = anomalie.getCodeError();
                String data = zone.getData();
                if (Objects.equals(codeError, data)) {
                    System.out.println("erreur detecter");

                    // Vérifier si une erreur correspondante existe déjà dans la liste des erreurs
                    Erreur existingErreur = null;
                    for (Erreur erreur : zone.getErreurs()) {
                        if (Objects.equals(erreur.getCodeError(), codeError)) {
                            existingErreur = erreur;
                            break;
                        }
                    }

                    if (existingErreur != null) {
                        // Mettre à jour l'erreur existante avec les informations de l'anomalie
                        existingErreur.setMajBijw(anomalie.getMajBijw());
                        existingErreur.setModifType(anomalie.getModifType());
                        existingErreur.setErrorNature(anomalie.getErrorNature());
                        existingErreur.setCodeError(anomalie.getCodeError());
                        existingErreur.setBelgeDescription(anomalie.getBelgeDescription());
                        existingErreur.setFrenshDescription(anomalie.getFrenshDescription());
                        existingErreur.setRemarque(anomalie.getRemarque());
                        erreurRepository.save(existingErreur);
                    } else {
                        // Créer une nouvelle erreur et l'ajouter à la liste des erreurs de la zone
                        Erreur newErreur = new Erreur();
                        newErreur.setMajBijw(anomalie.getMajBijw());
                        newErreur.setModifType(anomalie.getModifType());
                        newErreur.setErrorNature(anomalie.getErrorNature());
                        newErreur.setCodeError(anomalie.getCodeError());
                        newErreur.setBelgeDescription(anomalie.getBelgeDescription());
                        newErreur.setFrenshDescription(anomalie.getFrenshDescription());
                        newErreur.setRemarque(anomalie.getRemarque());
                        newErreur.setZone(zone);
                        newErreur.setFile(zone.getFile());
                        newErreur.getFile().setScorefileErr(newErreur.getFile().getScorefileErr() + 1);
                        newErreur.getZone().setScoreZoneErr(newErreur.getZone().getScoreZoneErr()+1);
                        erreurRepository.save(newErreur);

                        zone.getErreurs().add(newErreur); // Ajouter la nouvelle erreur à la liste des erreurs de la zone
                    }
                }
            }
        }
    }}

      /*  List<Erreur> erreurs = new ArrayList<>();
        try {
            List<Zone> zones = zoneRepository.findAll();

            for (Zone zone : zones) {
               String dataZONE = zone.getData();
                String data = zone.getData(); // Récupérer la donnée à rechercher

                List<Anomalie> anomalies = anomalieRepository.findByData(data); // Rechercher la donnée dans la table "anomalie"

                if (!anomalies.isEmpty()) {


                    // dataZONEString existe dans la table "anomalie"
                    // Ajouter les données avec les erreurs à la classe "Erreur"
                    for (Anomalie anomalie : anomalies) {
                        Erreur erreur = new Erreur();
                        erreur.setData(dataZONE);
                        erreur.setMajBijw(anomalie.getMajBijw());
                        erreur.setModifType(anomalie.getModifType());
                        erreur.setErrorNature(anomalie.getErrorNature());
                        erreur.setCodeError(anomalie.getCodeError());
                        erreur.setBelgeDescription(anomalie.getBelgeDescription());
                        erreur.setFrenshDescription(anomalie.getFrenshDescription());
                        erreur.setRemarque(anomalie.getRemarque());

                        // Ajouter l'entité à la liste des erreurs
                        erreurs.add(erreur);
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace(); // Afficher la trace de l'exception dans la console
            // Gérer l'exception selon vos besoins
        }

        return erreurs;*/




