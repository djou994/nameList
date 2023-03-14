package com.nameList.service;

import com.nameList.model.Name;
import com.nameList.repository.NamesRepository;
import com.nameList.service.exception.DuplicatedNameException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NamesService {
    private final NamesRepository repository;

    public NamesService(NamesRepository repository) {
        this.repository = repository;
    }

    /**
     * Save individual name
     *
     * @param name name to add on name's table
     * @return saved name
     */
    public Name save(Name name){
        return repository.save(name);
    }

    /**
     * Save list of names
     * @param nameList list of Names
     * @return list of saved names
     */
    public List<Name> saveList(List<Name> nameList) throws DuplicatedNameException {
        for (Name name : nameList){
            verifyList(name, nameList);
        }

        return repository.saveAll(nameList);
    }


    /**
     * Save list of names on string format like "Name1, Name2..."
     * @param list string with a list of names
     * @return list of saved names
     *
     */
    public List<Name> saveListFromString(String list) throws DuplicatedNameException {
        List<String> namesToSave = List.of(list.replace("\"","").split(", "));
        List<Name> nameList = new ArrayList<>();
        namesToSave.forEach(item -> nameList.add(new Name(item)));

        return saveList(nameList);
    }

    /**
     *
     * @return All the names on table name
     */
    public List<Name> getAllNames(){
        return repository.findAll();
    }

    /**
     *
     * @param name verified name
     * @return true if the name exists on database
     */
    public boolean verifyIfExists(String name){
        return repository.existsByNameIgnoreCase(name);
    }

    private void verifyList(Name name, List<Name> nameList) throws DuplicatedNameException {
        if (verifyIfExists(name.getName()) || Collections.frequency(nameList, name) > 1) {
            throw new DuplicatedNameException();
        }
    }
}
