package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @Column(name = "review_id")
    private String id;
    private String comment;
    private String reviewer;
    @Column(name = "review_days")
    private int reviewDays;
    @Column(name = "review_date")
    private LocalDate reviewDate;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "article_id")
    private Article article;
}
