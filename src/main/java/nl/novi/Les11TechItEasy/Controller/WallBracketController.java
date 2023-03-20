package nl.novi.Les11TechItEasy.Controller;


import nl.novi.Les11TechItEasy.Dto.input.WallBracketDto;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import nl.novi.Les11TechItEasy.Dto.output.WallBracketOutputDto;
import nl.novi.Les11TechItEasy.Model.WallBracket;
import nl.novi.Les11TechItEasy.Services.TelevisionWallBracketService;
import nl.novi.Les11TechItEasy.Services.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RequestMapping("WallBracket")
@RestController
public class WallBracketController {
@Autowired
    private final WallBracketService wallbracketService;
    private final TelevisionWallBracketService televisionWallBracketService;

    public WallBracketController(WallBracketService service,TelevisionWallBracketService televisionWallBracketService) {
        this.wallbracketService = service;
        this.televisionWallBracketService =televisionWallBracketService;
    }

    @GetMapping()
    public ResponseEntity<List<WallBracketOutputDto>> getAllWallBrackets(){
        List<WallBracketOutputDto>wallBracketOutputDtos = wallbracketService.getAllWallBrackets();
        return ResponseEntity.ok(wallBracketOutputDtos);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<WallBracketOutputDto>getWallBracketById(@PathVariable Long id){
        WallBracketOutputDto wallBracketOutputDto = wallbracketService.getWallBracketById(id);
        return ResponseEntity.ok(wallBracketOutputDto);


    }
    @PostMapping()
    public ResponseEntity<Object> createWallBracket(@RequestBody WallBracketDto wbdto) {

        Long id = wallbracketService.createWallBracket(wbdto);
        wbdto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/"+ id).toUriString());

        return ResponseEntity.created(uri).body(wbdto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<List<WallBracket>> deleteById(@PathVariable Long id ){
        wallbracketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteTvByName(@PathVariable String name ){
        wallbracketService.deleteByName(name);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracket> updateWBList(@PathVariable Long id,@RequestParam String name ){

        wallbracketService.updateWBList(id,name);

        return ResponseEntity.ok().build();

    }
    @GetMapping("/televisions/{wallBracketId}")
    public Collection<TelevisionOutputDto> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId){
        return televisionWallBracketService.getTelevisionsByWallBracketId(wallBracketId);
    }

}
