package ju.soumyajit.SpeciesCaptureBackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
@CompoundIndexes({})  // Add as needed ...
public class Species {

    @Id private String id;
    @Indexed() String name;
    @Indexed(unique = true) String scientificName;
    @Indexed() String description;

}
