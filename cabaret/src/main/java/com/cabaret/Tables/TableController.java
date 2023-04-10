package com.cabaret.Tables;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableRepository tableRepository;

    public TableController(TableRepository tableRepository){
        this.tableRepository = tableRepository;
    }

    record NewUpdateRequest(
            String tableNumber
    ){

    }
    @GetMapping
    public List<Tables> tableList(){
        return tableRepository.findAll();
    }
    @PostMapping("/")
    public Tables createTable(@RequestBody Tables tables){
        return tableRepository.save(tables);
    }
    @GetMapping("/{id}")
    public Tables getTableById(@PathVariable long id){
        return tableRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Table not found!"));

    }
    @PutMapping("{id}")
    public void updateTable(@PathVariable long id,
                            @RequestBody TableController.NewUpdateRequest request){
        Tables existingTable = tableRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Table not found!"));

        existingTable.setTableNumber(request.tableNumber());
        tableRepository.save(existingTable);
    }

    public void deleteTable(@PathVariable("id") long id){
         tableRepository.deleteById(id);
    }
}
