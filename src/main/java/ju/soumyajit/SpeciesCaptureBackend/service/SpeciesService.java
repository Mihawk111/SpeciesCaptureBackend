package ju.soumyajit.SpeciesCaptureBackend.service;

import ju.soumyajit.SpeciesCaptureBackend.model.Species;
import ju.soumyajit.SpeciesCaptureBackend.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {
    
    @Autowired
    private SpeciesRepository speciesRepository;
    
    public boolean addSpecies(Species species) {
        return speciesRepository.addSpecies(species);
    }
    
    public Species getSpeciesByScientificName(String speciesScientificName) {
        return speciesRepository.getSpeciesByScientificName(speciesScientificName);
    }
    
    public List<Species> getSpeciesList(int limit, int skip) {
        return speciesRepository.getSpeciesList(limit, skip);
    }
    
    public Species getSpeciesById(String id) {
        return speciesRepository.getSpeciesById(id);
    }
    
    public boolean updateSpecies(Species species) {
        return speciesRepository.updateSpecies(species);
    }
    
    public boolean removeSpecies(String speciesScientificName) {
        return speciesRepository.removeSpecies(speciesScientificName);
    }
    
    public long getCount() {
        return speciesRepository.getCount();
    }
    
}