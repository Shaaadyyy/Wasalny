package com.example.demo.ridePlanning;

import com.example.demo.database.FileBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Event
{
	public String name;
	//public LocalDateTime time;
	Date date;
	String strDate;
	public Client client;
	public Driver driver;
	public float price;
	public String suggestPrice(Driver driver,Ride ride,Offer offer)
	{
		this.name="Price Suggestion";
		this.date= Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		this.strDate = dateFormat.format(date);
		//this.time=LocalDateTime.now();
		this.driver=ride.driver;
		this.client=null;
		this.price=offer.price;
		FileBase db=FileBase.getInstance();
		for(int i=0;i<db.clientsAccounts.size();i++)
		 {
			if(ride.client==db.clientsAccounts.get(i))
			{
				ride.client.offers.add(offer);
			}
	}
		ride.events.add(this);
		return ("event name: "+this.name+"\n"
				+"current time: "+this.strDate+"\n"
				+"driver user name:  "+ this.driver.userName+"\n"
				+"price: "+offer.price);
	}
	public String acceptOffer(Ride ride,Offer offer)
	{
		this.name="accept offer";
		ride.driver.busy=true;
		this.client=ride.client;
		this.driver=ride.driver;
		this.date= Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		ride.price=offer.price;
		this.strDate = dateFormat.format(date);
		ride.events.add(this);
		return ("event name: "+this.name+"\n"+
				"current time: "+this.strDate+" \n"+
				"client user name: "+this.client.userName);
	}
	public String arrivedToUserLocation(Ride ride)
	{
		this.name="arrived to user location";
		this.client=ride.client;
		this.date= Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		this.strDate = dateFormat.format(date);
		this.driver=ride.driver;
		ride.events.add(this);
		return ("event name: "+this.name+"\n"
				+"current time :"+this.strDate+"\n"
				+"client user name: " +this.client.userName+"\n"+
				"driver user name: "+this.driver.userName);
	}
	public String arrivedToUserDest(Ride ride)
	{
		this.name="arrived to user dest";
		this.client=ride.client;
		this.driver=ride.driver;
		this.date= Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		this.strDate = dateFormat.format(date);
		ride.events.add(this);
		ride.driver.busy=false;
		ride.driver.currentRide=null;
		return ("event name: "+this.name+"\n"
				+"current time: "+this.strDate+"\n"
				+"client user name: " +this.client.userName+"\n"+
				"driver user name: "+this.driver.userName);
	}
}
