package nl.novi.Les11TechItEasy.Model.betweenTable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class TelevisionWallBracketKey implements Serializable {

    @Column(name = "television_id")
    private Long televisionId;

    @Column(name = "wall_bracket_id")
    private Long wallBracketId;

    public TelevisionWallBracketKey (){

    }
    public TelevisionWallBracketKey(Long televisionId, Long wallBracketId){
        this.televisionId= televisionId;
        this.wallBracketId = wallBracketId;

    }
    public Long getTelevisionId() {
        return televisionId;
    }

    public void setTelevisionId(Long televisionId) {
        this.televisionId = televisionId;
    }

    public Long getWallBracketId() {
        return wallBracketId;
    }

    public void setWallBracketId(Long wallBracketId) {
        this.wallBracketId = wallBracketId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null||getClass() != o.getClass()) return false;
        TelevisionWallBracketKey that = (TelevisionWallBracketKey) o;
        return televisionId.equals(that.televisionId)&&wallBracketId.equals(that.wallBracketId);

    }

    @Override
    public int hashCode(){
        return Object.hash(televisionId,wallBracketId);

    }

}
