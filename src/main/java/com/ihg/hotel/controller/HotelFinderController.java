package com.ihg.hotel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ihg.hotel.model.WebhookRequest;
import com.ihg.hotel.model.WebhookResponse;

public class HotelFinderController {
	
	@PostMapping(path = "/webhook")
	public WebhookResponse webhook(@RequestBody WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		
		String city = request.getQueryResult().getParameters().get("city").toString();
		String checkIn = request.getQueryResult().getParameters().get("checkIn").toString();
		String checkOut = request.getQueryResult().getParameters().get("checkOut").toString();
		String adults = request.getQueryResult().getParameters().get("adults").toString();
		String children = request.getQueryResult().getParameters().get("children").toString();
		String rooms = request.getQueryResult().getParameters().get("rooms").toString();
		
		response.setFulfillmentText("Your request for "+rooms+" room(s) for "+adults+" adult(s) and "+children+
				" child(ren) from "+checkIn+" to "+checkOut+" in "+city+" has been confirmed.");
		return response ;
	}

}
