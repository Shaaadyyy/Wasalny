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
	public int numberOfTrips;
	public boolean authenticated;

	public Client(String userName, String mobileNumber, String email, String password)//,String birthday) throws ParseException
	{
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.birthday=birthday;
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
	public Request requestRide(Area source,Area destination,int numberOfPassengers)
	{	
		Request request=new Request(source, destination,this, numberOfPassengers);
		return request;
	}
	public void accOff(Offer offer)
	{
		//Ride ride=new Ride();
		FileBase fb= FileBase.getInstance();
		Ride ride = null;
		Driver driver = null  ;
		for(int i=0;i<fb.DriversAccounts.size();i++)
		{
			if(offer.price==fb.DriversAccounts.get(i).offer.price)
			{
				driver= (Driver) fb.DriversAccounts.get(i);
				break;
			}
		}
		for(int i=0;i<fb.requests.size();i++)
		{
			if(this.userName==fb.requests.get(i).client.userName)
			{
				Area source=fb.requests.get(i).source;
				Area dest =fb.requests.get(i).dest;
				Client client =fb.requests.get(i).client;
				int no=fb.requests.get(i).numberOfPassenngers;
				ride=new Ride(source,dest,client,driver,no);
				break;
			}
		}
		for(int i=0;i<fb.rides.size();i++)
		{
			if(ride.client.userName.equals(fb.rides.get(i).client.userName))
			{
				acceptOffer(fb.rides.get(i),offer);
			}
		}
	}
	public void acceptOffer(Ride ride,Offer offer)
	{
		Event event=new Event();
		event.acceptOffer(ride,offer);
	}

	public boolean register() throws IOException //throws FileNotFoundException
	{
		Registration reg=new Registration();
		reg.clientRegiter(this);
		return true;
	}
	public Client login()
	{
		FileBase f=FileBase.getInstance();
		for(int i=0;i<f.clientsAccounts.size();i++)
		{
			if(this==f.clientsAccounts.get(i))
				return this;
		}
		return null;
	}

}