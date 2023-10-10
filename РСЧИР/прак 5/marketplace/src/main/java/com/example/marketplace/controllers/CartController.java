package com.example.marketplace.controllers;

import com.example.marketplace.models.Item;
import com.example.marketplace.models.Warehouse;
import com.example.marketplace.repository.BooksRepository;
import com.example.marketplace.repository.PhonesRepository;
import com.example.marketplace.repository.WarehouseRepository;
import com.example.marketplace.repository.WashingMachinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    private Map<Item, Integer> items = new HashMap();
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private PhonesRepository phonesRepository;
    @Autowired
    private WashingMachinesRepository washingMachinesRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<Item, Integer>> getAll() {
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Item item){
        Warehouse warehouse = warehouseRepository.findByIdProductAndTypeOfProduct(item.getIdProduct(), item.getTypeOfProduct());
        if (warehouse.getAmount() > 0){
            if (items.containsKey(item)) {
                if (warehouse.getAmount() >= items.get(item) + 1)  items.put(item, items.get(item) + 1);
                else return ResponseEntity.ok("This item is out of stock");;
            }
            else items.put(item, 1);
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.ok("This item is out of stock");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> remove(@RequestBody Item item){
        if (items.containsKey(item)){
            if (items.get(item) == 1) items.remove(item);
            else items.put(item, items.get(item) - 1);
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.ok("Item is not in the cart");
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clear(){
        items.clear();
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/order")
    public ResponseEntity<String> order(){
        int summa = 0;
        for (Item item: items.keySet()) {
            Warehouse warehouse = warehouseRepository.findByIdProductAndTypeOfProduct(item.getIdProduct(), item.getTypeOfProduct());
            if (warehouse.getAmount() < items.get(item)) return ResponseEntity.ok("We don't have enough items");
            switch (item.getTypeOfProduct()){
                case "Book":
                    summa += booksRepository.findById(item.getIdProduct()).get().getPrice();
                    break;
                case "Phone":
                    summa += phonesRepository.findById(item.getIdProduct()).get().getPrice();
                    break;
                case "WM":
                    summa += washingMachinesRepository.findById(item.getIdProduct()).get().getPrice();
                    break;
            }
        }
        items.clear();
        return ResponseEntity.ok("Price = " + summa);
    }

}