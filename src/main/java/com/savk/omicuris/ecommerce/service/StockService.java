package com.savk.omicuris.ecommerce.service;

import com.savk.omicuris.ecommerce.dto.StockDto;
import com.savk.omicuris.ecommerce.entity.Item;
import com.savk.omicuris.ecommerce.entity.Stock;
import com.savk.omicuris.ecommerce.repository.ItemRepository;
import com.savk.omicuris.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;

    @Autowired
    ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StockDto findById(long id) throws Exception   {
        Stock stock = stockRepository.findById(id).orElse(null);
        if(stock == null)   {
            throw new Exception("Stock not Found");
        }
        return new StockDto(stock);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StockDto save(StockDto stockDto)    {
        Stock stock = new Stock(stockDto);
        stock = stockRepository.save(stock);
        stockDto.setId(stock.getId());
        return stockDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StockDto updateStock(Long itemId, Long value) throws Exception    {
        Item item = itemRepository.findById(itemId).orElse(null);
        if(item == null)    {
            throw new Exception("Item not Found");
        }

        Stock stock = stockRepository.findStockByItemId(item.getId());
        Long currStock = 0L;
        if(stock != null)   {
            currStock = stock.getAvailable();
        }
        currStock += value;
        stock.setAvailable(currStock);
        stock = stockRepository.save(stock);
        return new StockDto(stock);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StockDto getStockForItem(Long itemId)    {
        Stock stock = stockRepository.findStockByItemId(itemId);
        return new StockDto(stock);
    }
}
