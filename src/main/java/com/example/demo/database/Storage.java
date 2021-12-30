package com.example.demo.database;

import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Driver;
import com.example.demo.ridePlanning.Ride;

import java.io.FileNotFoundException;
import java.io.IOException;
public interface Storage {
	public void addClient(Client client) throws IOException;
	public void addDriver(Driver driver) throws IOException;
	public void addSuspendedDriver(Driver suspendedDriver) throws IOException;
	public void addSuspendedClient(Client suspendedClient) throws FileNotFoundException;
	public void addDriverPendingRegistrations(Driver driverPendingRegistrations) throws IOException;
	public void addRide(Ride ride) throws FileNotFoundException;
}
