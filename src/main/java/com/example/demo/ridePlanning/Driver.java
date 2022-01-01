package com.example.demo.ridePlanning;
import com.example.demo.Account;
import com.example.demo.Registration;
import com.example.demo.database.FileBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.IOException;
import java.util.ArrayList;
public class Driver extends Account {
	public Ride currentRide;
	public String DrivingLicense;
	@JsonIgnore
	public String NationalID;
	@JsonIgnore
	public boolean authenticated;
	public ArrayList<String> FavoriteArea = new ArrayList<> ();
	@JsonIgnore
	public ArrayList<Integer> Ratings = new ArrayList<> ();
	//@JsonIgnore
	//public ArrayList<Ride> rides = new ArrayList<> ();
	public boolean isAuthenticated() {
		return authenticated;
	}
	public boolean busy;
	public boolean arrived;
	public boolean suspended;
	//public boolean authenticated;
	@JsonIgnore
	public Offer offer;
	public Driver() 
	{
		busy=false;
	}
	public Driver(String userName, String mobileNumber, String email, String password,String DrivingLicense, String NationalID)
	{
		super(userName, mobileNumber, email,password) ;
		this.DrivingLicense = DrivingLicense;
		this.NationalID = NationalID;
		this.suspended=false;
		this.authenticated=false;
	}
	public void setDrivingLicense(String DrivingLicense)
	{
		this.DrivingLicense = DrivingLicense;
	}
	
	public void setNationalID(String NationalID)
	{
		this.NationalID = NationalID;
	}
	
	public String getDrivingLicense()
	{
		return DrivingLicense;
	}
	public String getNationalID()
	{
		return NationalID;
	}
	public String AddFavoriteArea(String area)
	{
		FavoriteArea.add(area);
		return area;
	}
	public ArrayList<Integer> ListRatings()
	{
		return Ratings;
	}
	public String offer(Client client,Offer offer)
	{
		this.offer=offer;
		Event event=new Event();
		return(event.suggestPrice(this, currentRide, offer));
	}
	public String arrivedToUserLocation()
	{
		Event event=new Event();
		return (event.arrivedToUserLocation(currentRide));
	}
	public String arrivedToUserDest()
	{
		Event event=new Event();
		Ride tempRide=this.currentRide;
		tempRide.checkDiscount();
		currentRide=null;
		return (event.arrivedToUserDest(tempRide)+
				"\n"+" money goes to driver "+tempRide.price+
				"\n"+" amount paid by client "+tempRide.discount.price
				);
	}
	public Driver register() throws IOException
	{
		Registration reg=new Registration();
		reg.driverRegister(this);
		return this;
	}
	public int addRating(int rate)
	{
		Ratings.add(rate);
		return rate;
	}

}