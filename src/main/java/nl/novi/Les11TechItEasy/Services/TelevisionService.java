package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Dto.input.TelevisionDto;
import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Repository.CiModuleRepository;
import nl.novi.Les11TechItEasy.Repository.RemoteControllerRepository;
import nl.novi.Les11TechItEasy.Repository.TelevisionRepository;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
@Autowired
    private final TelevisionRepository tvRepos;

    private final RemoteControllerRepository rmRepos;

    private final CiModuleRepository cMRepos;




    public TelevisionService(TelevisionRepository tvRepos, RemoteControllerRepository rmRepos,CiModuleRepository cMRepos){
        this.tvRepos = tvRepos;
        this.rmRepos = rmRepos;
        this.cMRepos = cMRepos;
    }
//--------------------------------------------------------------------------------------------------------
    public List<TelevisionOutputDto> getAllTelevisions(){
        Iterable<Television> television = tvRepos.findAll();
        List<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();
        for (Television t: television) {
            TelevisionOutputDto todto = televisionToOutputDto(t);
            televisionOutputDtos.add(todto);

        }
        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById(Long id){
        Optional<Television> television= tvRepos.findById(id);

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

        tvRepos.save(tv);
        return tv.getId();

    }

    public TelevisionOutputDto deleteById(Long id){
        Optional<Television> television = tvRepos.findById(id);
        if(television.isPresent()){
            Television t = television.get();
            tvRepos.delete(t);
        }else{
            throw new RecordNotFoundException("Television not found");

        }
        return null;
    }
    public TelevisionOutputDto deleteByName( String name){
        Optional<Television> television = tvRepos.findByName(name);
        if(television.isPresent()){
            Television t = television.get();
            tvRepos.delete(t);
        }else
            throw  new RecordNotFoundException("Television not found");
        return null;
    }
    public  TelevisionDto updateTvList (long id, String name){
        Optional<Television> television = tvRepos.findById(id);
        if(television.isPresent()){
            Television tv = television.get();
            tv.setName(name);
            tvRepos.save(tv);
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

    public void assingRemoteControllerToTelevision(Long id,Long remoteControllerID){
        var optionalTelevision = tvRepos.findById(id);
        var optionalRemteController = rmRepos.findById(remoteControllerID);

        if (optionalTelevision.isPresent()&&optionalRemteController.isPresent()){
            var television = optionalTelevision.get();
            var remteController = optionalRemteController.get();


            television.setRemoteController(remteController);
            tvRepos.save(television);
        }else{
            throw new RecordNotFoundException();
        }

    }

    public void assingCiModudleToTelevision(Long id, Long ciModuleId){
         var optionalTelevision = tvRepos.findById(id);
         var optionalciModule = cMRepos.findById(ciModuleId);

         if (optionalTelevision.isPresent()&& optionalciModule.isPresent()){
             var television = optionalTelevision.get();
             var ciModule = optionalciModule.get();

             television.setCiModule(ciModule);
             tvRepos.save(television);
        }else{
             throw new RecordNotFoundException();



        }
    }

}
