package com.example.demo.database;
import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Request;
import com.example.demo.ridePlanning.Driver;
import com.example.demo.ridePlanning.Ride;

import java.io.*;
//import java.sql.Driver;
import java.util.ArrayList;
public class FileBase implements Storage {
	private static FileBase single_instance = null;
	public ArrayList<Client> clientsAccounts = new ArrayList<> (); 
	public ArrayList<Driver> DriversAccounts = new ArrayList<> ();
    public ArrayList <Driver> SuspendedDriver = new ArrayList <> ();
	public ArrayList <Client> SuspendedClient = new ArrayList <> ();
	public ArrayList<Driver>DriverPendingRegistrations=new ArrayList<>();
    public ArrayList<Ride>rides=new ArrayList<>();
    public ArrayList<Request>requests=new ArrayList<>();
	private FileBase()
	{
		
	}
	public static FileBase getInstance()
    {
        if (single_instance == null)
            single_instance = new FileBase();
        return single_instance;
    }
	public void addClient(Client client) throws IOException
	{
        BufferedWriter out = new BufferedWriter(
                new FileWriter("clientAccounts.txt", true));
		out.write(client.userName+" "+client.email+" "+client.mobileNumber+" "+client.birthday);
        out.write("\n");
        out.close();
        clientsAccounts.add(client);
        /*PrintWriter pw = new PrintWriter(new FileOutputStream("clientsAccounts.txt"));
        pw.println(client.userName+" "+client.email+" "+client.password+" "+client.mobileNumber);
        pw.close();*/
	}
	public void addDriver(Driver driver) throws IOException
	{
		DriversAccounts.add(driver);
        BufferedWriter out = new BufferedWriter(
                new FileWriter("driversAccounts.txt", true));
        out.write(driver.userName+" "+driver.email+" "+driver.mobileNumber+" ");
        out.write("\n");
        out.close();
        /////////////////////////////////////////////////
        /*PrintWriter pw = new PrintWriter(new FileOutputStream("driversAccounts.txt"));
        pw.println(driver.userName+" "+driver.email+" "+driver.password+driver.NationalID+" "+driver.mobileNumber+" "+driver.DrivingLicense);
        pw.close();*/
        /////////////////////////////////////////////////
	}
    public void addSuspendedDriver(Driver driver) throws IOException
	{
		SuspendedDriver.add(driver);
        BufferedWriter out = new BufferedWriter(
                new FileWriter("suspendedDrivers.txt", true));
        out.write(driver.userName+" "+driver.email+" "+driver.mobileNumber+" ");
        out.write("\n");
        out.close();
	}
    public void addSuspendedClient(Client suspendedClient) throws FileNotFoundException
	{
		SuspendedClient.add(suspendedClient);
        ///////////////////////////////////////
        PrintWriter pw = new PrintWriter(new FileOutputStream("SuspendedClient.txt"));
        pw.println(suspendedClient.userName+" "+suspendedClient.email+" "+suspendedClient.password+" "+suspendedClient.mobileNumber);
        pw.close();
        ////////////////////////////////////////
	}
    public void addDriverPendingRegistrations(Driver driver) throws IOException
	{
		DriverPendingRegistrations.add(driver);
        BufferedWriter out = new BufferedWriter(
                new FileWriter("pendingRegistartions.txt", true));
        out.write(driver.userName+" "+driver.email+" "+driver.mobileNumber+" ");
        out.write("\n");
        out.close();
        ///////////////////////////////////////////////////////////
	}
    public void addRide(Ride ride) throws FileNotFoundException
	{
		 rides.add(ride);
         PrintWriter pw = new PrintWriter(new FileOutputStream("Rides.txt"));
         pw.println(ride.client.userName+" "+ ride.driver.userName+" "+ride.source);
         pw.close();
	}           
}