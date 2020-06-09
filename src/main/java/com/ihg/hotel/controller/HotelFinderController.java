package com.ihg.hotel.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihg.hotel.model.WebhookRequest;
import com.ihg.hotel.model.WebhookResponse;

@RestController
public class HotelFinderController {

	@PostMapping(path = "/webhook")
	public WebhookResponse webhook(@RequestBody WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();

		String city = request.getQueryResult().getParameters().getCity();
		Date checkIn = request.getQueryResult().getParameters().getCheckIn();
		Date checkOut = request.getQueryResult().getParameters().getCheckOut();
		int adults = request.getQueryResult().getParameters().getAdults();
		int children = request.getQueryResult().getParameters().getChildren();
		int rooms = request.getQueryResult().getParameters().getRooms();
		String ratePreference = request.getQueryResult().getParameters().getRatePreference();

		if (checkIn.after(checkOut)) {
			response.setFulfillmentText("CheckIn date cannot be after the checkOut date");
		} else {

			response.setFulfillmentText("Your request for " + rooms + " room(s) for " + adults + " adult(s) and "
					+ children + " child(ren) from " + checkIn + " to " + checkOut + " in " + city
					+ "with rate preference " + ratePreference + " has been confirmed.");
		}
		return response;
	}

}
