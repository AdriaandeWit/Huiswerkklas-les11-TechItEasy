package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Dto.input.RemoteControllerDto;
import nl.novi.Les11TechItEasy.Dto.output.RemoteControllerOutputDto;
import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.RemoteController;
import nl.novi.Les11TechItEasy.Repository.RemoteControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    @Autowired
    private final RemoteControllerRepository repos;


    public RemoteControllerService(RemoteControllerRepository repos){
    this.repos = repos;
}
    public List<RemoteControllerOutputDto> getAllRemotControllers(){
        Iterable<RemoteController> remoteController = repos.findAll();
        List<RemoteControllerOutputDto> remoteControllerOutputDtos = new ArrayList<>();
        for (RemoteController rc: remoteController) {
            RemoteControllerOutputDto rcOdto = remoteControllerOutputDto(rc);
            remoteControllerOutputDtos.add(rcOdto);

        }
        return remoteControllerOutputDtos;
    }

    public RemoteControllerOutputDto getRemoteControllerById(Long id){
        Optional<RemoteController> remoteController= repos.findById(id);

        if (remoteController.isEmpty()){
            throw  new RecordNotFoundException("Television not found");
        }
        else{
            RemoteController rc = remoteController.get();
            RemoteControllerOutputDto rcodto =  remoteControllerOutputDto(rc);
            return rcodto;
        }
    }
    public Long createRemteController(RemoteControllerDto rcDto){
        RemoteController rc = DtoToRemoteController(rcDto);

        repos.save(rc);
        return rc.getId();

    }

    public RemoteControllerOutputDto deleteById(Long id){
        Optional<RemoteController> remoteController = repos.findById(id);
        if(remoteController.isPresent()){
            RemoteController rc = remoteController.get();
            repos.delete(rc);
        }else{
            throw new RecordNotFoundException("Television not found");

        }
        return null;
    }
    public RemoteControllerOutputDto deleteByName( String name){
        Optional<RemoteController> remoteController = repos.findByName(name);
        if(remoteController.isPresent()){
            RemoteController rc = remoteController.get();
            repos.delete(rc);
        }else
            throw  new RecordNotFoundException("Remote not found");
        return null;
    }
    public  RemoteControllerDto updateRemoteList (long id, String name){
        Optional<RemoteController> remoteController = repos.findById(id);
        if(remoteController.isPresent()){
            RemoteController rc = remoteController.get();
            rc.setName(name);
            repos.save(rc);
        }else
            throw  new RecordNotFoundException("Remote not found");
        return null;
    }


    public RemoteControllerOutputDto remoteControllerOutputDto(RemoteController rc ){
        RemoteControllerOutputDto rCODto = new RemoteControllerOutputDto();
        rCODto.id = rc.getId();
        rCODto.compatibleWith = rc.getCompatibleWith();
        rCODto.batteryType = rc.getBatteryType();
        rCODto.name = rc.getName();
        rCODto.brand = rc.getBrand();
        rCODto.price = rc.getPrice();
        rCODto.originalStock = rc.getOriginalStock();
        return rCODto;

    }
    public RemoteController DtoToRemoteController (RemoteControllerDto rCDto){
        RemoteController rC = new RemoteController();

        rC.setCompatibleWith(rCDto.compatibleWith);
        rC.setBatteryType(rCDto.batteryType);
        rC.setName(rCDto.name);
        rC.setBrand(rCDto.brand);
        rC.setPrice(rCDto.price);
        rC.setOriginalStock(rCDto.originalStock);
        return rC;
    }



}
