package hu.cowork.advertising.repository;

import hu.cowork.advertising.entity.Advertising;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {

    Optional<Advertising> findByUserId(Long userId);

    Optional<Advertising> findById(Long id);

    List<Advertising> findAllByUserIdAndIsActive(Long userId, boolean isActive);

    List<Advertising> findAllByIsFavorite(boolean isFavorite);

    @Query("SELECT a FROM Advertising a WHERE (:id IS NULL OR a.id = :id)" +
            "AND (:userId IS NULL OR a.userId = :userId) " +
            "AND (:email IS NULL OR a.email LIKE %:email%) " +
            "AND (:text IS NULL OR a.text LIKE %:text%)" +
            "AND (:priority IS NULL OR a.priority = :priority)" +
            "AND (:address IS NULL OR a.address LIKE %:address%)" +
            "AND (:isActive IS NULL OR a.isActive = :isActive)")
    Page<Advertising> search(
            @Param("id") String id,
            @Param("userId") String userId,
            @Param("email") String email,
            @Param("text") String text,
            @Param("priority") String priority,
            @Param("address") String address,
            @Param("isActive") String isActive,
            Pageable pageable
    );
}
