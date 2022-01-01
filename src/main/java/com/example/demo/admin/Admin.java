package com.example.demo.admin;//package com.example.demo;
import com.example.demo.Account;
import com.example.demo.database.FileBase;
//import com.example.demo.ridePlanning.Area;
import com.example.demo.ridePlanning.Event;
import com.example.demo.ridePlanning.Ride;
import com.example.demo.ridePlanning.Client;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.ridePlanning.Driver;
import java.io.IOException;
import java.util.ArrayList;
@RestController
public class Admin extends Account {
	public ArrayList <Driver> SuspendedDriver = new ArrayList <> () ;
	public ArrayList <Client> SuspendedClient = new ArrayList <> () ;
	public ArrayList<Driver>DriverPendingRegistrations=new ArrayList<>();
    public ArrayList<Ride>rides=new ArrayList<>();
	private static Admin single_instance = null;
	public ArrayList<String>discountAreas=new ArrayList<>();
	private Admin()
	{
		userName = "admin";
		password = "admin";
	}
	public void addPendingDriver(Driver driver) throws IOException {
		FileBase fb=FileBase.getInstance();
		fb.addDriverPendingRegistrations(driver);
		DriverPendingRegistrations.add(driver);
	}
	public static Admin getInstance()
    {
        if (single_instance == null)
            single_instance = new Admin();
        return single_instance;
    }
	public ArrayList<Event> showEvents(Ride ride)
	{
		return ride.events;
	}
	public String verifyDriver(Driver driver) throws IOException {
		boolean flag = false;
		FileBase db=FileBase.getInstance();
		for(int i = 0; i < DriverPendingRegistrations.size(); i++)
		{
			if(driver.userName.equals(DriverPendingRegistrations.get(i).userName))
			{
				Driver d1 = DriverPendingRegistrations.get(i);
				db.addDriver(d1);
				db.DriverPendingRegistrations.remove(DriverPendingRegistrations.get(i));
				DriverPendingRegistrations.remove(DriverPendingRegistrations.get(i));
				flag = true;
				driver.suspended=false;
				//DriverPendingRegistrations.get(i).suspended=false;
				return "driver verified";
			}
		}
		if(flag == false)
		{
			return "driver doesn't exist";
		}
		return null;
	}
	public ArrayList<Driver> ListDriverPendingAccount()
	{
        FileBase db=FileBase.getInstance();
		return DriverPendingRegistrations;
	}
	public void ListDriverAccounts()
	{
		FileBase db=FileBase.getInstance();
		for(int i = 0; i < db.DriversAccounts.size(); i++)
		{
			System.out.println("Client account no. " + (i+1));
			System.out.println("Username: " + db.DriversAccounts.get(i).userName);
			System.out.println("Mobile Number: " + db.DriversAccounts.get(i).mobileNumber);
			System.out.println("Email: " + db.DriversAccounts.get(i).email);
			System.out.println("Password: " + db.DriversAccounts.get(i).password);
			System.out.println("Driving License: " + db.DriversAccounts.get(i).getDrivingLicense());
			System.out.println("National ID: " + db.DriversAccounts.get(i).getNationalID());
			System.out.println("-----------------------------------------");
		}
		
	}
	public String suspendDriver (Driver driver)
	{
		FileBase db=FileBase.getInstance();
		boolean flag = false;
		for(int i = 0; i < db.DriversAccounts.size(); i++)
		{
			if(driver.userName.equals(db.DriversAccounts.get(i).userName))
			{
				Driver d=db.DriversAccounts.get(i);
				SuspendedDriver.add(d);
				db.SuspendedDriver.add(d);
				db.DriversAccounts.remove(d);
				//db.DriversAccounts.get(i).suspended=true;
				driver.suspended=true;
				flag = true;
				return "driver suspended";
			}
		}
		if(flag == false)
			System.out.println("Username is not found!");
		return null;
	}
	public String releaseDriverAccount(Driver driver) throws IOException
	{
		FileBase db=FileBase.getInstance();
		boolean flag = false;
		for(int i = 0; i < SuspendedDriver.size(); i++)
		{
			if(driver.userName.equals(SuspendedDriver.get(i).userName))
			{
				Driver d=SuspendedDriver.get(i);
				db.addDriver(d);
				SuspendedDriver.remove(d);
				db.SuspendedDriver.remove(d);
				//SuspendedDriver.get(i).suspended=false;
				driver.suspended=false;
				flag = true;
				return "Driver released";
			}
		}
		if(flag == false)
			return "driver not found";
		return null;
	}
	public String suspendClient(Client client)
	{
		FileBase db=FileBase.getInstance();
		boolean flag = false;
		//ListClientAccounts();
		for(int i = 0; i < db.clientsAccounts.size(); i++)
		{
			if(client.userName.equals(db.clientsAccounts.get(i).userName)){
				Client c=db.clientsAccounts.get(i);
				SuspendedClient.add(c);
				//db.clientsAccounts.get(i).suspended=true;
				client.suspended=true;
				db.SuspendedClient.add(c);
				db.clientsAccounts.remove(db.clientsAccounts.get(i));
				flag=true;
				return "Client suspended";
			}
		}
		if(flag == false)
			return "client not found";
		return null;
	}
	
	public String releaseClientAccount( Client client)
	{
		FileBase db=FileBase.getInstance();
		boolean flag = false;
		System.out.println(SuspendedClient.size());
		for(int i = 0; i < SuspendedClient.size(); i++)
		{
			if(client.userName.equals(SuspendedClient.get(i).userName)){
				Client c=SuspendedClient.get(i);
				SuspendedClient.remove(c);
				db.SuspendedClient.remove(c);
				client.suspended=false;
				//SuspendedClient.get(i).suspended=false;
				db.clientsAccounts.add(c);
				flag=true;
				return "client released";
			}
		}
		if(flag == false)
			return "client not found";
		return null;
	}
	public void addDiscountToArea(String area)
	{
		discountAreas.add(area);
	}
}