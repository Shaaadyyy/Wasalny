package com.example.demo.ridePlanning;
import com.example.demo.database.FileBase;
public class Request {
	private String source;
	private String dest;
	private Client client;
	private int numberOfPassenngers;
	Request(String source,String dest,Client client,int numberOfPassenngers)
	{
		this.source=source;
		this.dest=dest;
		this.client=client;
		this.numberOfPassenngers=numberOfPassenngers;
		FileBase fb=FileBase.getInstance();
		fb.requests.add(this);
		DriverNotifier notifier=new DriverNotifier(this);
	}
	public Client getCLient()
	{
		return client;
	}
	public int getNumberOfPassenngers()
	{
		return numberOfPassenngers;
	}
	public String getSourceArea()
	{
		return source;
	}
	public String getDestArea()
	{
		return dest;
	}
}
