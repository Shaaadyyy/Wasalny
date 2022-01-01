package com.example.demo.admin;//package com.example.demo;

import com.example.demo.database.FileBase;
import com.example.demo.ridePlanning.Ride;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
public class Discount{
	public float price;
	public Discount()
	{
		this.price=0;
	}
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
		FileBase fb=FileBase.getInstance();
		int cnt=0;
		for(int i=0;i<fb.rides.size();i++)
		{
			if(ride.client.userName.equals(fb.rides.get(i).client.userName))
				cnt++;
		}
		if(cnt==1)
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
		boolean flag=false;
		LocalDate date = LocalDate.now();
		DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		switch (day) {
			case SATURDAY:
				flag=true;
				System.out.println("got here");
			case SUNDAY:
				flag=true;
		}
		return flag;
	}

	public boolean areaDiscount(Ride ride) {
		Admin admin=Admin.getInstance();
		for(int i=0;i<admin.discountAreas.size();i++)
		{
			System.out.println(ride.dest);
			System.out.println(admin.discountAreas.get(i));
			if(ride.dest.equals(admin.discountAreas.get(i)))
				return  true;
		}
		return false;
	}
}
