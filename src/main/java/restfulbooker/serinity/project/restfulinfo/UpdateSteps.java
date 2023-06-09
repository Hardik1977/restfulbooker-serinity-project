package restfulbooker.serinity.project.restfulinfo;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import restfulbooker.serinity.project.constants.EndPoints;
import restfulbooker.serinity.project.model.UpdateBookingPojo;

public class UpdateSteps {

    @Step("Update Booking using token {0}, bookingid {1}, firstname {2}, lastname {3}, totalprice {4}, depositpaid {5}, checkin date {6}, checkout date {7}, checkout {8}, additionalneeds {9}")
    public ValidatableResponse updateBooking(String token, int bookingid, String firstname, String lastname, int totalprice, Boolean depositpaid, String checkin, String checkout, String additionalneeds) {

        UpdateBookingPojo.BookingDates dates = new UpdateBookingPojo.BookingDates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);
        UpdateBookingPojo updateBookingPojo = new UpdateBookingPojo();
        updateBookingPojo.setFirstname(firstname);
        updateBookingPojo.setLastname(lastname);
        updateBookingPojo.setTotalprice(totalprice);
        updateBookingPojo.setDepositpaid(depositpaid);
        updateBookingPojo.setBookingdates(dates);
        updateBookingPojo.setAdditionalneeds(additionalneeds);
        updateBookingPojo.setBookingid(bookingid);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingid)
                .body(updateBookingPojo)
                .when()
                .put(EndPoints.UPDATE_BY_ID)
                .then().log().all();

    }

    @Step("Getting student information with bookingId: {0}")
    public ValidatableResponse getUserbyId(String token, int bookingid) {
        return SerenityRest.given().log().all()
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingid)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }


}
