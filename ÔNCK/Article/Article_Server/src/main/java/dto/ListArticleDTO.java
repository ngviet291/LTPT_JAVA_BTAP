package dto;

import entity.ReviewSuggestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListArticleDTO implements Serializable {
    private int year;
    private String institutionName;
    private ReviewSuggestion reviewSuggestion;
}
