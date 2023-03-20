package nl.novi.Les11TechItEasy.Model;

import jakarta.persistence.*;
import nl.novi.Les11TechItEasy.Model.betweenTable.TelevisionsWallbracket;

import java.util.List;

@Entity
@Table(name = "Wallbrackets")
public class WallBracket {
@Id
@GeneratedValue
    private Long id;

    private String name;

    private Double price;

    private String size;

    private Boolean ajustable;


@ManyToMany(mappedBy = "wallBrackets")
    private List<Television> televisions;

@OneToMany(mappedBy = "wallBracket" )
    private List<TelevisionsWallbracket> televisionsWallbrackets;


//---------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAjustable() {
        return ajustable;
    }

    public void setAjustable(Boolean ajustable) {
        this.ajustable = ajustable;
    }

    public List<Television> getTelevisions() {
        return televisions;
    }

    public void setTelevisions(List<Television> televisions) {
        this.televisions = televisions;
    }

    public List<TelevisionsWallbracket> getTelevisionsWallbrackets() {
        return televisionsWallbrackets;
    }

    public void setTelevisionsWallbrackets(List<TelevisionsWallbracket> televisionsWallbrackets) {
        this.televisionsWallbrackets = televisionsWallbrackets;
    }
}
