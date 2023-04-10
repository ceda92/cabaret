package com.cabaret.Order_Item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
public class Order_ItemController {
    private final Order_itemRepository orderItemRepository;

    public Order_ItemController(Order_itemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }
    record NewUpdateRequest(
            int amount
    ){

    }


    @PostMapping("/")
    public Order_Item createItem(@RequestBody Order_Item orderItem){
        return orderItemRepository.save(orderItem);
    }

    @GetMapping
    public List<Order_Item> itemList(){
        return orderItemRepository.findAll();
    }
    @GetMapping("/{id}")
    public Order_Item findItemById(Long id){
        return orderItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item order not found!"));
    }

    @PutMapping("{id}")
    public void updateOrderItem(@PathVariable ("id") long id,
                                @RequestBody NewUpdateRequest request){
        Order_Item existingOrderItem = orderItemRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item order not found!"));
        existingOrderItem.setAmount(request.amount());
        orderItemRepository.save(existingOrderItem);
    }

    @DeleteMapping("{id}")
    public void deleteOrderItem(@PathVariable ("id") long id){
        orderItemRepository.deleteById(id);
    }

}
