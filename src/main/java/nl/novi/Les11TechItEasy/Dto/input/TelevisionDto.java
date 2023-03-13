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
    public double price;

    public double availableSize;

    public double refreshRate;

    public String screenType;

    public String screenQuality;

    public boolean smartTv;

    public boolean wifi;

    public boolean voiceControl;

    public boolean hdr;

    public boolean bluetooth;

    public boolean ambiLight;

    public int originalStock;

    public int sold;

}
