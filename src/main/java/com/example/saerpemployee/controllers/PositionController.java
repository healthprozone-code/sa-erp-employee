package com.example.saerpemployee.controllers;

import com.example.saerpemployee.documents.Position;
import com.example.saerpemployee.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public ResponseEntity<?> getAllPositions(){
        List<Position> aux = positionService.getAllPositions();
        if(!aux.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPositionBiId(@PathVariable("id")String id){
        Optional<Position> aux = positionService.getPositionById(id);
        if(aux.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> savePosition(@RequestBody Position position){
        Position aux = positionService.savePosition(position);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePosition(@PathVariable("id")String id, @RequestBody Position position){
        Position aux = positionService.updatePosition(id, position);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable("id")String id){
        Position aux = positionService.deletePosition(id);
        if (aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
