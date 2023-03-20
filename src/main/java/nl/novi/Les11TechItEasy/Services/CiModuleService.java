package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Dto.input.CiModuleDto;
import nl.novi.Les11TechItEasy.Dto.output.CiModuleOutputDto;
import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.CiModule;
import nl.novi.Les11TechItEasy.Repository.CiModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CiModuleService {
    @Autowired
    private final CiModuleRepository repos;


    public CiModuleService(CiModuleRepository repos) {
        this.repos = repos;
    }
    public List<CiModuleOutputDto> getAllCiModules(){
        Iterable<CiModule> ciModule = repos.findAll();
        List<CiModuleOutputDto> ciModuleOuputDtos = new ArrayList<>();
        for (CiModule ciM : ciModule) {
            CiModuleOutputDto ciMODto = ciModuleToOutputDto(ciM);
            ciModuleOuputDtos.add(ciMODto);

        }
        return ciModuleOuputDtos;
    }

    public CiModuleOutputDto getciModuleById(Long id){
        Optional<CiModule> ciModule= repos.findById(id);

        if (ciModule.isEmpty()){
            throw  new RecordNotFoundException("Ci- module not found");
        }
        else{
            CiModule ciM = ciModule.get();
            CiModuleOutputDto cMODto = ciModuleToOutputDto(ciM);
            return cMODto;
        }
    }
    public Long createCiModule(CiModuleDto cMDto){
        CiModule cM = DtoToCiModule(cMDto);

        repos.save(cM);
        return cM.getId();

    }

    public CiModuleOutputDto deleteById(Long id){
        Optional<CiModule> ciModule = repos.findById(id);
        if(ciModule.isPresent()){
            CiModule cM = ciModule.get();
            repos.delete(cM);
        }else{
            throw new RecordNotFoundException("Ci- module not found");

        }
        return null;
    }
    public CiModuleOutputDto deleteByName(String name){
        Optional<CiModule> ciModule = repos.findByName(name);
        if(ciModule.isPresent()){
            CiModule cM = ciModule.get();
            repos.delete(cM);
        }else
            throw  new RecordNotFoundException("Ci- module not found");
        return null;
    }
    public  CiModuleDto updateCiModuleList (long id, String name){
        Optional<CiModule> ciModule = repos.findById(id);
        if(ciModule.isPresent()){
            CiModule cM = ciModule.get();
            cM.setName(name);
            repos.save(cM);
        }else
            throw  new RecordNotFoundException("Ci- module not found");
        return null;
    }

    public CiModuleOutputDto ciModuleToOutputDto(CiModule cM){
        CiModuleOutputDto cMODto = new CiModuleOutputDto();
        cMODto.id = cM.getId();
        cMODto.name = cM.getName();
        cMODto.type = cM.getType();
        cMODto.price = cM.getPrice();
        return cMODto;
    }
    public CiModule DtoToCiModule(CiModuleDto cMDto){
        CiModule cM = new CiModule();
        cM.setName(cMDto.name);
        cM.setType(cMDto.type);
        cM.setPrice(cMDto.price);
        return cM;
    }

}
