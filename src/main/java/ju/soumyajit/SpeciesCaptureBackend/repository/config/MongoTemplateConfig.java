package ju.soumyajit.SpeciesCaptureBackend.repository.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Getter
public class MongoTemplateConfig {
    
    @Value("${species-capture-app.mongo.database-url}") private String mongoUrl;
    @Value("${species-capture-app.mongo.database-name}") private String databaseName;
    @Value("${species-capture-app.mongo.pool-max-size}") private int poolMaxSize;
    @Value("${species-capture-app.mongo.pool-min-size}") private int poolMixSize;
    @Value("${species-capture-app.mongo.databases.species.collection}") private String speciesCollection;
    @Value("${species-capture-app.mongo.databases.contribution.collection}") private String contributionCollection;
    
    private MongoClient mongoClient() {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(mongoUrl))
            .applyToConnectionPoolSettings(builder -> {
                builder.maxSize(poolMaxSize);
                builder.minSize(poolMixSize);
            })
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }

    @Bean @Qualifier("species")
    public MongoTemplate SpeciesMongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }

}
