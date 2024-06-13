package uz.pdp.g34callingexternalapis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("posts")
@Getter
@Setter
public class Post {
    @Id
    @JsonIgnore
    private String id;

    @JsonProperty(value = "id")
    private Long genId;

    private Long userId;

    private String title;

    private String body;
}
