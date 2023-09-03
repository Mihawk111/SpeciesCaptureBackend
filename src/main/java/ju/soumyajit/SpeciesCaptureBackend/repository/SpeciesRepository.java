package ju.soumyajit.SpeciesCaptureBackend.repository;


import ju.soumyajit.SpeciesCaptureBackend.model.Species;

import java.util.List;

public interface SpeciesRepository {

    boolean addSpecies(Species species);
    Species getSpeciesByScientificName(String speciesScientificName);
    Species getSpeciesById(String id);
    List<Species> getSpeciesList(int limit, int skip);
    boolean updateSpecies(Species species);
    boolean removeSpecies(String speciesScientificName);
    long getCount();

}
