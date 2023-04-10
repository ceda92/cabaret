package com.cabaret.Orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository ;

    public OrderController (OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    record NewUpdateRequest(
            Boolean isActive,
            LocalDateTime dateTime
    ){

    }
    @PostMapping("/")
    public Orders createOrder(@RequestBody Orders orders){
     return  orderRepository.save(orders);

    }

    @GetMapping
   public List<Orders> ordersList(){
        return orderRepository.findAll();
    }
    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable long id){
        return orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found!"));
    }

    @PutMapping("{id}")
    public void updateOrder(@PathVariable("id")long id,
                            @RequestBody OrderController.NewUpdateRequest request
    ){
        Orders existingOrder = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found!"));
        existingOrder.setActive(request.isActive());
        existingOrder.setDateTime(request.dateTime());
        orderRepository.save(existingOrder);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long id)
    {
        orderRepository.deleteById(id);
    }

}
