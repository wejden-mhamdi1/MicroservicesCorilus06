package tn.corilus.corilus.Services;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.corilus.corilus.Entities.ChiffrementEnum;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.Status;
import tn.corilus.corilus.Repository.FileRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Service
public class FileServiceIMPL implements FileService {
    public FileServiceIMPL(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    private FileRepository fileRepository;
    @Autowired
    EnregistrementService enregistrementService;


    // java.io.File convertedFile = convertirFichier(file);
    @Override
    public File SaveBDD(MultipartFile file, String path) throws Exception {

        File fichier = convertirFichier(file);
        String dateSt = LocalDate.now().toString();
        String fileName =  StringUtils.cleanPath(fichier.getFileName().
                substring(0, fichier.getFileName().length() - 4) + "_" + dateSt + ".txt");
        java.io.File fileModel = new java.io.File(path);
        FileInputStream inputStream = new FileInputStream(fileModel);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String type = reader.readLine().substring(0, 6);
        reader.close();

        try {
            // Générer la clé de cryptage
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();

            // Crypter les données
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(fichier.getData());

            // Sauvegarder les données cryptées dans la base de données
            File file2 = new File(fileName, fichier.getFileType(), encryptedData);

            file2.setStatus(Status.NOTSPLIT);
            file2.setKey(secretKey);
            file2.setEssa(true);
            file2.setUserId(1);
            file2.setFileName(fileName);
            file2.setChiffrementEnum(ChiffrementEnum.ENCRYPTED);

            return fileRepository.save(file2);
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    // Méthode pour décrypter les données


    @Override
    public File getFileDécrypter(int fileId) throws Exception {

        File file = fileRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
        byte[] decryptedData = enregistrementService.decryptData(file.getData(), file.getKey());
        file.setChiffrementEnum(ChiffrementEnum.DECRYPTED);

        // Mise à jour des données de fichier avec les données décryptées
        file.setData(decryptedData);

        return file;
    }

    @Override
    public File getFile(int fileId)  {
        return fileRepository
                .findById(fileId).orElse(null);
    }

    @Override
    public boolean downloadFile(String url, String saveAsFileName) {
        try {
            URL fileUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) fileUrl.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            Files.copy(inputStream, Paths.get(saveAsFileName), StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File saveFile(String file, String name) {
        File txtfile = new File();
        txtfile.setFileName(name);
        txtfile.setData(file.getBytes());


        return fileRepository.save(txtfile);
    }


    @Override
    public List<String> uploadFileTW(int fileId) throws Exception {
        File file = null;
        file = getFile(fileId);

        //file.setEssa(true);
        // file.setChiffrementEnum(ChiffrementEnum.ENCRYPTED);
        if (file.isEssa()) {//file.getChiffrementEnum().equals("ENCRYPTED")
            file = getFileDécrypter(fileId);
            file.setEssa(false);


        } else file = getFile(fileId);
        //  file.getChiffrementEnum().equals("DECRYPTED");

        file.setEssa(false);
        return enregistrementService.saveAndAffect22(file);

    }

    public List<File> Getall() {


        return fileRepository.findAll();

    }


    @Override
    public List<File> findAllFilesByUser(Integer userId) {

            return fileRepository.findAllByUserId(userId);
        }


    public File convertirFichier(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFileName= file.getOriginalFilename().substring(0,file.getOriginalFilename().length()-4) +".txt" ;
        if (fileName.endsWith(".txt")) {
           File filetxt= new File(fileName,file.getContentType(),file.getBytes());
           return  filetxt;
        } else if (fileName.endsWith(".csv")) {
            // Lire le contenu du fichier CSV
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            String fileContent = "";
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                for (String field : fields) {
                    fileContent += field + " ";
                }
                fileContent += "\n"; // Ajouter un caractère de saut de ligne
            }
            reader.close();
            //cree le fichier txt
            String txtFileName = fileName.replaceAll("\\.csv$", newFileName+".txt");
            java.io.File txtFile = new java.io.File(txtFileName);
            try (FileWriter writer = new FileWriter(txtFile)) {
                writer.write(fileContent);
            } catch (IOException e) {
                throw new Exception("Erreur lors de l'écriture du fichier TXT!", e);
            }

            // Lire le contenu du fichier TXT
            try {
                File file2 = new File(txtFileName, "text/plain", Files.readAllBytes(txtFile.toPath()));
                file2.setData(Files.readAllBytes(txtFile.toPath()));
                file2.setFileType("text/plain");
                file2.setFileName(txtFileName);
                return file2;
            } catch (IOException e) {
                throw new Exception("Erreur lors de la lecture du fichier TXT!", e);
            }

        }
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String fileContent = stripper.getText(document);
        document.close();

        // Créer le fichier TXT
        String txtFileName = fileName.replaceAll("\\.pdf$", newFileName+".txt");
        java.io.File txtFile = new java.io.File(txtFileName);
        try (FileWriter writer = new FileWriter(txtFile)) {
            writer.write(fileContent);
        } catch (IOException e) {
            throw new Exception("Erreur lors de l'écriture du fichier TXT!", e);
        }

        // Lire le contenu du fichier TXT
        try {
            File file2 = new File(txtFileName, "text/plain",Files.readAllBytes(txtFile.toPath()));
            file2.setData(Files.readAllBytes(txtFile.toPath()));
            file2.setFileType("text/plain"); //file.getContentType()
            file2.setFileName(txtFileName);
            return file2 ;//Files.readAllBytes(txtFile.toPath())
        } catch (IOException e) {
            throw new Exception("Erreur lors de la lecture du fichier TXT!", e);
        }
    }

}






