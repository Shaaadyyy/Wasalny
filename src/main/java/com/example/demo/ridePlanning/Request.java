package com.example.demo.ridePlanning;
import com.example.demo.database.FileBase;
public class Request {
	Area source;
	Area dest;
	Client client;
	int numberOfPassenngers;
	Request(Area source,Area dest,Client client,int numberOfPassenngers)
	{
		this.source=source;
		this.dest=dest;
		this.client=client;
		this.numberOfPassenngers=numberOfPassenngers;
		FileBase fb=FileBase.getInstance();
		fb.requests.add(this);
		DriverNotifier notifier=new DriverNotifier(this);

	}
}
