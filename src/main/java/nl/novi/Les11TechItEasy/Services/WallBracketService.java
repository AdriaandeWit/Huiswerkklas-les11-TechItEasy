package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Dto.input.TelevisionDto;
import nl.novi.Les11TechItEasy.Dto.input.WallBracketDto;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import nl.novi.Les11TechItEasy.Dto.output.WallBracketOutputDto;
import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Model.WallBracket;
import nl.novi.Les11TechItEasy.Repository.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {
    @Autowired
    private final WallBracketRepository repos;


    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }

    public List<WallBracketOutputDto> getAllWallBrackets(){
        Iterable<WallBracket> wallBracket = repos.findAll();
        List<WallBracketOutputDto> wallBracketOutputDtos = new ArrayList<>();
        for (WallBracket wb: wallBracket) {
            WallBracketOutputDto wbodto = wallBracketOutputDto(wb);
            wallBracketOutputDtos.add(wbodto);

        }
        return wallBracketOutputDtos;
    }

    public WallBracketOutputDto getWallBracketById(Long id){
        Optional<WallBracket> wallBracket= repos.findById(id);

        if (wallBracket.isEmpty()){
            throw  new RecordNotFoundException("Wallbracket not found");
        }
        else{
            WallBracket wb = wallBracket.get();
            WallBracketOutputDto wbodto  =  wallBracketOutputDto(wb);
            return wbodto;
        }
    }
    public Long createWallBracket (WallBracketDto wbDto){
        WallBracket wb = DtoToWallBracket(wbDto);

        repos.save(wb);
        return wb.getId();

    }

    public WallBracketOutputDto deleteById(Long id){
        Optional<WallBracket> wallBracket = repos.findById(id);
        if(wallBracket.isPresent()){
            WallBracket wb = wallBracket.get();
            repos.delete(wb);
        }else{
            throw new RecordNotFoundException("wallbracket not found");

        }
        return null;
    }
    public WallBracketOutputDto deleteByName( String name){
        Optional<WallBracket> wallBracket = repos.findByName(name);
        if(wallBracket.isPresent()){
            WallBracket wb  = wallBracket.get();
            repos.delete(wb);
        }else
            throw  new RecordNotFoundException("Wallbracket not found");
        return null;
    }
    public  WallBracketDto updateWBList (long id, String name){
        Optional<WallBracket> wallBracket = repos.findById(id);
        if(wallBracket.isPresent()){
            WallBracket wb = wallBracket.get();
            wb.setName(name);
            repos.save(wb);
        }else
            throw  new RecordNotFoundException("Wallbracket not found");
        return null;
    }
    public WallBracketOutputDto wallBracketOutputDto(WallBracket wb){
        WallBracketOutputDto WBodto = new WallBracketOutputDto();
        WBodto.id = wb.getId();
        WBodto.name = wb.getName();
        WBodto.price = wb.getPrice();
        WBodto.size = wb.getSize();
        WBodto.ajustable = wb.getAjustable();
        return WBodto;
    }
    public WallBracket DtoToWallBracket (WallBracketDto WBdto){
        WallBracket wb = new WallBracket();
        wb.setName(WBdto.name);
        wb.setPrice(WBdto.price);
        wb.setSize(WBdto.size);
        wb.setAjustable(WBdto.ajustable);
        return wb;
    }





}
