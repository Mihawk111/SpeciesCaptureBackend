package ju.soumyajit.SpeciesCaptureBackend.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.UpdateResult;
import ju.soumyajit.SpeciesCaptureBackend.model.Species;
import ju.soumyajit.SpeciesCaptureBackend.repository.config.MongoTemplateConfig;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpeciesMongoRepository implements SpeciesRepository {
    
    @Value("${species-capture-app.mongo.databases.species.collection}") private String speciesCollection;
    @Autowired @Qualifier("species") private MongoTemplate speciesMongoTemplate;
    @Autowired private ObjectMapper objectMapper;
    
    @Override
    public boolean addSpecies(Species species) {
        if(getSpeciesByScientificName(species.getScientificName()) == null) {
            speciesMongoTemplate.save(species, speciesCollection);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public Species getSpeciesByScientificName(String speciesScientificName) {
        Query query = Query.query(Criteria.where("scientificName").is(speciesScientificName));
        return speciesMongoTemplate.findOne(query, Species.class, speciesCollection);
    }
    
    @Override
    public List<Species> getSpeciesList(int limit, int skip) {
        Query query = new Query().limit(limit).skip(skip).with(Sort.by(Sort.Direction.ASC, "name"));
        return speciesMongoTemplate.find(query, Species.class, speciesCollection);
    }
    
    @Override
    public Species getSpeciesById(String id) {
        return speciesMongoTemplate.findById(id, Species.class, speciesCollection);
    }
    
    @Override
    public boolean updateSpecies(Species species) {
        Species foundSpecies = getSpeciesByScientificName(species.getScientificName());
        System.out.println("Received: " + species);
        System.out.println("Found: " + foundSpecies);
        if(foundSpecies != null && !foundSpecies.getId().equals(species.getId())) return false;
        
        try {
            Query query = Query.query(Criteria.where("id").is(species.getId()));
            Document updateDocument = Document.parse(objectMapper.writeValueAsString(species));
            updateDocument.remove("id");
            updateDocument.put("_class", Species.class.toString().substring(6));
            Update update = Update.fromDocument(updateDocument);
            UpdateResult updateResult = speciesMongoTemplate.updateFirst(query, update, Species.class, speciesCollection);
            return updateResult.getModifiedCount() > 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean removeSpecies(String speciesScientificName) {
        Query query = Query.query(Criteria.where("scientificName").is(speciesScientificName));
        return speciesMongoTemplate.remove(query, Species.class, speciesCollection).getDeletedCount() > 0;
    }
    
    @Override
    public long getCount() {
        return speciesMongoTemplate.count(new Query(), speciesCollection);
    }
}
