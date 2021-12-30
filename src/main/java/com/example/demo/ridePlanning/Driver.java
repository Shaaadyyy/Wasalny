package com.example.demo.ridePlanning;
import com.example.demo.Account;
import com.example.demo.Registration;
import com.example.demo.database.FileBase;
import java.io.IOException;
import java.util.ArrayList;
public class Driver extends Account {
	public String DrivingLicense;
	public String NationalID;
	public boolean authenticated;
	public ArrayList<Area> FavoriteArea = new ArrayList<> ();
	public ArrayList<Integer> Ratings = new ArrayList<> ();
	public ArrayList<Ride> rides = new ArrayList<> ();
	public boolean isAuthenticated() {
		return authenticated;
	}
	public boolean busy;
	public boolean arrived;
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
	public Area AddFavoriteArea(Area area)
	{
		FavoriteArea.add(area);
		return area;
	}
	public ArrayList<Integer> ListRatings()
	{
		return Ratings;
	}
	public void offer(Ride ride,Offer offer)
	{
		this.offer=offer;
		Event event=new Event();
		event.suggestPrice(this, ride, offer);
	}
	public void arrivedToUserLocation(Ride ride)
	{
		Event event=new Event();
		event.arrivedToUserLocation(ride);
	}
	public void arrivedToUserDest(Ride ride)
	{
		Event event=new Event();
		event.arrivedToUserDest(ride);
		ride.checkDiscount();
	}
	public Driver register() throws IOException {
		Registration reg=new Registration();
		reg.driverRegister(this);
		return this;
	}
	public Driver login()
	{
		FileBase f=FileBase.getInstance();
		for(int i=0;i<f.DriversAccounts.size();i++)
		{
			if(this==f.DriversAccounts.get(i))
				return f.DriversAccounts.get(i);
		}
		return null;
	}
	public int addRating(int rate)
	{
		Ratings.add(rate);
		return rate;
	}
	public void ListRides()
	{
		for(int i = 0; i < rides.size(); i++)
		{
			System.out.println((i+1) + ")" + " " + 
		rides.get(i).client.userName+" "
		+rides.get(i).driver.userName+" "
		+rides.get(i).source.area+ " "
		+rides.get(i).dest.area
		);
		}
	}
}