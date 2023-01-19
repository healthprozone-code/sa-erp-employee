package com.sa.erp.controllers;

import com.sa.erp.dtos.MessageErrorDto;
import com.sa.erp.entities.Repo;
import com.sa.erp.services.RepoService;
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
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/repo")
@Tag(name = "Repo", description = "Repo services")
public class RepoController {

    @Autowired
    private RepoService repoService;

    @Operation(summary = "Get all repos in system", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all repos registered",
            content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Repo.class)))}),
            @ApiResponse(responseCode = "204", description = "Positions no found or registered",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))
    })
    @GetMapping("/")
    public ResponseEntity<?> getAllRepos(){
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from getAllRepos");
        List<Repo> aux = repoService.getAllRepos();
        if(!aux.isEmpty()){
            log.debug("responseBody: {} ",aux.toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in getAllRepos");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageErrorDto("204", "Repos no found or registered"));
        }
    }

    @Operation(summary = "Get a repo by its id", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a repo registered by its id",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Repo.class)))}),
            @ApiResponse(responseCode = "204", description = "Repo not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true,
            description = "id of the repo to find",
            in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @GetMapping("/{id}")
    public ResponseEntity<?> getRepoByID(@PathVariable("id")String id){
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from getRepoByID");
        log.debug("requestParam ID: {}", id);
        Optional<Repo> aux = repoService.getRepoByID(id);
        if(aux.isPresent()){
            log.debug("responseBody: {} ",aux.get().toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in getRepoByID");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageErrorDto("204", "Repo no found or registered"));
        }
    }

    @Operation(summary = "Save a repo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Repo saved",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Repo.class))}),
            @ApiResponse(responseCode = "400", description = "Repo not saved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "json of the repo to save",
            content = @Content(schema = @Schema(implementation = Repo.class), mediaType = "application/json"))
    @PostMapping("/")
    public ResponseEntity<?> saveRepo(@RequestBody Repo repo){
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from saveRepo");
        log.debug("requestBody ID: {}", repo);
        Repo aux = repoService.saveRepo(repo);
        if(aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            log.info("error in saveRepo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDto("400", "Repo not saved"));
        }
    }

    @Operation(summary = "Update a repo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Repo updated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Repo.class))}),
            @ApiResponse(responseCode = "400", description = "Repo not updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true,
            description = "id of the repo to updated",
            in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "json of the repo to update, don't change the status",
            content = @Content(schema = @Schema(implementation = Repo.class), mediaType = "application/json"))
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRepo(@PathVariable("id")String id, @RequestBody Repo repo){
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from updateRepo");
        log.debug("requestBody ID: {}, requestParam: {}", repo, id);
        Repo aux = repoService.updateRepo(id, repo);
        if(aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in updateRepo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDto("400", "Repo not update"));
        }
    }

    @Operation(summary = "Delete a repo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Repo deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Repo.class))}),
            @ApiResponse(responseCode = "400", description = "Repo not deleted",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true,
            description = "id of the repo to delete",
            in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRepo(@PathVariable("id")String id){
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from deleteRepo");
        log.debug("requestParam: {}", id);
        Repo aux = repoService.deleteRepo(id);
        if(aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in deleteRepo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDto("400", "Repo not deleted"));
        }
    }

}
