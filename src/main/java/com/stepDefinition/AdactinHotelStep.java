package com.stepDefinition;

import java.util.concurrent.TimeUnit;

import com.objectRepository.BookHotelPageLocator;
import com.objectRepository.ConfirmPageLocator;
import com.objectRepository.LoginPageLocator;
import com.objectRepository.SearchHotelPageLocator;
import com.resourses.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class AdactinHotelStep extends BaseClass {
	@Given("^User verify the url and title$")
	public void user_verify_the_url_and_title() {
		if (getURL().contains("adactin")) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		if (getTitle().contains("Hotel")) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

	@Given("^User login the adactin page \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_login_the_adactin_page_and(String user, String pass) {
		LoginPageLocator l = new LoginPageLocator();
		type(l.getTxtUserName(), user);
		type(l.getTxtPassWord(), pass);
		click(l.getBtnLogin());

	}

	@Given("^User enter the details in search hotel page \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_the_details_in_search_hotel_page_and(String location, String hotel, String roomtype,
			String numrooms, String checkin, String checkout, String adtcount, String chdcount)
			throws InterruptedException {
		SearchHotelPageLocator s = new SearchHotelPageLocator();
		selectByText(s.getTxtLocation(), location);
		selectByText(s.getTxtHotels(), hotel);
		selectByText(s.getTxtRoomType(), roomtype);
		selectByText(s.getTxtRoomNo(), numrooms);
		type(s.getTxtDateIn(), checkin);
		type(s.getTxtDateOut(), checkout);
		selectByText(s.getTxtAdtRoom(), adtcount);
		selectByText(s.getTxtChdRoom(), chdcount);
		click(s.getBtnSubmit());
		implicitWait();
		click(s.getBtnHotelSel());
		click(s.getBtnContinue());
	}

	@When("^User enter the details in booking hotel page \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enter_the_details_in_booking_hotel_page_and(String firstname, String lastname, String address,
			String cardno, String cardtype, String expmonth, String expyear, String ccv) throws InterruptedException {
		BookHotelPageLocator b = new BookHotelPageLocator();
		type(b.getTxtFirstName(), firstname);
		type(b.getTxtLastName(), lastname);
		type(b.getTxtAddress(), address);
		type(b.getTxtCreditCard(), cardno);
		selectByText(b.getTxtCardType(), cardtype);
		selectByText(b.getTxtCardExpMonth(), expmonth);
		selectByText(b.getTxtCardExpYear(), expyear);
		type(b.getTxtCreditCCV(), ccv);
		click(b.getBtnBookNow());
		implicitWait();
		ConfirmPageLocator c = new ConfirmPageLocator();
		String orderid = c.getTxtOrderNo().getAttribute("value");
		System.out.println("Order Id: " + orderid);
		click(c.getBtnMyIter());
		implicitWait();
		click(c.getBtnCheckAll());
		click(c.getBtnCancel());
		alert("Confirm", "Yes");
		click(c.getBtnLogout());
	}
}
