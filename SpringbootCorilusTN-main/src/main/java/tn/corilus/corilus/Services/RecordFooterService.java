package tn.corilus.corilus.Services;

import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.RecordFooter;
import tn.corilus.corilus.Entities.Segment;

import java.util.List;

public interface RecordFooterService {

    List<RecordFooter> getall();
    RecordFooter getRecordFooter(int id);
    public RecordFooter AffRecord(int longeur, int debut, String nomzone, String description,RecordFooter segment);

}
