package hu.cowork.advertising.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "advertising")
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADVERTISING_TABLE")
    @SequenceGenerator(name = "SEQ_ADVERTISING_TABLE", sequenceName = "SEQ_ADVERTISING_TABLE", allocationSize = 1, initialValue = 100)
    private Long id;

    private Long userId;

    private String text;
    
    private String email;

    private Long priority;

    private String address;

    private Boolean isActive;

    private Boolean isFavorite;

    @ElementCollection
    @CollectionTable(name = "advertising_category", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "category")
    private List<String> category;
}
