package hu.cowork.advertising.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RATING_TABLE")
    @SequenceGenerator(name = "SEQ_RATING_TABLE", sequenceName = "SEQ_RATING_TABLE", allocationSize = 1, initialValue = 25)
    private Long id;

    private Long userId;

    private Integer ratingValue;

}
