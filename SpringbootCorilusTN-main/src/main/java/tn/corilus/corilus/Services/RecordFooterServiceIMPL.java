package tn.corilus.corilus.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.RecordFooter;
import tn.corilus.corilus.Entities.Segment;
import tn.corilus.corilus.Entities.ZoneEmbeded;
import tn.corilus.corilus.Repository.RecordFooterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service


public class RecordFooterServiceIMPL implements RecordFooterService{
    @Autowired
    RecordFooterRepository recordrep;




    @Override
    public List<RecordFooter> getall() {
        return null;
    }

    @Override
    public RecordFooter getRecordFooter(int id) {
        return null;
    }

    @Override
    public RecordFooter AffRecord(int longeur, int debut, String nomzone, String description, RecordFooter segment2) {
        RecordFooter segment = new RecordFooter();
        String sub = segment2.getData().substring(debut - 1, longeur + debut - 1);
        //  if (sub == null || sub.trim().isEmpty()) {return null;}

        segment.setData(sub);
        segment.setDescription(description);

        return recordrep.save(segment);
    }

}
