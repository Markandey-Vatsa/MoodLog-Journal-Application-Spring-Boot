package net.personalProject.journalApp.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Document(collection = "config_app_journal")
public class ConfigAppCache {

    @Id
    private ObjectId id;

    private String key;
    private String value;
}
