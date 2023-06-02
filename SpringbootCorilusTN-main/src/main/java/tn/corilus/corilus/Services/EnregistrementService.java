package tn.corilus.corilus.Services;


import tn.corilus.corilus.Entities.Enregistrement;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.Zone;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public interface EnregistrementService {


   public List<String> saveAndAffect22(File file) throws Exception;
   public File EncryptedFile(File file) throws Exception;
   public byte[] decryptData(byte[] encryptedData, SecretKey secretKey) throws Exception;
   List<Enregistrement> getall();
   Enregistrement getEnregistrement(int fileId) ;


  public void segmentsave(File file) throws Exception;
   public void recordsave(Enregistrement file) throws Exception;

  // public void segmentsave(File file) throws Exception;

}
