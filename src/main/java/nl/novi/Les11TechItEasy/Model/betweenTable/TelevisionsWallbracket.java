package nl.novi.Les11TechItEasy.Model.betweenTable;

import jakarta.persistence.*;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Model.WallBracket;

@Entity
public class TelevisionsWallbracket {
    @EmbeddedId
    private TelevisionWallBracketKey id;
    @ManyToOne(fetch = FetchType.LAZY )
    @MapsId("televisionId")
    @JoinColumn(name = "television_id")
    private Television television;

    @ManyToOne
    @MapsId("wallBracketId")
    @JoinColumn(name ="wall_bracket_id"  )
    private WallBracket wallBracket;


    public TelevisionWallBracketKey getId() {
        return id;
    }

    public void setId(TelevisionWallBracketKey id) {
        this.id = id;
    }

    public WallBracket getWallBracket() {
        return wallBracket;
    }

    public void setWallBracket(WallBracket wallBracket) {
        this.wallBracket = wallBracket;
    }

    public Television getTelevision() {
        return television;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }
}
