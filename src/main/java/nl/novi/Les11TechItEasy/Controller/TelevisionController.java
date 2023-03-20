package nl.novi.Les11TechItEasy.Controller;

import jakarta.validation.Valid;
import nl.novi.Les11TechItEasy.Dto.input.IdInputDto;
import nl.novi.Les11TechItEasy.Dto.input.TelevisionDto;
import nl.novi.Les11TechItEasy.Dto.input.WallBracketDto;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Services.TelevisionService;
import nl.novi.Les11TechItEasy.Services.TelevisionWallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RequestMapping("television")
@RestController
public class TelevisionController {

@Autowired
    private final TelevisionService televisionService;

    private final TelevisionWallBracketService televisionWallBracketService;

    public TelevisionController(TelevisionService services, TelevisionWallBracketService twbService ){
        this.televisionService = services;
        this.televisionWallBracketService = twbService;
    }


    @GetMapping()
    public ResponseEntity <List<TelevisionOutputDto>>getAllTelevisions(){
        List<TelevisionOutputDto>televisionOutputDtos = televisionService.getAllTelevisions();
        return ResponseEntity.ok(televisionOutputDtos);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TelevisionOutputDto>getTelevisionById(@PathVariable Long id){
        TelevisionOutputDto televisionOutputDto = televisionService.getTelevisionById(id);
        return ResponseEntity.ok(televisionOutputDto);


    }
    @PostMapping()
    public ResponseEntity<Object> createTelevision( @RequestBody TelevisionDto dto){

        Long id = televisionService.createTelevision(dto);
        dto.id = id;

                URI uri = URI.create(ServletUriComponentsBuilder.
                        fromCurrentRequest().path("/"+ id).toUriString());

                return ResponseEntity.created(uri).body(dto);
            }


    @DeleteMapping("/{id}")
    public ResponseEntity<List<Television >> deleteById(@PathVariable Long id ){
                televisionService.deleteById(id);
                return ResponseEntity.noContent().build();
            }

    @DeleteMapping("/delete/{name}")
            public ResponseEntity<String> deleteTvByName(@PathVariable String name ){
                televisionService.deleteByName(name);
                return ResponseEntity.noContent().build();

            }

    @PutMapping("/{id}")
            public ResponseEntity<Television> updateTvList(@PathVariable Long id,@RequestParam String name ){

                televisionService.updateTvList(id,name);

                return ResponseEntity.ok().build();

            }


    @PutMapping("/televisions/{id}/{ciModuleId}")
    public void assignCIModuleToTelevision(@PathVariable("id") Long id, @PathVariable("ciModuleId") Long ciModuleId) {
        televisionService.assingCiModudleToTelevision(id, ciModuleId);
    }

    // Deze methode is om alle wallbrackets op te halen die aan een bepaalde television gekoppeld zijn.
    // Deze methode maakt gebruik van de televisionWallBracketService.
    @GetMapping("/televisions/wallBrackets/{televisionId}")
    public Collection<WallBracketDto> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId){
        return televisionWallBracketService.getWallBracketsByTelevisionId(televisionId);
    }
    }






