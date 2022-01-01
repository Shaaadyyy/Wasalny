package com.example.demo.ridePlanning;
import com.example.demo.Account;
import com.example.demo.Registration;
import com.example.demo.database.FileBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
public class Client extends Account {
	public ArrayList<Offer>offers=new ArrayList<Offer>();
	public String birthday;
	public Date Birthdate;
	//public int numberOfTrips;
	public boolean authenticated;
	public boolean suspended;
	public Client(String userName, String mobileNumber, String email, String password)//,String birthday) throws ParseException
	{
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.birthday=birthday;
		this.authenticated=false;
		this.suspended=false;
	}
	public boolean isAuthenticated() {
		return authenticated;
	}
	public int rate(Driver driver,int rate){
		driver.Ratings.add(rate);
		return rate;
	}
	public float checkAvgRating(Driver driver){
	    float sum=0;
		for(int i=0;i<driver.Ratings.size();i++)
	    {
	    	sum+=driver.Ratings.get(i);
	    }
		float avg=sum/(driver.Ratings.size());
		return avg;
	}
	public Request requestRide(String source,String destination,int numberOfPassengers)
	{	
		Request request=new Request(source, destination,this, numberOfPassengers);
		return request;
	}
	public String accOff(Offer offer)
	{
		//Ride ride=new Ride();
		FileBase fb= FileBase.getInstance();
		Ride ride = null;
		Driver driver = null  ;
		System.out.println(offer.price);
		for(int i=0;i<fb.DriversAccounts.size();i++)
		{
			System.out.println(offer.price+" "+fb.DriversAccounts.get(i).offer.price);
			if(offer.price==fb.DriversAccounts.get(i).offer.price)
			{
				driver= (Driver) fb.DriversAccounts.get(i);
				break;
			}
		}
		for(int i=0;i<fb.requests.size();i++)
		{
			if(this.userName.equals(fb.requests.get(i).getCLient().userName))
			{
				String source=fb.requests.get(i).getSourceArea();
				String dest =fb.requests.get(i).getDestArea();
				Client client =fb.requests.get(i).getCLient();
				int no=fb.requests.get(i).getNumberOfPassenngers();
				ride=new Ride(source,dest,client,driver,no);
				break;
			}
		}
		for(int i=0;i<fb.rides.size();i++)
		{
			if(ride.client.userName.equals(fb.rides.get(i).client.userName))
			{
				return (acceptOffer(fb.rides.get(i),offer));
			}
		}
		return null;
	}
	public String  acceptOffer(Ride ride,Offer offer)
	{
		Event event=new Event();
		return(event.acceptOffer(ride,offer));
	}
	public boolean register() throws IOException //throws FileNotFoundException
	{
		Registration reg=new Registration();
		reg.clientRegiter(this);
		return true;
	}
}