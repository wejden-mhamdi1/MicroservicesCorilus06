package tn.corilus.corilus.Services;
import org.springframework.web.multipart.MultipartFile;
import tn.corilus.corilus.Entities.File;

import java.util.List;

public interface FileService {
    public File SaveBDD(MultipartFile file,String path) throws Exception;

    // Méthode pour décrypter les données
   // byte[] decryptData(byte[] encryptedData, SecretKey secretKey) throws Exception;

    File getFileDécrypter(int fileId) throws Exception;
    File getFile(int fileId);

    boolean downloadFile(String url, String saveAsFileName);

    public File saveFile(String file,String name);
    public  File  convertirFichier(MultipartFile file) throws Exception;

    public List<String> uploadFileTW(int fileId) throws Exception;
    List<File> Getall();



    List<File> findAllFilesByUser(Integer userId);
}