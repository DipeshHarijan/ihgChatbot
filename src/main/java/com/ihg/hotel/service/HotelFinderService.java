package com.ihg.hotel.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ihg.hotel.model.WebhookRequest;
import com.ihg.hotel.model.WebhookResponse;

@Service
public class HotelFinderService {

	public WebhookResponse getHotelReservation(WebhookRequest request) {

		WebhookResponse response = new WebhookResponse();

		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
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
					+ children + " child(ren) from " + formatter.format(checkIn) + " to " + formatter.format(checkOut)
					+ " in " + city + " with rate preference " + ratePreference + " has been confirmed.");
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
						+ "2. Ambassador\r\n" + "3. Business Rewards Club\r\n" + "4. Karma/Kimpton Inner Circle\r\n");
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

	public WebhookResponse getLoyaltyProgram(WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();
		String membership = request.getQueryResult().getParameters().getLoyaltyProgram();
		if (membership.equalsIgnoreCase("Reward Club")) {
			response.setFulfillmentText("IHG Rewards Club\r\n" + "\r\n"
					+ "There is no membershp fee for IHG Reward Club. To maintain the Points in your membership account, you must have at least one “earn” or “redeem” transaction posted to your account every twelve (12) months\r\n"
					+ "\r\n" + "The membership tiers for IHG Rewards club are:\r\n" + "\r\n"
					+ "Club (basic level) — 0 nights / 0 stays per year.\r\n" + "\r\n"
					+ "Gold — 10 nights / 10 000 Elite qualifying points /\r\n" + "\r\n"
					+ "Platinum — 40 nights / 40 000 Elite qualifying points. ...\r\n" + "\r\n"
					+ "Spire — 75 nights / 75 000 Elite qualifying points.");
		} else if (membership.equalsIgnoreCase("Ambassador")) {
			response.setFulfillmentText("In addition to being an IHG Rewwards Club member,\r\n" + "\r\n"
					+ "There is a $5,000 spend requirement between the eligible dates at InterContinental, Kimpton, and Regent-brands to qualify for Royal Ambassador successfully.");
		} else if (membership.equalsIgnoreCase("Business Rewards")) {
			response.setFulfillmentText(
					"Membership in IHG Business Rewards is available to individuals who are either corporate bookers, business travel/meetings agents or individual bookers subject to the following conditions:\r\n"
							+ "\r\n"
							+ "1. A corporate booker is an individual who books Qualifying Spend at Participating Hotels for and on behalf of his or her employer for business purposes\r\n"
							+ "\r\n"
							+ "2. Members will earn three (3) IHG Rewards Club Points for every 1 U.S. dollar or local currency equivalent paid towards Qualifying Spend at Participating Hotels");
		} else if (membership.equalsIgnoreCase("Kimpton Inner Circle")) {
			response.setFulfillmentText(
					"Kimpton Inner Circle is an invitation only extension of IHG® Rewards Club Spire Elite providing Kimpton on-property benefits including the Inner Circle amenity, Chef’s Taste, and Guaranteed One Category Room Upgrade.\r\n"
							+ "\r\n"
							+ "Awarded to those most loyal to Kimpton, IHG® Rewards Club Members will receive an invitation when they’ve reached Spire Elite and meet the Inner Circle criteria (unpublished) that is based on loyalty to the brand over the course of the year");
		} else {
			response.setFulfillmentText("Could not find your membership. Please try again.");
		}
		return response;
	}

}
