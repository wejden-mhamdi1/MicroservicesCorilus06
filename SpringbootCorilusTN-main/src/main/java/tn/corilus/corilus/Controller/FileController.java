package tn.corilus.corilus.Controller;

import org.apache.http.entity.ContentType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.ByteArrayResource;

import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.ResponseDataFile;

import tn.corilus.corilus.Services.FileService;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("file")
public class FileController {
    @Autowired
    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService=fileService;
    }

    //cette methode sert a convertir fichier de type .pdf, .csv en .txt
@PostMapping("/ess")

public ResponseEntity<?> convertir(@RequestParam("file") MultipartFile file) {
    try {
        File result = fileService.convertirFichier(file);
        return ResponseEntity.ok()
                .body(result);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
    //cette methode sert a stocker le fichier de maniere securisé dans la base de données et en local sous fichier ./uploads
    @PostMapping("/upload")
    public ResponseDataFile uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        File file11 = null;
        String downloadURl = "";
        String dateSt = LocalDate.now().toString();
        String newFileName =  StringUtils.cleanPath(file.getOriginalFilename().
                substring(0, file.getOriginalFilename().length() - 4) + "_" + dateSt + ".txt");
        Path path = Paths.get("./SpringbootCorilusTN-main/uploads/"+ ".txt");

        file11 = fileService.SaveBDD(file,path.toString());
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("./uploads/")
                .path(String.valueOf(file11.getId()))
                .toUriString();
        return new ResponseDataFile(file11.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());

    }
    //cette methode sert a Decrypter le contenue de fichier avec leur key pour etre bien visible
    @GetMapping("/FileDecypter/{fileId}")
    public ResponseEntity<Resource> FileDecypter(@PathVariable int fileId) throws Exception {
        File file = null;
        file = fileService.getFileDécrypter(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFileName()
                                + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
    //cette methode sert a crypter le contenue de fichier pour etre sécuriser
    @GetMapping("/FileCypter/{fileId}")
    public ResponseEntity<Resource> FileCypter(@PathVariable int fileId) throws Exception {
        File file = null;
        file = fileService.getFile(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFileName()
                                + "\"")
                .body(new ByteArrayResource(file.getData()));
    }


    //cette methode sert a telecharger le fichier d'un serveur distant (pas tester)

    @GetMapping("/telecharger")
    public ResponseEntity<?> downloadFile(@RequestParam String fileUrl) {
        String saveFilePath = "./";
        fileService.downloadFile(fileUrl, saveFilePath);
        return ResponseEntity.ok("Le fichier a été téléchargé avec succès.");
    }
    //cette methode sert resumer tous les etapes :
    // L'utilisateur peut stocker le fichier de manière sécurisée dans la base de données en utilisant un cryptage ,
    // tout en le stockant localement.
    // De plus, il peut entrer différents types de fichiers tels que .txt, .pdf et .csv, qui seront convertis en format .txt
    @GetMapping ("/upload22/{fileId}")
    public ResponseEntity<List<String>> fileUpload22 (@PathVariable int fileId)
            throws Exception {
        return new ResponseEntity<>(fileService.uploadFileTW(fileId),
                HttpStatus.OK);

    }
//cette methode sert a consulter tous les details de fichier uploader
    @GetMapping("/getAll")
    public List<File> getAll(){
        return fileService.Getall();
    }
    /*
    ici
    */
    @GetMapping("/getFileID/{id}")
    public File getFileID(@PathVariable int id){
        return fileService.getFile(id);
    }
    @GetMapping("/auth/{id}")
    public ResponseEntity< List<File> >getAll(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(fileService.getallUserBYFile(id));
    }

}




