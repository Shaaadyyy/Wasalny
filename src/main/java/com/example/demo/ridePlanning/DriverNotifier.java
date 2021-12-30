package com.example.demo.ridePlanning;
import com.example.demo.database.FileBase;
import java.io.FileNotFoundException;
public class DriverNotifier {
public DriverNotifier(Request request)
{
	FileBase fb=FileBase.getInstance();
	Ride ride=null;
	for(int i=0;i<fb.DriversAccounts.size();i++)
	{
		for(int j=0;j<fb.DriversAccounts.get(i).FavoriteArea.size();j++) {
			if(fb.DriversAccounts.get(i).FavoriteArea.get(j).area==request.source.area &&fb.DriversAccounts.get(i).busy==false)
			{
				ride=new Ride(request.source,request.dest,request.client,fb.DriversAccounts.get(i),request.numberOfPassenngers);
				fb.DriversAccounts.get(i).rides.add(ride);
				try {
					fb.addRide(ride);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
}
