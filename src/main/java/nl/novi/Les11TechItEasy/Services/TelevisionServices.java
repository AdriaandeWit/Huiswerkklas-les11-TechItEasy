package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Repository.TelevisionRepository;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionServices {

    private final TelevisionRepository repos;

    public TelevisionServices(TelevisionRepository repos){
        this.repos = repos;
    }

    public List<TelevisionOutputDto> gettAllTelevisions(){
        Iterable<Television> television = repos.findAll();
        List<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        for (Television t: television) {
            TelevisionOutputDto todto = new TelevisionOutputDto();
            todto.id = t.getId();
            todto.name = t.getName();
            todto.brand = t.getBrand();
            todto.type = t.getType();


        }
        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById(Long id){
        Optional<Television> television= repos.findById(id);
        if (television.isEmpty()){
            Throw new RecordNotFoundException("Television not found");
        }
        else{
            Television t = television.get();
            TelevisionOutputDto todto =  TelevisionOutputDto(t);
            return todto
        }
    }
    public TelevisionOutputDto televisionToDto(Television t ){
        TelevisionOutputDto todto = new TelevisionOutputDto();
        todto.id = t.getId();
        todto.brand = t.getBrand();
        todto.type = t.getType();
        todto.name = t.getName();
        todto.price = t.getPrice();
        todto.availableSize = t.getAvailableSize();
        todto.refreshRate = t.getRefreshRate();
        todto.screenType = t.getScreentype();
        todto.screenQuality = t.getScreenQuality();
        todto.smartTv = t.isSmartTv();
        todto.voiceControl = t.isVoiceControl();
        todto.hdr = t.isHdr();
        todto.bluetooth = t.isBluetooth();
        todto.ambiLight = t.isAmbiLight();
        todto.originalStock = t.getOriginalStock();
        todto.sold = t.getSold();
        return todto;


    }

}
