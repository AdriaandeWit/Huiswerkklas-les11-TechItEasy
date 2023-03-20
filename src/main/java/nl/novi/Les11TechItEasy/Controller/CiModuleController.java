package nl.novi.Les11TechItEasy.Controller;

import nl.novi.Les11TechItEasy.Dto.input.CiModuleDto;
import nl.novi.Les11TechItEasy.Dto.output.CiModuleOutputDto;
import nl.novi.Les11TechItEasy.Model.CiModule;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Services.CiModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class CiModuleController {


    private final CiModuleService ciModuleService;

    public CiModuleController(CiModuleService service) {
        this.ciModuleService = service;
    }
    @GetMapping()
    public ResponseEntity<List<CiModuleOutputDto>> getAllCiModules(){
        List<CiModuleOutputDto>ciModuleOuputDtos = ciModuleService.getAllCiModules();
        return ResponseEntity.ok(ciModuleOuputDtos);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CiModuleOutputDto>getCiModuleById(@PathVariable Long id){
        CiModuleOutputDto ciModuleOutputDto = ciModuleService.getciModuleById(id);
        return ResponseEntity.ok(ciModuleOutputDto);


    }
    @PostMapping()
    public ResponseEntity<Object> createCiModule( @RequestBody CiModuleDto cMDto){

        Long id = ciModuleService.createCiModule(cMDto);
        cMDto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/"+ id).toUriString());

        return ResponseEntity.created(uri).body(cMDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<List<CiModule>> deleteById(@PathVariable Long id ){
        ciModuleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<String> deleteCiModuleByName(@PathVariable String name ){
        ciModuleService.deleteByName(name);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateCiModuleList(@PathVariable Long id,@RequestParam String name ){

        ciModuleService.updateCiModuleList(id,name);

        return ResponseEntity.ok().build();

    }
}

