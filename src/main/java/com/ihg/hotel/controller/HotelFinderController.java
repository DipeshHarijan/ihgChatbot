package com.ihg.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihg.hotel.model.WebhookRequest;
import com.ihg.hotel.model.WebhookResponse;
import com.ihg.hotel.service.HotelFinderService;

@RestController
public class HotelFinderController {

	@Autowired
	private HotelFinderService hotelFinderService;

	@PostMapping(path = "/webhook")
	public WebhookResponse webhook(@RequestBody WebhookRequest request) {
		WebhookResponse response = new WebhookResponse();

		try {
			String intent = request.getQueryResult().getIntent().getDisplayName();

			switch (intent) {

			case "find_hotel":
				response = hotelFinderService.getHotelReservation(request);
				break;

			case "loyalty_point_balance_confirmation_fin":
				response = hotelFinderService.getLoyaltyPoints(request);
				break;

			case "loyalty_membership_status_confirmation_fin":
				response = hotelFinderService.getLoyaltyStatus(request);
				break;

			case "loyalty_enquiry":
				response = hotelFinderService.getLoyaltyOptions(request);
				break;

			default:
				response.setFulfillmentText("Did not match the intent");
			}
		} catch (Exception e) {
			response.setFulfillmentText(e.getMessage());
		}

		return response;
	}

}
