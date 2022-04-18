package com.a.service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.a.entity.Item;
import com.a.entity.VisitEvent;
import com.a.repository.ItemRepository;
import com.a.repository.VisitEventRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.a.model.MonthSales;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class AdminService {

    private final VisitEventRepository visitEventRepository;


    @Autowired
    public AdminService(VisitEventRepository visitEventRepository) {
        this.visitEventRepository = visitEventRepository;
    }
    
    public MonthSales  monthlySales(int month, int year) throws ParseException {
        MonthSales result = new MonthSales(month, year, new HashMap<String,Integer>());
        List<VisitEvent> visitEvents = visitEventRepository.findAll();
        
        for(VisitEvent visitEvent : visitEvents) {
        	if(visitEvent.getEventtype().equals("PURCHASE")) {
	        	int vMonth= Integer.valueOf(visitEvent.getDay().substring(0, 2));
	        	int vYear = Integer.valueOf(visitEvent.getDay().substring(6));
	        	System.out.println(vMonth + " " + vYear);
	        	System.out.println(month + " " + year);
        		if (result.getMonth() == vMonth && result.getYear() == vYear) {
        			result.incQuantity(visitEvent.getItem().getBid());
        		}
        	}
        }
		return result;
    }


}