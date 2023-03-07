package hu.cowork.comment.entity;

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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMMENT_TABLE")
    @SequenceGenerator(name = "SEQ_COMMENT_TABLE", sequenceName = "SEQ_COMMENT_TABLE", allocationSize = 1, initialValue = 50)
    private Long id;
    
    private Long userId;

    private Long adId;

    private String text;

}
