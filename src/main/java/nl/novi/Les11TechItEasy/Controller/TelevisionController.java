package nl.novi.Les11TechItEasy.Controller;

import nl.novi.Les11TechItEasy.Dto.input.TelevisionDto;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Services.TelevisionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("television")
@RestController
public class TelevisionController {

@Autowired
    private final TelevisionServices televisionServices;

    public TelevisionController(TelevisionServices services){
        this.televisionServices = services;
    }


    @GetMapping()
    public ResponseEntity <List<TelevisionOutputDto>>getAllTelevisions(){
        List<TelevisionOutputDto>televisionOutputDtos = televisionServices.getAllTelevisions();
        return ResponseEntity.ok(televisionOutputDtos);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TelevisionOutputDto>getTelevisionById(@PathVariable Long id){
        TelevisionOutputDto televisionOutputDto = televisionServices.getTelevisionById(id);
        return ResponseEntity.ok(televisionOutputDto);


    }
    @PostMapping()
    public ResponseEntity<Object> createTelevision( @RequestBody TelevisionDto dto){

        Long id = televisionServices.createTelevision(dto);
        dto.id = id;

                URI uri = URI.create(ServletUriComponentsBuilder.
                        fromCurrentRequest().path("/"+ id).toUriString());

                return ResponseEntity.created(uri).body(dto);
            }


    @DeleteMapping("/{id}")
    public ResponseEntity<List<Television >> deleteById(@PathVariable Long id ){
                televisionServices.deleteById(id);
                return ResponseEntity.noContent().build();
            }

    @DeleteMapping("/delete/{name}")
            public ResponseEntity<String> deleteTvByName(@PathVariable String name ){
                televisionServices.deleteByName(name);
                return ResponseEntity.noContent().build();

            }

    @PutMapping("/{id}")
            public ResponseEntity<Television> updateTvList(@PathVariable Long id,@RequestParam String name ){

                televisionServices.updateTvList(id,name);

                return ResponseEntity.ok().build();

            }
        }




