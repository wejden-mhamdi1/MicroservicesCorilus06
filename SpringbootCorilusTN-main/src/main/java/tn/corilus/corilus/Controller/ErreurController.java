package tn.corilus.corilus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.corilus.corilus.Entities.Erreur;
import tn.corilus.corilus.Services.ErreurService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ErreurController {
    @Autowired
    ErreurService erreurService;

    @GetMapping("/getall")
    public List<Erreur> getAll(){
        return erreurService.getall();
    }
    @GetMapping("/recherche")
    public ResponseEntity<String> rechercherAnomaliesDansZones() {
        erreurService.rechercherAnomalies();
        return ResponseEntity.ok("Recherche effectuée");
    }
    @GetMapping("/geterr/{id}")
    public Erreur getConsultationById(@PathVariable Long id) {
        return erreurService.getErreurById(id);
    }
    @GetMapping("iderr/{id}")
    public List<Erreur> iderr(@PathVariable int id){

        return erreurService.getErreurByFileId(id);

    }
    @GetMapping("iderrZONE/{id}")
    public List<Erreur> iderrZONE(@PathVariable int id){

        return erreurService.getErreurByZoneId(id);

    }
}
