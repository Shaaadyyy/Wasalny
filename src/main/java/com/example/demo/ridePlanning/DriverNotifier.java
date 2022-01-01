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
			System.out.println("arrived here");
			if(fb.DriversAccounts.get(i).FavoriteArea.get(j).equals(request.getSourceArea() )&&fb.DriversAccounts.get(i).busy==false)
			{
				ride=new Ride(request.getSourceArea(),request.getDestArea(),request.getCLient(),fb.DriversAccounts.get(i),request.getNumberOfPassenngers());
				//fb.DriversAccounts.get(i).rides.add(ride);
				fb.DriversAccounts.get(i).currentRide=ride;
				try {
					fb.addRide(ride);
					//System.out.println("--------"+ride.dest.discount);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
}
