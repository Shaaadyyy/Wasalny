package com.example.demo.ridePlanning;

import com.example.demo.admin.Discount;

import java.util.ArrayList;
import java.util.Calendar;

public class Ride 
{
	  public ArrayList<Event>events = new ArrayList<>();
	  //public ٍString source;
	 public String source;
	 public String dest;
	  public Client client;
	  public Driver driver;
	  public int numberOfPassengers;
	  public float price;
	  public Discount discount;
	  public Ride(String source, String dest, Client client,int numberOfPassengers)
	 {
		    this.source=source;
		    this.dest=dest;
		    this.client=client;
		    this.numberOfPassengers=numberOfPassengers;
	 }
	  public Ride(String source, String dest, Client client,Driver driver,int numberOfPassengers)
	  {
		  this.client=client;
		  this.dest=dest;
		  this.driver=driver;
		  this.source=source;
		  this.discount=new Discount();
		  this.numberOfPassengers=numberOfPassengers;
	  }
	  public Ride(String source, String dest, String clientUserName,String driverUserName)
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
		this.discount.price=this.price;
		float x=0;
		if(discount.birthdayCheck(this))
		{
			x+=10;
			//this.discount.price= (float) (this.discount.price*0.90);
		}
		if(discount.areaDiscount(this))
		{
			x+=10;
			System.out.println("area ");
			//this.discount.price= (float) (this.discount.price*0.90);
		}
		if(discount.numberOfTripsCheck(this))
		{
			x+=10;
			//this.discount.price= (float) (this.discount.price*0.90);
		}
		if(discount.numberOfPassengersCheck(this))
		{
			x+=5;
			System.out.println("number of passengers");
			//this.discount.price= (float) (this.discount.price*0.95);
		}
		if(discount.publicHolidayCheck(this))
		{
			x+=5;
			//this.discount.price= (float) (this.discount.price*0.95);
		}
		float y=(100-x)/100;
		this.discount.price=(float) (this.price*y);
	}
}