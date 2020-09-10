package com.techpp.controller.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.techpp.modal.websocket.HelloMessage;
import com.techpp.service.RidesCacheManagerService;
import com.techpp.service.impl.RidesCacheManagerServiceImpl;

@Controller
public class WebSocketController {

	RidesCacheManagerService rideCacheManagerService = new RidesCacheManagerServiceImpl();
	Logger LGR = LogManager.getLogger(getClass().getName());
  

	@MessageMapping("/getdriverinfo/{contact}")
	@SendTo("/driver/{contact}")
	public HelloMessage updateRouteLocation(@DestinationVariable String contact, HelloMessage message) {
		LGR.info("Send driver info " + contact);
		message.setName(contact + " send some msg");
		return message;

	}
	
	@MessageMapping("/getMsg/{contact}")
	@SendTo("/driver/{contact}")
	public String stringTest(@DestinationVariable String contact, String msg) {
		LGR.info("Send driver info " + contact);
//		message.setName(contact + " send some msg");
		return "Hello " + ", contact : " + contact + ":" + msg;

	}

}

