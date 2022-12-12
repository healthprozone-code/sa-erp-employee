package com.example.saerpemployee.services;

import com.example.saerpemployee.documents.Position;
import com.example.saerpemployee.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepo;

    public List<Position> getAllPositions(){
        return positionRepo.findAll();
    }

    public Optional<Position> getPositionById(String id){
        return positionRepo.findById(id);
    }

    public Position savePosition(Position position){
        return positionRepo.save(position);
    }

    public Position updatePosition(String id, Position position){
        Optional<Position> aux = this.getPositionById(id);
        if(aux.isPresent()){
            Position pivot = aux.get();
            pivot.setName(position.getName());
            return this.savePosition(pivot);
        }
        return null;
    }

    public Position deletePosition(String id){
        Optional<Position> aux = this.getPositionById(id);
        if(aux.isPresent()){
            Position pivot = aux.get();
            pivot.setEnable(false);
            return this.savePosition(pivot);
        }
        return null;
    }


}
