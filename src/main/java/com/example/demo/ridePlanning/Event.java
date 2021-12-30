package com.example.demo.ridePlanning;

import com.example.demo.database.FileBase;

import java.time.LocalDateTime;
public class Event 
{
	public String name;
	public LocalDateTime time;
	public Client client;
	public Driver driver;
	public void suggestPrice(Driver driver,Ride ride,Offer offer)
	{
		this.name="Price Suggestion";
		this.time=LocalDateTime.now();
		this.driver=ride.driver;
		this.client=ride.client;
		FileBase db=FileBase.getInstance();
		for(int i=0;i<db.clientsAccounts.size();i++)
		 {
			if(ride.client==db.clientsAccounts.get(i))
			{
				ride.client.offers.add(offer);
				System.out.println("event prinnt");
			}
	}
		ride.events.add(this);
	}
	public void acceptOffer(Ride ride,Offer offer)
	{
		
		this.name="accept offer";
		ride.driver.busy=true;
		this.client=ride.client;
		this.driver=ride.driver;
		this.time=LocalDateTime.now();
		FileBase fb=FileBase.getInstance();
		ride.events.add(this);
	}
	public void arrivedToUserLocation(Ride ride)
	{	
		this.name="arrived to user location";
		this.client=ride.client;
		this.driver=ride.driver;
		this.time=LocalDateTime.now();
		ride.events.add(this);
	}
	public void arrivedToUserDest(Ride ride)
	{
		this.name="arrived to user dest";
		this.client=ride.client;
		this.driver=ride.driver;
		this.time=LocalDateTime.now();
		ride.events.add(this);
		ride.driver.busy=false;

	}
}
