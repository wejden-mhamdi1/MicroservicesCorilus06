package tn.corilus.corilus.Services;

import tn.corilus.corilus.Entities.Enregistrement;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.RecordFooter;
import tn.corilus.corilus.Entities.Zone;

import java.io.IOException;
import java.util.List;

public interface ZoneService {
    Zone saveAndAffectZonesans(int longeur , int debut, Enregistrement enregistrement);
     Zone saveAndAffectZone(int longeur, int debut,String nomzone,String description, Enregistrement enregistrement);
    List<String> generateZoneTxt(List<String> zones, Enregistrement enregistrement) throws IOException;

    List<Zone> Getall();
    Zone getFile(int fileId) ;
    public    List<Zone>  getzoneByFileId(int fileId);
}