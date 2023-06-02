package tn.corilus.corilus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.corilus.corilus.Entities.Enregistrement;
import tn.corilus.corilus.Entities.Segment;
import tn.corilus.corilus.Entities.Zone;
import tn.corilus.corilus.Repository.SegmentRepository;
import tn.corilus.corilus.Services.SegmentService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SegmentController {
    @Autowired
    SegmentService segmentService;
    @Autowired
    SegmentRepository segmentRepository;

   @GetMapping("/getAllSEGMENT")



    public List<Segment> getAll(){
        return segmentService.getall();
    }

    @GetMapping("id/{id}")
    public  List<Segment>  findSegmentById(@PathVariable int id){

        return segmentService.getSegmentByFileId(id);

    }




}
