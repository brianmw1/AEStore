package com.a.service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.a.entity.Item;
import com.a.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartService {

    private final ItemRepository itemRepository;

    private Map<Item, Integer> items = new HashMap<>();

    @Autowired
    public CartService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public void addItem(Item item) {
        if (items.containsKey(item)) {
            items.replace(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    public void removeItem(Item item) {
        if (items.containsKey(item)) {
            if (items.get(item) > 1)
                items.replace(item, items.get(item) - 1);
            else if (items.get(item) == 1) {
                items.remove(item);
            }
        }
    }

    public Map<Item, Integer> getCart() {
        return items;
    }

	public Double getTotal() {
		double total = 0;
		for (Map.Entry<Item, Integer> entry : items.entrySet()) {
			int quantity = entry.getValue();
			Item item = entry.getKey();
			total += quantity * item.getPrice();
		}
		return total;
	}
}