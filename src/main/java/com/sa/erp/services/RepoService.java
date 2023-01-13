package com.sa.erp.services;

import com.sa.erp.entities.Repo;
import com.sa.erp.repositories.RepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RepoService {

    @Autowired
    private RepoRepository repoRepo;

    public List<Repo> getAllRepos(){
        return repoRepo.findAll();
    }

    public Optional<Repo> getRepoByID(String id){
        return repoRepo.findById(id);
    }

    public Repo saveRepo(Repo repo){
        repo.setCreateDate(LocalDate.now());
        repo.setUpdateDate(LocalDate.now());
        return repoRepo.save(repo);
    }

    public Repo updateRepo(String id, Repo repo){
        Optional<Repo> aux = this.getRepoByID(id);
        if(aux.isPresent()){
            Repo pivot = aux.get();
            pivot.setName(repo.getName());
            pivot.setUrl(repo.getUrl());
            pivot.setUpdateDate(LocalDate.now());
            return this.saveRepo(pivot);
        }else{
            return null;
        }
    }

    public Repo deleteRepo(String id){
        Optional<Repo> aux = this.getRepoByID(id);
        if(aux.isPresent()){
            Repo pivot = aux.get();
            pivot.setEnable(false);
            pivot.setUpdateDate(LocalDate.now());
            return this.saveRepo(pivot);
        }else{
            return null;
        }
    }

}
