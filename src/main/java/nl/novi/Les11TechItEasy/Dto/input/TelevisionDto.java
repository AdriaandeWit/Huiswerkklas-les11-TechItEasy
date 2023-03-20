package nl.novi.Les11TechItEasy.Dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TelevisionDto {
    public Long id;

    @Size(min = 3, max = 255)
    public String name;
    @Size(min = 3, max = 255)
    public String brand;
    @Size(min = 3, max = 255)
    public String type;
    @NotNull
    public Double price;

    public Double availableSize;

    public Double refreshRate;

    public String screenType;

    public String screenQuality;

    public Boolean smartTv;

    public Boolean wifi;

    public Boolean voiceControl;

    public Boolean hdr;

    public Boolean bluetooth;

    public Boolean ambiLight;

    public int originalStock;

    public int sold;

}
