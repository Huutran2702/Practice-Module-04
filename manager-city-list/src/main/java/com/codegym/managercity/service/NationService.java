package com.codegym.managercity.service;

import com.codegym.managercity.model.Nation;
import com.codegym.managercity.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService implements INationService{

    @Autowired
    private NationRepository nationRepository;
    @Override
    public List<Nation> findAll() {
        return nationRepository.findAll();
    }

    @Override
    public Nation findById(Long id) {
        return nationRepository.findById(id).get();
    }

    @Override
    public Nation save(Nation nation) {
        return nationRepository.save(nation);
    }

    @Override
    public void remove(Long id) {
        nationRepository.deleteById(id);
    }
}
