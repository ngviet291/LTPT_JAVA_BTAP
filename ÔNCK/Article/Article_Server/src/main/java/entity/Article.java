package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "articles")
public class Article {
    @Id
    @Column(name = "article_id")
    private String id;
    private String title;
    private int year;
    @Enumerated(EnumType.STRING)
    private ReviewSuggestion reviewSuggestion;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "author_id")
    private Author author;
    @OneToMany(mappedBy = "article")
    @ToString.Exclude
    private List<Review> reviews;
}
