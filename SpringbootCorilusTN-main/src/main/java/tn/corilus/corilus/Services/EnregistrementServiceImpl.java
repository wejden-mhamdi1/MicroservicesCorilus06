package tn.corilus.corilus.Services;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import tn.corilus.corilus.Entities.*;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Repository.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@Service

public class EnregistrementServiceImpl implements EnregistrementService{
    @PersistenceContext
    private EntityManager entityManager;

    private EnregistrementRepository enregistrementRepository;

private RecordFooterRepository recordFooterRepository;
    private ZoneRepository zoneRepository;
    private ZoneService zoneService;
    private ErreurService erreurService;
    private SegmentRepository segmentRepository;
    private SegmentService segmentService;
    private RecordFooterService recordFooterService;
    public EnregistrementServiceImpl(ErreurService erreurService,RecordFooterService recordFooterService, RecordFooterRepository recordFooterRepository, EnregistrementRepository enregistrementRepository, FileRepository fileRepository, ZoneRepository zoneRepository, ZoneService zoneService,SegmentRepository segmentRepository, SegmentService segmentService) {
        this.enregistrementRepository = enregistrementRepository;
        this.segmentRepository = segmentRepository;
        this.zoneRepository = zoneRepository;
        this.zoneService = zoneService;
        this.segmentService = segmentService;
        this.recordFooterRepository = recordFooterRepository;
        this.recordFooterService = recordFooterService;
        this.erreurService = erreurService;



    }


        @Override
    public byte[] decryptData(byte[] encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return decryptedData;
    }

    @Override
    public List<Enregistrement> getall() {
        return enregistrementRepository.findAll();
    }

    @Override
    public Enregistrement getEnregistrement(int fileId) {
        return enregistrementRepository.findById(fileId).orElse(null);
    }
    @Override
    public void segmentsave(File file) throws Exception {
        List<Segment> segmentList= new ArrayList<>();
        Segment segment2 = new Segment();
        List<String> zones = new ArrayList<>();
        segment2.setData(new String(file.getData(), "UTF-8"));
        //enregistrement2.setFichier(file);
        segment2.setFile(file);
        if(segment2.getData().substring(0,6).equals("920000")) {
            segment2.setDataSegment200(new String(file.getData(), "UTF-8").substring(0, 67));
            segment2.setDataSegment300(new String(file.getData(), "UTF-8").substring(67, 227));
        } else if (segment2.getData().substring(0, 6).equals("920900") || segment2.getData().substring(0, 6).equals("920099") || segment2.getData().substring(0, 6).equals("920999")) {
            segment2.setDataSegment200(new String(file.getData(), "UTF-8").substring(0, 67+450));
            segment2.setDataSegment300(new String(file.getData(), "UTF-8").substring(67+450, 227+450));
        }
        segmentRepository.save(segment2);

        for (Map.Entry<Integer, ZoneEmbeded> entry : segment2.getSegment200().entrySet()) {


            ZoneEmbeded value = entry.getValue();
            zones.add( segmentService.AffSegment(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(),segment2).getData());

        }

        for (Map.Entry<Integer, ZoneEmbeded> entry : segment2.getSegment300().entrySet()) {


            ZoneEmbeded value = entry.getValue();
            zones.add( segmentService.AffSegment(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(),segment2).getData());

        }
    }

    @Override
    public void recordsave(Enregistrement file) throws Exception {

    }

    @Override
    public List<String> saveAndAffect22(File file) throws Exception {

        List<Enregistrement> enregistrementList= new ArrayList<>();
        List<String> zones = new ArrayList<>();
       Enregistrement enregistrement = new Enregistrement();
       //RECORD
        RecordFooter record = new RecordFooter();


        enregistrement.setEntete(new String(file.getData(), "UTF-8").substring(0, 227));
        enregistrement.setBody(new String(file.getData(), "UTF-8").substring(227, (file.getData().length-700)));//700=350+350
        enregistrement.setFooter(new String(file.getData(), "UTF-8").substring((enregistrement.getFooter()+enregistrement.getBody()).length(), (file.getData().length)));
        enregistrement.setType(new String(file.getData(), "UTF-8").substring(0, 2));
       enregistrement.setFichier(file);
       enregistrementList.add( enregistrementRepository.save(enregistrement));

        if (enregistrement.getEntete().substring(0, 6).trim().equals("920000")) {
            System.out.println( "hello");
        int x =227;
        int y=227+350;
        for(int i=0 ;i<(new String(file.getData(), "UTF-8").length()+1-228)/350;i++) {
//Enregistrement

            Enregistrement enregistrement2 = new Enregistrement();
            enregistrement2.setFichier(file);
            enregistrement2.setData(new String(file.getData(), "UTF-8").substring(x, y));
            enregistrement2.setType(new String(file.getData(), "UTF-8").substring(x, x + 2));
            enregistrementList.add(enregistrementRepository.save(enregistrement2));
            //Segment


            switch (enregistrement2.getType()) {
                case "10":
                    enregistrement2.setDescription("donnée sur la facture");
                    for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType10().entrySet()) {

                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());
                    }
                    break;
                case "20":
                    enregistrement2.setDescription("info sur la patient");
                    for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType20().entrySet()) {

                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());
                    }
                    break;
                case "50":
                    enregistrement2.setDescription("information  sur la prestation");
                    for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType50().entrySet()) {

                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                    }
                    break;
                case "51":
                    enregistrement2.setDescription("information sur etar");
                    for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType51().entrySet()) {

                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                    }
                    break;

                case "80":
                    enregistrement2.setDescription("total des prestations ");
                    for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType80().entrySet()) {

                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                    }
                    break;

                case "90":
                    for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType90().entrySet()) {


                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                    }
                case "95":
                    for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType95().entrySet()) {


                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                    }
                case "96":
                    for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType96().entrySet()) {


                        ZoneEmbeded value = entry.getValue();
                        zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                    }
            }


            x = y;
            y = y + 350;
        }

        }
       else if (enregistrement.getEntete().substring(0, 6).trim().equals("920900") || enregistrement.getEntete().substring(0, 6).trim().equals("920099") || enregistrement.getEntete().substring(0, 6).trim().equals("920999")) {

            int x = 677;
            int y = 677 + 800;
            for (int i = 0; i < (new String(file.getData(), "UTF-8").length() + 1 - 678) / 800; i++) {

                Enregistrement enregistrement2 = new Enregistrement();
                Segment segment2 = new Segment();
                enregistrement2.setFichier(file);
                enregistrement2.setData(new String(file.getData(), "UTF-8").substring(x, y));

                enregistrement2.setType(new String(file.getData(), "UTF-8").substring(x, x + 2));
                enregistrementList.add(enregistrementRepository.save(enregistrement2));
                segment2.setData(new String(file.getData(), "UTF-8").substring(x, y));
                if (enregistrement.getEntete().substring(0, 6).trim().equals("920900")) {

                    switch (enregistrement2.getType()) {
                        case "10":
                            enregistrement2.setDescription("donnée sur la facture");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType10().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "50":
                            enregistrement2.setDescription("information  sur la prestation");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType50MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "51":
                            enregistrement2.setDescription("information sur etar");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType51MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "20":
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType20MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "80":
                            enregistrement2.setDescription("total des prestations ");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType80MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;


                        case "90":

                            for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType90MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "91":

                            for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType91().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "92":

                            for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType92().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;

                    }


                }//HBF

                else if (enregistrement.getEntete().substring(0, 6).trim().equals("920099")) {
                    System.out.println( " ");
                    switch (enregistrement2.getType()) {
                        case "10":
                            enregistrement2.setDescription("donnée sur la facture");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType10().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "50":
                            enregistrement2.setDescription("information  sur la prestation");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType50MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "51":
                            enregistrement2.setDescription("information sur etar");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType51MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "20":
                            enregistrement2.setDescription("info sur la patient");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType20MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "80":
                            enregistrement2.setDescription("total des prestations ");
                            for (Map.Entry<Integer, ZoneEmbeded> entry : enregistrement2.getType80MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;


                        case "90":

                            for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType90MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                    }
                }//HB
                else if (enregistrement.getEntete().substring(0, 6).trim().equals("920999")) {

                    switch (enregistrement2.getType()) {
                        case "95":

                            for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType95MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;
                        case "96":

                            for (Map.Entry<Integer, ZoneEmbeded> entry : record.getType96MSGOA().entrySet()) {

                                ZoneEmbeded value = entry.getValue();
                                zones.add(zoneService.saveAndAffectZone(value.getLongueur(), entry.getKey(), value.getNom_zone(), value.getDesription(), enregistrement2).getData());

                            }
                            break;

                    }


                }//HF
                x = y;
                y = y + 800;
            }
        }
        file.setStatus(Status.SPLITED);
        zoneService.generateZoneTxt(zones,enregistrement);
segmentsave(file);
erreurService.rechercherAnomalies();
        return zones;
    }
    @Override
    public File EncryptedFile(File file) throws Exception {
        // Générer la clé de cryptage
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        // Crypter les données
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(file.getData());

        // Sauvegarder les données cryptées dans la base de données
        file.setData(encryptedData);
return file;

    }










/*Testing
    public String EnregistrementType10(String ligne) throws IOException {
        FileSystemResource fileResource = new FileSystemResource(ligne);
        byte[] fileContent = FileCopyUtils.copyToByteArray(fileResource.getInputStream());
        String fileContentAsString = new String(fileContent, StandardCharsets.UTF_8);
        String  extractedLine = "";
        int[] positions = {1, 3,9,10,17,33,36,49,50,53,56,68,108,113,120,128,138,167,179,219,235,270,271,305,349};
        int[] Longueur = { 2,6,1,7,12,3,12,1,1,3,12,12,5,2,8,10,25,11,34,11,34,1,32,11,2};
        int[] positions20 = {1, 3,9,10,17,25,33,36,49,50,51,53,56,68,80,81,85,88,100,108,115,127,128,138,163,175,177,180,183,207,213,262,271,293,305,313,321,239,333,337,349};
        int[] Longueur20 = { 2,6,1,7,8,8,3,13,1,1,1,3,12,12,1,4,3,12,8,5,12,1,10,25,163,175,177,180,183,207,213,262,271,293,305,313,321,329,333,337,349};
        int[] positions21 = {1, 3,9,13,21,22,23,24,25,349};
        int[] Longueur21 = { 2,6,4,8,1,1,1,1,15,2};
        int[] positions30 = {1, 3,9,10,17,25,33,36,49,50,51,53,56,68,81,88,108,115,128,138,165,175,176,262,270,287,293,34};
        int[] Longueur30 = { 2,6,1,7,8,8,3,13,1,1,1,3,12,12,7,12,5,12,10,25,10,1,1,8,1,6,12,2};
        int[] positions40 = {1, 3,9,10,17,25,33,36,49,50,51,53,56,68,80,81,88,100,108,113,115,128,138,165,175,176,177,179,180,183,195,205,213,262,270,271,283,287,293,347,349};
        int[] Longueur40 = { 2,6,1,7,8,8,3,13,1,1,1,3,12,12,1,7,12,8,5,2,12,10,25,10,1,1,2,1,3,12,10,8,48,8,1,12,4,6,12,2,2};
        // @ElementCollection
        //Map<Integer,Integer> bla= new TreeMap.ofEntries(entry(50,12),)
        String s =fileContentAsString.substring(227,fileContentAsString.length());
        try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            switch (s.substring(0, 2)) {
                case "10":
                    for (int i = 0; i < positions.length; i++) {
                        extractedLine += s.substring(positions[i] + 1, Longueur[i] + positions[i] + 1).trim() + "\n";
                    }
                case "20" :
                    for (int i = 0; i < positions.length; i++) {
                        extractedLine += s.substring(positions20[i] + 1, Longueur20[i] + positions20[i] + 1).trim() + "\n";
                    }
                case "21" :
                    for (int i = 0; i < positions.length; i++) {
                        extractedLine += s.substring(positions21[i] + 1, Longueur21[i] + positions21[i] + 1).trim() + "\n";
                    }
                case "30" :
                    for (int i = 0; i < positions.length; i++) {
                        extractedLine += s.substring(positions30[i] + 1, Longueur30[i] + positions30[i] + 1).trim() + "\n";
                    }
                case "40" :
                    for (int i = 0; i < positions.length; i++) {
                        extractedLine += s.substring(positions40[i] + 1, Longueur40[i] + positions40[i] + 1).trim() + "\n";
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return s.substring(0, 2)+ "\n"+ extractedLine;
}
 */
}