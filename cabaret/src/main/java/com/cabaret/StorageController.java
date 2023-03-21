package com.cabaret;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageRepository storageRepository;

    public StorageController(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }
    record NewUpdateRequest(
            String name,
            String type,
            Integer amount
    ){

    }
    @PostMapping("/")
    public Storage createStorage(@RequestBody Storage storage){
        return storageRepository.save(storage);
    }
    @GetMapping
    public List<Storage>storageList(){
        return storageRepository.findAll();
    }
    @GetMapping("/{id}")
    public  Storage findItemById(@PathVariable Integer id){
        return storageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Storage item not found"));
    }
    @PutMapping("{id}")
    public void updateStorage(
            @PathVariable("id") Integer id,
            @RequestBody StorageController.NewUpdateRequest request
    ){
        Storage existingStorage = storageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Storage item not found"));
        existingStorage.setName(request.name());
        existingStorage.setType(request.type());
        existingStorage.setAmount(request.amount());
        storageRepository.save(existingStorage );
    }
    @DeleteMapping("{id}")
    public void deleteStorage(@PathVariable("id") Integer id){
        storageRepository.deleteById(id);
    }
}
