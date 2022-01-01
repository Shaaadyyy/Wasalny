package com.example.demo;
import com.example.demo.database.FileBase;
import com.example.demo.ridePlanning.*;
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
    @DeleteMapping("client/logout")
    public String logout()
    {
        client=null;
        return "logged out";
    }
    @GetMapping("client/login/{username}/{password}")
    public String cleintLogin(@PathVariable String username,@PathVariable String password) {
        Authentication auth = new Authentication();
        client = auth.clientAuthentication(username, password);
        if (client!=null) {
            client.authenticated = true;
            return "login successfull";
        }
        //client=null;
        return " client not authenticated";
    }
    @PostMapping("driver/rate/{driverUserName}/{rate}")
    public int rate(@PathVariable String driverUserName,@PathVariable int rate)
    {
        if(client.authenticated&&!client.suspended) {
            Driver driver = null;
            FileBase fb = FileBase.getInstance();
            for (int i = 0; i < fb.DriversAccounts.size(); i++) {
                if (driverUserName.equals(fb.DriversAccounts.get(i).userName)) {
                    driver = fb.DriversAccounts.get(i);
                    return client.rate(driver, rate);
                }
            }
        }
        return 0;
    }
    @GetMapping("driver/checkAvgRate/{driverUserName}")
    public float checkAvgRating(@PathVariable String driverUserName)
    {
        if(client.authenticated&&!client.suspended) {
            Driver driver = null;
            FileBase fb = FileBase.getInstance();
            for (int i = 0; i < fb.DriversAccounts.size(); i++) {
                if (driverUserName.equals(fb.DriversAccounts.get(i).userName)) {
                    driver = fb.DriversAccounts.get(i);
                    break;
                }
            }
            return client.checkAvgRating(driver);
        }
        return 0;
    }
    @PostMapping("client/requestRide/{source}/{dest}/{numberOfPassengers}")
    public Request requestRide(@PathVariable String source,@PathVariable String dest,@PathVariable String numberOfPassengers)
    {
        if(client.authenticated&&!client.suspended) {
            int num = Integer.parseInt(numberOfPassengers);
            return (client.requestRide(source, dest, num));
        }
        return null;
    }
    @PostMapping("client/acceptOffer/{price}")
    public String acceptOffer(@PathVariable float price)
    {
        if(client.authenticated&&!client.suspended) {
            Offer offer = new Offer(price);
            return (client.accOff(offer));
        }
        return "client not authenticated";
    }
}
