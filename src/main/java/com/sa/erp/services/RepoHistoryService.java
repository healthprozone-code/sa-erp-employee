package com.sa.erp.services;

import com.sa.erp.entities.Repo;
import com.sa.erp.entities.RepoHistory;
import com.sa.erp.repositories.RepoHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RepoHistoryService {

    @Autowired
    private RepoHistoryRepository repoHistoryRepository;

    /**
     * method to save record of repo
     * @param repo repo data to save
     */
    public void saveRepoHistory(Repo repo){
        RepoHistory aux = new RepoHistory(null, repo, LocalDate.now());
        this.repoHistoryRepository.save(aux);
    }

}
