package com.example.demo.ridePlanning;

import com.example.demo.admin.Discount;

import java.util.ArrayList;
import java.util.Calendar;

public class Ride 
{
	  public ArrayList<Event>events = new ArrayList<>();
	  public Area source;
	  public Area dest;
	  public Client client;
	  public Driver driver;
	  public int numberOfPassengers;
	  public float price;
	  public Discount dicount;
	  public Ride(Area source, Area dest, Client client,int numberOfPassengers)
	 {
		    this.source=source;
		    this.dest=dest;
		    this.client=client;
		    this.numberOfPassengers=numberOfPassengers;
	 }
	  public Ride(Area source, Area dest, Client client,Driver driver,int numberOfPassengers)
	  {
		  this.client=client;
		  this.dest=dest;
		  this.driver=driver;
		  this.source=source;
		  this.numberOfPassengers=numberOfPassengers;
	  }
	  public Ride(Area source, Area dest, String clientUserName,String driverUserName)
	 {
		    this.source=source;
		    this.dest=dest;
		    this.client=client;
		    this.driver=driver;
	 }
	public Ride() {
		// TODO Auto-generated constructor stub
	}
	public void checkDiscount() {
		Discount discount=new Discount();
		if(discount.birthdayCheck(this))
		{
			this.dicount.price= (float) (this.price*0.90);
		}
		if(discount.numberOfTripsCheck(this))
		{
			this.dicount.price= (float) (this.price*0.90);
		}
		if(discount.areaDiscount(this))
		{
			this.dicount.price= (float) (this.price*0.90);
		}
		if(discount.numberOfPassengersCheck(this))
		{
			this.dicount.price= (float) (this.price*0.95);
		}
		if(discount.publicHolidayCheck(this))
		{
			this.dicount.price= (float) (this.price*0.95);
		}
	}
}