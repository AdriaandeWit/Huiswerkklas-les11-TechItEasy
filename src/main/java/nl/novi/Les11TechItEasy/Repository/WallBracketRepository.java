package nl.novi.Les11TechItEasy.Repository;

import jakarta.transaction.Transactional;
import nl.novi.Les11TechItEasy.Model.RemoteController;
import nl.novi.Les11TechItEasy.Model.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WallBracketRepository extends JpaRepository<WallBracket,Long> {
    @Transactional
    @Modifying
    @Query("delete from RemoteController t where t.name = :name" )
    Long deleteByName(@Param("name")String name);
    Optional<WallBracket> findByName(String name);
}
