package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Dto.input.TelevisionDto;
import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Repository.TelevisionRepository;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionServices {
@Autowired
    private final TelevisionRepository repos;

    public TelevisionServices(TelevisionRepository repos){
        this.repos = repos;
    }

    public List<TelevisionOutputDto> getAllTelevisions(){
        Iterable<Television> television = repos.findAll();
        List<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        for (Television t: television) {
            TelevisionOutputDto todto = televisionToOutputDto(t);
            televisionOutputDtos.add(todto);

        }
        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById(Long id){
        Optional<Television> television= repos.findById(id);

        if (television.isEmpty()){
            throw  new RecordNotFoundException("Television not found");
        }
        else{
            Television t = television.get();
            TelevisionOutputDto todto =  televisionToOutputDto(t);
            return todto;
        }
    }
    public Long createTelevision (TelevisionDto tvDto){
        Television tv = DtoToTelevision(tvDto);

        repos.save(tv);
        return tv.getId();

    }

    public TelevisionOutputDto deleteById(Long id){
        Optional<Television> television = repos.findById(id);
        if(television.isPresent()){
            Television t = television.get();
            repos.delete(t);
        }else{
            throw new RecordNotFoundException("Television not found");

        }
        return null;
    }
    public TelevisionOutputDto deleteByName( String name){
        Optional<Television> television = repos.findByName(name);
        if(television.isPresent()){
            Television t = television.get();
            repos.delete(t);
        }else
            throw  new RecordNotFoundException("Television not found");
        return null;
    }
    public  TelevisionDto updateTvList (long id, String name){
        Optional<Television> television = repos.findById(id);
        if(television.isPresent()){
            Television tv = television.get();
            tv.setName(name);
            repos.save(tv);
        }else
            throw  new RecordNotFoundException("Television not found");
        return null;
    }


    public TelevisionOutputDto televisionToOutputDto(Television t ){
        TelevisionOutputDto todto = new TelevisionOutputDto();
        todto.id = t.getId();
        todto.brand = t.getBrand();
        todto.type = t.getType();
        todto.name = t.getName();
        todto.price = t.getPrice();
        todto.availableSize = t.getAvailableSize();
        todto.refreshRate = t.getRefreshRate();
        todto.screenType = t.getScreenType();
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
    public Television DtoToTelevision (TelevisionDto tvDto){
        Television tv = new Television();
        tv.setBrand(tvDto.brand);
        tv.setType(tvDto.type);
        tv.setName(tvDto.name);
        tv.setPrice(tvDto.price);
        tv.setAvailableSize(tvDto.availableSize);
        tv.setRefreshRate(tvDto.refreshRate);
        tv.setScreenType(tvDto.screenType);
        tv.setScreenQuality(tvDto.screenQuality);
        tv.setSmartTv(tvDto.smartTv);
        tv.setVoiceControl(tvDto.voiceControl);
        tv.setHdr(tvDto.hdr);
        tv.setBluetooth(tvDto.bluetooth);
        tv.setAmbiLight(tvDto.ambiLight);
        tv.setOriginalStock(tvDto.originalStock);
        tv.setSold(tvDto.sold);
        return tv;
    }

}
