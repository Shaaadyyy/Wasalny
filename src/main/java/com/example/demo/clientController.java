package com.example.demo;
import com.example.demo.database.FileBase;
import com.example.demo.ridePlanning.Area;
import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Driver;
import com.example.demo.ridePlanning.Request;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.io.IOException;
@RestController
public class clientController {
    Client client;
    @PostMapping("client/register")
    public boolean regiter(@RequestBody Client client) throws FileNotFoundException {
        try {
            return client.register();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    @GetMapping("client/login/{username}/{password}")
    public String cleintLogin(@PathVariable String username,@PathVariable String password)
    {
        Authentication auth=new Authentication();
        client=auth.clientAuthentication(username,password);
        if(client!=null)
        {
           // client.authenticated=true;
            return "login successfull";
        }
        else
        {
            return " client not authenticated";
        }
    }
    @PostMapping("client/requestRide")
    public Request requestRide(@RequestBody  Area source, Area dest, int numberOfPassengers) {
        return client.requestRide(source, dest, numberOfPassengers);
    }
    @PostMapping("driver/rate/{driverUserName}/{rate}")
    public int rate(@PathVariable String driverUserName,@PathVariable int rate)
    {
        Driver driver = null;
        FileBase fb=FileBase.getInstance();
        for (int i = 0; i < fb.DriversAccounts.size(); i++) {
            if (driverUserName.equals(fb.DriversAccounts.get(i).userName)) {
                driver = fb.DriversAccounts.get(i);
                return client.rate(driver, rate);
            }
        }
        return 0;
    }
    @GetMapping("driver/checkAvgRate/{driverUserName}")
    public float checkAvgRating(@PathVariable String driverUserName)
    {
        Driver driver = null;
        FileBase fb=FileBase.getInstance();
        for(int i=0;i<fb.DriversAccounts.size();i++)
        {
            if(driverUserName.equals(fb.DriversAccounts.get(i).userName))
            {
                driver=fb.DriversAccounts.get(i);
                break;
            }
        }
        return client.checkAvgRating(driver);
    }
    @GetMapping("client/get")
    public Client getClient()
    {
        FileBase fb=FileBase.getInstance();
        return fb.clientsAccounts.get(0);
    }
    @PostMapping("client/requestRide/{source}/{dest}/{numberOfPassengers}")
    public Request requestRide(@PathVariable String source,@PathVariable String dest,@PathVariable String numberOfPassengers)
    {
        int num=Integer.parseInt(numberOfPassengers);
        Area s=new Area(source);
        Area d=new Area(dest);
        return (client.requestRide(s,d,num));
    }
}
