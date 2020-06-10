package com.ihg.hotel.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ihg.hotel.model.WebhookRequest;
import com.ihg.hotel.model.WebhookResponse;

@Service
public class HotelFinderService {

	public WebhookResponse getHotelReservation(WebhookRequest request) {

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
					+ " with rate preference " + ratePreference + " has been confirmed.");
		}
		return response;
	}

	public WebhookResponse getLoyaltyPoints(WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		int rcNumber = request.getQueryResult().getOutputContexts().get(0).getParameters().getRcNumber().getNumber();
		response.setFulfillmentText("The rewards points with RC number " + rcNumber + " is 2500");
		return response;
	}

}
