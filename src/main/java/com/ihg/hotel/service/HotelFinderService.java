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
		if (rcNumber == 1001) {
			response.setFulfillmentText("Hi Akshay, Your reward points are 23000");
		} else if (rcNumber == 1002) {
			response.setFulfillmentText("Hi Mohit, Your reward points are 20000");
		} else
			response.setFulfillmentText("The rewards points with RC number " + rcNumber + "  is 12000");
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
				"At IHG we offer the following reward clubs loyalty programs listed below:\r\n  1. Rewards Club\r\n"
						+ "2. Ambassador\r\n" + "3. Business Rewards Club\r\n"+"4. Karma\r\n");
		return response;
	}

	public WebhookResponse getReservationStatus(WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		String email = null;
		int rcNumber = 0;
		if (request.getQueryResult().getOutputContexts().get(0).getParameters().getReservationEnquiry()
				.getEmail() != null) {
			email = request.getQueryResult().getOutputContexts().get(0).getParameters().getReservationEnquiry()
					.getEmail();
		} else {
			rcNumber = request.getQueryResult().getOutputContexts().get(0).getParameters().getReservationEnquiry()
					.getRcNumber().getNumber();
		}
		if (email != null) {
			if (email.equalsIgnoreCase("praveen@gmail.com")) {
				response.setFulfillmentText(
						"Hi Praveen your reservation with IHG Holiday Inn Bangalore is confirmed for 2 days starting 25th June, 2020 and your reservation Id is: RSV1872");
			} else if (email.equalsIgnoreCase("bharath@gmail.com")) {
				response.setFulfillmentText(
						"Hi Bharath your reservation with IHG Crown Plaza Atlanta, GA is confirmed for 2 days starting 15th August, 2020 and your reservation Id is: RSV34251");
			} else
				response.setFulfillmentText("Reservation with email id " + email
						+ " is confirmed for Crown Plaza, NY for 4 days starting 15th July, 2020 and your reservation Id is: RSV78388");
		} else {

			response.setFulfillmentText("Reservation with RC number " + rcNumber
					+ " is confirmed for 5 days starting 5th July, 2020 and your reservation Id is: RSV11388");

		}
		return response;
	}

}
