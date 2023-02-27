package nl.novi.Les11TechItEasy.Controller;

import nl.novi.Les11TechItEasy.Exception.RecordNotFoundException;
import nl.novi.Les11TechItEasy.Model.Television;
import nl.novi.Les11TechItEasy.Repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class TelevisionController {

    @RestController
    @RequestMapping("television")
    public class TelevisionsController {

        @Autowired
        TelevisionRepository repo;


        @GetMapping()
        public ResponseEntity <List<Television>>getAllTelevisions(){
            return ResponseEntity.ok(repo.findAll());

        }

        @GetMapping("/find/{name}")
        public ResponseEntity<Television>findByName(@PathVariable String name){
            Optional<Television> optionalTv = repo.findByName(name);

            if(optionalTv.isPresent())
                return ResponseEntity.ok(optionalTv.get());
            else
                return ResponseEntity.noContent().build();

        }
        @PostMapping()
        public ResponseEntity<Television> createTelevision(@RequestBody Television t ){
            repo.save(t);

            URI uri = URI.create(ServletUriComponentsBuilder.
                    fromCurrentRequest().path("/"+ t.getId()).toUriString());

            return ResponseEntity.created(uri).body(t);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<List<Television >> deleteById(@PathVariable Long id ){
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        @DeleteMapping("/delete/{name}")
        public ResponseEntity<String> deleteTvByName(@PathVariable String name ){
            repo.deleteByName(name);
            return ResponseEntity.noContent().build();

        }


        @GetMapping("/{id}")
        public ResponseEntity<Television>getTvWithId(@PathVariable Long id) {
            Optional<Television> optionalTelevision = repo.findById(id);
            if (optionalTelevision.isPresent()) {
                Television television = optionalTelevision.get();
                return ResponseEntity.ok(television);
            } else {
                throw new RecordNotFoundException("not found");
            }
        }
        @PutMapping("/{id}")
        public ResponseEntity<Television> updateTvList(@PathVariable Long id,@RequestParam String name ){
            Optional<Television> optionalTelevision = repo.findById(id);
            if (optionalTelevision.isPresent()) {
                Television television = optionalTelevision.get();
                television.setName(name);
                return ResponseEntity.ok(television);
            } else {
                throw new RecordNotFoundException("not found");
            }
        }
}
}
