package ma.marnissiabdelkarim.ecommebackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.marnissiabdelkarim.ecommebackend.services.OrderItemService;

@RestController
@RequestMapping("/orderItem") 
public class OrderItemController {
	
	@Autowired
	OrderItemService orderItemService;
	
	

}
