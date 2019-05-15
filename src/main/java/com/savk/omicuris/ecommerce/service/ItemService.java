package com.savk.omicuris.ecommerce.service;

import com.savk.omicuris.ecommerce.dto.CustomerDto;
import com.savk.omicuris.ecommerce.dto.ItemDto;
import com.savk.omicuris.ecommerce.entity.Customer;
import com.savk.omicuris.ecommerce.entity.Item;
import com.savk.omicuris.ecommerce.entity.Stock;
import com.savk.omicuris.ecommerce.repository.ItemRepository;
import com.savk.omicuris.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ItemDto findById(long id) throws Exception   {
        Item item = itemRepository.findById(id).orElse(null);
        if(item == null)    {
            throw new Exception("Item not Found");
        }
        return new ItemDto(item);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ItemDto> findAll()    {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<>();
        for(Item item : items)  {
            itemDtos.add(new ItemDto(item));
        }
        return itemDtos;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ItemDto save(ItemDto itemDto) {      //TODO : Handle update differently
        Item item = new Item(itemDto);
        item = itemRepository.save(item);
        itemDto.setId(item.getId());

        Stock stock = stockRepository.findStockByItemId(item.getId());
        if(stock == null)   {
            stock = new Stock();
            stock.setAvailable(0);
            stock.setItem(item);
            stockRepository.save(stock);
        }
        return itemDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long id) throws Exception {
        Item item = itemRepository.findById(id).orElse(null);
        if(item == null)    {
            throw new Exception("Item not Found");
        }
        Stock stock = stockRepository.findStockByItemId(id);
        stockRepository.delete(stock);
        itemRepository.delete(item);
    }
}
