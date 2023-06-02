package tn.corilus.corilus.Services;

import tn.corilus.corilus.Entities.Enregistrement;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.Segment;

import java.util.List;

public interface SegmentService  {
    public Segment AffSegment(int longeur, int debut, String nomzone, String description,Segment segment);
    //Segment
    public void segmentsave(File file) throws Exception;
    List<Segment> getall();
    Segment getSegment(int id);
    public    List<Segment>  getSegmentByFileId(int fileId);
}
