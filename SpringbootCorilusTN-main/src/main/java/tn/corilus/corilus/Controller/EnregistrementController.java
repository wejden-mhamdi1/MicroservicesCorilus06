package tn.corilus.corilus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.corilus.corilus.Entities.Enregistrement;
import tn.corilus.corilus.Entities.Erreur;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.Zone;
import tn.corilus.corilus.Services.EnregistrementService;
import tn.corilus.corilus.Services.ErreurService;
import tn.corilus.corilus.Services.FileService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("enregistrement")
public class EnregistrementController {
    @Autowired
    EnregistrementService enregistrementService;

    @GetMapping("/getAllEnregistrement")
    public List<Enregistrement> getAll(){
        return enregistrementService.getall();
    }

    @GetMapping("id/{id}")
    public Enregistrement findZoneById(@PathVariable int id){
        return enregistrementService.getEnregistrement(id);
    }
}