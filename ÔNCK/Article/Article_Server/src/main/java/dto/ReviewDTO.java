package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDTO implements Serializable {
    private String id;
    private String comment;
    private String reviewer;
    private int reviewDays;
    private LocalDate reviewDate;
    private ArticleDTO articleDTO;
}
