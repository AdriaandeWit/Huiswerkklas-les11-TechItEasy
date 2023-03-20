package nl.novi.Les11TechItEasy.Services;

import nl.novi.Les11TechItEasy.Dto.input.WallBracketDto;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import nl.novi.Les11TechItEasy.Dto.output.WallBracketOutputDto;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Model.WallBracket;
import nl.novi.Les11TechItEasy.Model.betweenTable.TelevisionsWallbracket;
import nl.novi.Les11TechItEasy.Repository.TelevisionRepository;
import nl.novi.Les11TechItEasy.Repository.TelevisionWallBracketRepository;
import nl.novi.Les11TechItEasy.Repository.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class TelevisionWallBracketService {
    @Autowired
    private TelevisionRepository televisionRepository;

    @Autowired
    private WallBracketRepository wallBracketRepository;

    @Autowired
    private TelevisionWallBracketRepository televisionWallBracketRepository ;

    public Collection<TelevisionOutputDto> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionOutputDto> tODts = new HashSet<>();
        Collection<TelevisionsWallbracket> televisionWallbrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
        for (TelevisionsWallbracket televisionWallbracket : televisionWallbrackets) {
            Television television = televisionWallbracket.getTelevision();
            TelevisionOutputDto tvODto = new TelevisionOutputDto();

            tvODto
            tvODto.setType(television.getType());
            tvODto.setBrand(television.getBrand());
            tvODto.setName(television.getName());
            tvODto.setPrice(television.getPrice());
            tvODto.setAvailableSize(television.getAvailableSize());
            tvODto.setRefreshRate(television.getRefreshRate());
            tvODto.setScreenType(television.getScreenType());
            tvODto.setScreenQuality(television.getScreenQuality());
            tvODto.setSmartTv(television.getSmartTv());
            tvODto.setWifi(television.getWifi());
            tvODto.setVoiceControl(television.getVoiceControl());
            tvODto.setHdr(television.getHdr());
            tvODto.setBluetooth(television.getBluetooth());
            tvODto.setAmbiLight(television.getAmbiLight());
            tvODto.setOriginalStock(television.getOriginalStock());
            tvODto.setSold(television.getSold());

            tODts.add(tvODto);
        }
        return tODts;
    }

    public Collection<WallBracketDto> getWallBracketsByTelevisionId(Long televisionId) {
        Collection<WallBracketDto> dtos = new HashSet<>();
        Collection<TelevisionsWallbracket> televisionWallbrackets = televisionWallBracketRepository.findAllByTelevisionId(televisionId);
        for (TelevisionsWallbracket televisionWallbracket : televisionWallbrackets) {
            WallBracket wallBracket = televisionWallbracket.getWallBracket();
            var dto = new WallBracketDto();

            dto.setId(wallBracket.getId());
            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());

            dtos.add(dto);
        }
        return dtos;
    }


    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        var televisionWallBracket = new TelevisionWallBracket();
        if (!televisionRepository.existsById(televisionId)) {throw new RecordNotFoundException();}
        Television television = televisionRepository.findById(televisionId).orElse(null);
        if (!wallBracketRepository.existsById(wallBracketId)) {throw new RecordNotFoundException();}
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);
        televisionWallBracket.setTelevision(television);
        televisionWallBracket.setWallBracket(wallBracket);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        televisionWallBracket.setId(id);
        televisionWallBracketRepository.save(televisionWallBracket);
        return id;
    }
}
}
