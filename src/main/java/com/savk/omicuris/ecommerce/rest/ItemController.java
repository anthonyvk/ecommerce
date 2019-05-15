package com.savk.omicuris.ecommerce.rest;

import com.savk.omicuris.ecommerce.dto.ItemDto;
import com.savk.omicuris.ecommerce.dto.StockDto;
import com.savk.omicuris.ecommerce.entity.Item;
import com.savk.omicuris.ecommerce.service.ItemService;
import com.savk.omicuris.ecommerce.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    StockService stockService;

    @GetMapping("")
    public List<ItemDto> getAllItems()   {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable("id") Long id) throws Exception   {
        return itemService.findById(id);
    }

    @PostMapping("")
    public ItemDto persistItem(@RequestBody ItemDto itemDto) throws Exception    {
        return itemService.save(itemDto);
    }

    @PutMapping("")
    public ItemDto updateItem(@RequestBody ItemDto itemDto)    {
        return itemService.save(itemDto);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) throws Exception {
        itemService.delete(id);
    }

    @PutMapping("/{id}/updateStock")
    public StockDto updateStock(@PathVariable("id") Long id, @RequestParam("value") Long value) throws Exception  {
        return stockService.updateStock(id, value);
    }

    @GetMapping("/{id}/getStock")
    public StockDto updateStock(@PathVariable("id") Long id)  {
        return stockService.getStockForItem(id);
    }
}