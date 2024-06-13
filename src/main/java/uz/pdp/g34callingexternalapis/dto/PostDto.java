package uz.pdp.g34callingexternalapis.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private String id;
    private Long userId;
    private String title;
    private String body;
    List<CommentDto> comments;
}
