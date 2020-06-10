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
		response.setFulfillmentText("The rewards points with RC number " + rcNumber + "  is 2500");
		return response;
	}

	public WebhookResponse getLoyaltyStatus(WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		int rcNumber = request.getQueryResult().getOutputContexts().get(0).getParameters().getRcNumber().getNumber();
		response.setFulfillmentText("Your membership with RC number " + rcNumber + " is active till 12th June 2025.");
		return response;
	}

	public WebhookResponse getLoyaltyOptions(WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		response.setFulfillmentText(
				"In IHG Reward Club/Loyalty Program, you can use your Points to book a free or discounted standard room at any of the IHG hotels and resorts worldwide, with no blackout dates.\r\n"
						+ "\r\n" + "Following Loyalty Programs are listed below:\r\n" + "\r\n" + "1. Ambassador\r\n"
						+ "2. Karma\r\n" + "3. Rewards Club");
		return response;
	}

	public WebhookResponse getReservationStatus(WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		String email = request.getQueryResult().getOutputContexts().get(0).getParameters().getReservationEnquiry()
				.getEmail();
		int rcNumber = request.getQueryResult().getOutputContexts().get(0).getParameters().getReservationEnquiry()
				.getRcNumber().getNumber();
		if (email != null) {
			response.setFulfillmentText("Reservation with email id " + email + " is confirmed");
		} else {
			response.setFulfillmentText("Reservation with RC number " + rcNumber + " is confirmed");

		}
		return response;
	}

}
