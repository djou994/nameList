package com.nameList.controller;

import com.nameList.model.Name;
import com.nameList.service.NamesService;
import com.nameList.service.exception.DuplicatedNameException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/names")
public class NamesController {

    private final NamesService service;

    public NamesController(NamesService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public Name create(@RequestBody Name name){
        return service.save(name);
    }

    @PostMapping()
    public List<Name> createList(@RequestBody List<Name> list) throws DuplicatedNameException {
        return service.saveList(list);
    }

    @PostMapping("/list")
    public List<Name> createFromString(@RequestBody String list) throws DuplicatedNameException {
        return service.saveListFromString(list);
    }

    @GetMapping()
    public List<Name> getAllNames(){
        return service.getAllNames();
    }

    @GetMapping("/verifiy/{name}")
    public boolean verifyIfExists(@PathVariable String name){
        return service.verifyIfExists(name);
    }
}
