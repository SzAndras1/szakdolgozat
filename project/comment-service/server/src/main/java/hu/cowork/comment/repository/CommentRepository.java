package hu.cowork.comment.repository;

import hu.cowork.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    Optional<Comment> findByUserId(Long userId);

    @Query("SELECT c FROM Comment c WHERE (:id IS NULL OR c.id = :id) " +
            "AND (:userId IS NULL OR c.userId = :userId) " +
            "AND (:text IS NULL OR c.text LIKE '%:text%')")
    Page<Comment> search(
            @Param("id") String id,
            @Param("userId") String userId,
            @Param("text") String text,
            Pageable pageable
    );
}
