package nl.novi.Les11TechItEasy.Repository;

import jakarta.transaction.Transactional;
import nl.novi.Les11TechItEasy.Model.WallBracket;
import nl.novi.Les11TechItEasy.Model.betweenTable.TelevisionWallBracketKey;
import nl.novi.Les11TechItEasy.Model.betweenTable.TelevisionsWallbracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface TelevisionWallBracketRepository extends JpaRepository<TelevisionsWallbracket, TelevisionWallBracketKey>
{
        // custom query om alle TelevisionWallBrackets te vinden die bij een bepaalde tv horen
        Collection<TelevisionsWallbracket> findAllByTelevisionId(Long televisionId);
        // custom query om alle TelevisionWallBrackets te vinden die bij een bepaalde wallbracket horen
        Collection<TelevisionsWallbracket> findAllByWallBracketId(Long wallBracketId);
}
