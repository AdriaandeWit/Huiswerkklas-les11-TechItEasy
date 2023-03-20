package nl.novi.Les11TechItEasy.Model;

import jakarta.persistence.*;
import nl.novi.Les11TechItEasy.Model.betweenTable.TelevisionsWallbracket;

import java.util.List;

@Entity
@Table(name = "Television")



public class Television {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String brand;
    private String type;
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private int originalStock;
    private int sold;


    @OneToOne
    private RemoteController remoteController ;

    @ManyToMany
    @JoinTable(name = "tussen_table",
            joinColumns = @JoinColumn(name = "tv_id"),
    inverseJoinColumns = @JoinColumn(name = "wb_id"))

    private List <WallBracket>  wallBrackets;

    @OneToMany(mappedBy = "television")
    private List<TelevisionsWallbracket> televisionsWallbrackets;

    @OneToOne
    private CiModule ciModule;
    public Television() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public double getAvailableSize() {
        return availableSize;
    }

    public double getRefreshRate() {
        return refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isVoiceControl() {
        return voiceControl;
    }

    public boolean isHdr() {
        return hdr;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public boolean isAmbiLight() {
        return ambiLight;
    }
    public int getOriginalStock() {
            return originalStock;
        }

    public int getSold() {
            return sold;
        }
    public RemoteController getRemoteController() {
        return remoteController;
    }

    public List<TelevisionsWallbracket> getTelevisionsWallbrackets() {
        return televisionsWallbrackets;
    }
    public CiModule getCiModule() {
        return ciModule;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableSize(double availableSize) {
        this.availableSize = availableSize;
    }

    public void setRefreshRate(double refreshRate) {
        this.refreshRate = refreshRate;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setScreenQuality(String screenQuality) {
        this.screenQuality = screenQuality;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public void setVoiceControl(boolean voiceControl) {
        this.voiceControl = voiceControl;
    }

    public void setHdr(boolean hdr) {
        this.hdr = hdr;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public void setAmbiLight(boolean ambiLight) {
        this.ambiLight = ambiLight;
    }
    public void setOriginalStock(int originalStock) {
        this.originalStock = originalStock;
    }
    public void setSold(int sold) {
        this.sold = sold;
    }

    public void setTelevisionsWallbrackets(List<TelevisionsWallbracket> televisionsWallbrackets) {
        this.televisionsWallbrackets = televisionsWallbrackets;
    }

    public void setRemoteController(RemoteController remoteController) {
        this.remoteController = remoteController;
    }

    public void setCiModule(CiModule ciModule) {
        this.ciModule = ciModule;
    }
}

