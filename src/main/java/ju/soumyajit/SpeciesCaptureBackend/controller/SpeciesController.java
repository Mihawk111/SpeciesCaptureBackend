package ju.soumyajit.SpeciesCaptureBackend.controller;

import ju.soumyajit.SpeciesCaptureBackend.model.Species;
import ju.soumyajit.SpeciesCaptureBackend.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    
    @Autowired private SpeciesService speciesService;
    
    @PostMapping("/add")
    public boolean addSpecies(@RequestBody Species species){
        return speciesService.addSpecies(species);
    }
    
    @GetMapping("/get")
    public Species getSpeciesByScientificName(@RequestParam String speciesScientificName){
        return speciesService.getSpeciesByScientificName(speciesScientificName);
    }
    
    @GetMapping("/get_all")
    public List<Species> getSpeciesList(@RequestParam int limit, @RequestParam int skip) {
        return speciesService.getSpeciesList(limit, skip);
    }
    
    @GetMapping("/get_by_id")
    public Species getSpeciesById(@RequestParam String id){
        return speciesService.getSpeciesById(id);
    }
    
    @PostMapping("/update")
    public boolean updateSpecies(@RequestBody Species species){
        return speciesService.updateSpecies(species);
    }
    
    @PostMapping("/remove")
    public boolean removeSpecies(@RequestBody String speciesScientificName){
        return speciesService.removeSpecies(speciesScientificName);
    }
    
    @GetMapping("/count")
    public long getCount(){
        return speciesService.getCount();
    }
    
}
