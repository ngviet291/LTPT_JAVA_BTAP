package dto;

import entity.ReviewSuggestion;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleDTO implements Serializable {
    private String id;
    private String title;
    private int year;
    private ReviewSuggestion reviewSuggestion;
}
