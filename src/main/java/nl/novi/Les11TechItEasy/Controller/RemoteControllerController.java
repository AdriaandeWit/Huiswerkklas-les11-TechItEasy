package nl.novi.Les11TechItEasy.Controller;

import nl.novi.Les11TechItEasy.Dto.input.RemoteControllerDto;
import nl.novi.Les11TechItEasy.Dto.output.RemoteControllerOutputDto;
import nl.novi.Les11TechItEasy.Dto.output.TelevisionOutputDto;
import nl.novi.Les11TechItEasy.Model.RemoteController;
import nl.novi.Les11TechItEasy.Services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("remotecontroller")
@RestController
public class RemoteControllerController {

    @Autowired

    private final RemoteControllerService remoteControllerService;


    public RemoteControllerController(RemoteControllerService service) {
        this.remoteControllerService = service;
    }


    @GetMapping()
        public ResponseEntity<List<RemoteControllerOutputDto>> getAllRemoteControllers(){
            List<RemoteControllerOutputDto>remoteControllerOutputDtos = remoteControllerService.getAllRemotControllers();
            return ResponseEntity.ok(remoteControllerOutputDtos);

        }

        @GetMapping("/find/{id}")
        public ResponseEntity<RemoteControllerOutputDto>getRemoteControllerById(@PathVariable Long id){
            RemoteControllerOutputDto remoteControllerOutputDto = remoteControllerService.getRemoteControllerById(id);
            return ResponseEntity.ok(remoteControllerOutputDto);


        }
        @PostMapping()
        public ResponseEntity<Object> createRemote( @RequestBody RemoteControllerDto rDto){

            Long id = remoteControllerService.createRemteController(rDto);
            rDto.id = id;

            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/"+ id).toUriString());

            return ResponseEntity.created(uri).body(rDto);
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<List<RemoteController>> deleteById(@PathVariable Long id ){
            remoteControllerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/delete/{name}")
        public ResponseEntity<String> deleteTvByName(@PathVariable String name ){
            remoteControllerService.deleteByName(name);
            return ResponseEntity.noContent().build();

        }

        @PutMapping("/{id}")
        public ResponseEntity<RemoteController> updateRemoteList(@PathVariable Long id,@RequestParam String name ){

            remoteControllerService.updateRemoteList(id,name);

            return ResponseEntity.ok().build();

        }
    }



