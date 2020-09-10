package com.techpp.controller.websocket;

import java.io.IOException;

import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.util.HtmlUtils;
import com.google.gson.Gson;
import com.techpp.modal.Driver;
import com.techpp.modal.ResponseObject;
import com.techpp.modal.websocket.CommMessage;
import com.techpp.modal.websocket.DriverDetails;
import com.techpp.modal.websocket.Greeting;
import com.techpp.modal.websocket.HelloMessage;
import com.techpp.service.DriversService;
import com.techpp.service.RidesCacheManagerService;
import com.techpp.service.impl.RidesCacheManagerServiceImpl;
import com.techpp.utils.AppUtils;

@Component
public class GreetingController extends AbstractWebSocketHandler {

	RidesCacheManagerService rideCacheManagerService = new RidesCacheManagerServiceImpl();
  
	/*@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String msg = String.valueOf(message.getPayload());
		System.out.println(msg);
		if(AppUtils.isNull(rideCacheManagerService.getDriversByGroupCode(msg))) {
			session.sendMessage(new TextMessage("No drivers found at the moment"));

		}
		ResponseObject response =  rideCacheManagerService.getDriversByGroupCode(msg);
		Gson g = new Gson();
		session.sendMessage(new TextMessage(g.toJson(response)));







}

	}*/
	

  
@SubscribeMapping("/driver/{contact}")
public String subscribeToDriver(
        @DestinationVariable(value = "contact") String contact) {
   
    return contact;
}

@MessageMapping("/driver/{contact}")
@SendTo("/driver/{contact}")
public String updateRouteLocation(
        @DestinationVariable(value = "contact") String contact) {
	return contact;
	
    }
}

