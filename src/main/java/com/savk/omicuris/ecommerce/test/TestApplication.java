package com.savk.omicuris.ecommerce.test;

import com.savk.omicuris.ecommerce.entity.Customer;
import com.savk.omicuris.ecommerce.entity.Item;
import com.savk.omicuris.ecommerce.entity.Order;
import com.savk.omicuris.ecommerce.entity.Stock;
import com.savk.omicuris.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TestApplication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Item createRandomItem() {
        String uuid = UUID.randomUUID().toString();
        Item item = new Item();
        item.setName("Item" + uuid);
        item.setDescription("Description " + uuid);
        item.setPrice(100);
        item = itemRepository.save(item);

        //Update stock
        Stock stock = new Stock();
        stock.setItem(item);
        stock.setAvailable(77);
        stockRepository.save(stock);

        return item;
    }

    public Customer createRandomCustomer() {
        String uuid = UUID.randomUUID().toString();
        Customer customer = new Customer();
        customer.setAddress("Addr" + uuid);
        customer.setName("Item" + uuid);
        customer.setContactinfo("Cont" + uuid);
        customer.setSsn("ssn" + uuid);
//        return customer;
        return customerRepository.save(customer);
    }

    public void testCreate()    {
        Customer customer = createRandomCustomer();

        //Item with 3 entries
        Set<Item> items = new HashSet<>();
        items.add(createRandomItem());
        items.add(createRandomItem());
        items.add(createRandomItem());

        //Make a new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setDatetime(new Date());
        order.setIncart(true);
        order.setOrderEntries(new HashSet<>());
        order.setPaymentmode("CARD");
        order.setTotal(300);

        orderRepository.save(order);
    }
}
