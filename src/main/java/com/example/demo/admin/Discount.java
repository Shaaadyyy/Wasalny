package com.example.demo.admin;//package com.example.demo;

import com.example.demo.ridePlanning.Ride;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
public class Discount{
	public float price;
	public boolean  birthdayCheck(Ride ride)
	{
		Date today = new Date();
		today = Calendar.getInstance().getTime();
		if(ride.client.Birthdate==today)
		{
			return true;
		}
		return false;
	}
	public boolean numberOfTripsCheck(Ride ride)
	{
		if(ride.client.numberOfTrips==1)
		{
			return true;
		}
		return false;
	}
	public boolean areaDiscount(Ride ride)
	{
		if(ride.dest.discount)
		{
			return true;
		}
		return false;
	}
	public boolean numberOfPassengersCheck(Ride ride)
	{
		if(ride.numberOfPassengers==2)
		{
			return true;
		}
		return false;
	}
	public boolean publicHolidayCheck(Ride ride)
	{
		Calendar myDate = Calendar.getInstance(); // set this up however you need it.
		int dow = myDate.get (Calendar.DAY_OF_WEEK);
		boolean isWeekday = ((dow == Calendar.FRIDAY) || (dow == Calendar.SATURDAY));
		return isWeekday;
		
	}
}
