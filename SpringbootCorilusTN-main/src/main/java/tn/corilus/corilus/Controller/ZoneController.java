package tn.corilus.corilus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.Segment;
import tn.corilus.corilus.Entities.Zone;
import tn.corilus.corilus.Services.ZoneService;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("zone")
public class ZoneController {
    @Autowired
    ZoneService zoneService;
    @GetMapping("/getAllZone")
    public List<Zone> getAll(){
        return zoneService.Getall();
    }
    @GetMapping("id/{id}")
    public  List<Zone>  findZoneById(@PathVariable int id){
        return zoneService.getzoneByFileId(id);
    }

}
