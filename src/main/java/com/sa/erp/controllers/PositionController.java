package com.sa.erp.controllers;

import com.sa.erp.entities.Position;
import com.sa.erp.dtos.MessageErrorDto;
import com.sa.erp.services.PositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/position")
@Tag(name = "Position", description = "Position services")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Operation(summary = "Get all positions in system", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all positions registered",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Position.class)))}),
            @ApiResponse(responseCode = "204", description = "Positions not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @GetMapping("/")
    public ResponseEntity<?> getAllPositions(){
        List<Position> aux = positionService.getAllPositions();
        log.info("request from getAllPosition");
        if(!aux.isEmpty()){
            log.debug("responseBody: {} ",aux.toString());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in getAllPosition");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageErrorDto("204", "Positions no found or registered"));
        }
    }

    @Operation(summary = "Get a positions by its id", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a positions registered by its id",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Position.class)))}),
            @ApiResponse(responseCode = "204", description = "Position not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true,
            description = "id of the position to find",
            in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @GetMapping("/{id}")
    public ResponseEntity<?> getPositionBiId(@PathVariable("id")String id){
        log.info("request from getPositionById");
        log.debug("requestParam ID: {}", id);
        Optional<Position> aux = positionService.getPositionById(id);
        if(aux.isPresent()){
            log.debug("responseBody: {} ",aux.get().toString());
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            log.info("error in getPositionById");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageErrorDto("204", "Position no found or registered"));
        }
    }

    @Operation(summary = "Save a position")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Position saved",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class))}),
            @ApiResponse(responseCode = "400", description = "Position not saved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @PostMapping("/")
    public ResponseEntity<?> savePosition(@RequestBody Position position){
        Position aux = positionService.savePosition(position);
        log.info("request from savePosition");
        log.debug("requestbody: {}", position);
        if(aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            log.info("error in savePosition");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDto("400", "Position not saved"));
        }
    }

    @Operation(summary = "Update a position")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Position updated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class))}),
            @ApiResponse(responseCode = "400", description = "Position not updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true, description = "id of the position to update", in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "json of the position to update, don't change the status of the position",
            content = @Content(schema = @Schema(implementation = Position.class), mediaType = "application/json"))
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePosition(@PathVariable("id")String id, @RequestBody Position position){
        Position aux = positionService.updatePosition(id, position);
        log.info("request from getPositionById");
        log.debug("requestbody: {}, and requestParam: {}", position, id);
        if(aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in updatePosition");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDto("400", "Position not updated"));
        }
    }

    @Operation(summary = "Delete a position")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Position deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class))}),
            @ApiResponse(responseCode = "400", description = "Position not deleted",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true, description = "id of the position to delete", in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePosition(@PathVariable("id")String id){
        log.info("request from deletePosition");
        log.debug("requestbody: {}", id);
        Position aux = positionService.deletePosition(id);
        if (aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in deletePosition");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
