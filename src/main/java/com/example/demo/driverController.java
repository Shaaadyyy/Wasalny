package com.example.demo;
import com.example.demo.database.FileBase;
import com.example.demo.ridePlanning.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
@RestController
public class driverController {
    Driver driver;
    @PostMapping("driver/register")
    public Driver driverRegister(@RequestBody Driver driver) throws IOException
    {
        return driver.register();
    }
    FileBase fb=FileBase.getInstance();
    @GetMapping("driver/login/{username}/{password}")
    public String driverLogin(@PathVariable String username,@PathVariable String password)
    {
        Authentication auth = new Authentication();
        driver = auth.driverAuthentication(username, password);
        if (driver != null) {
            driver.authenticated = true;
            return "login successfull";
        } else {
            //.authenticated=false;
            return " driver not authenticated";
        }
    }
    @PostMapping("driver/addFavoriteArea/{area}")
    public String addFavoriteArea(@PathVariable String area)
    {
        if(!driver.suspended&&driver.authenticated)
        {
            return driver.AddFavoriteArea(area);
        }
        return " driver not authenticated";
    }
    @GetMapping("driver/listRatings")
    public ArrayList<Integer> listRating()
    {
        if(driver.authenticated&&!driver.suspended) {
            return (driver.ListRatings());
        }
        return null;
    }
    @PostMapping("driver/offerPrice/{price}")
    public String suggestPrice(@RequestBody Client client, @PathVariable float price)
    {
        if(driver.authenticated&&!driver.suspended) {
            Offer offer = new Offer(price);
            return (driver.offer(client, offer));
        }
        return "driver not authenticated";
    }
    @PostMapping("driver/arriverToUserLocation")
    public String arriverToUserLocation()
    {
        if(driver.authenticated&&!driver.suspended) {
            return (driver.arrivedToUserLocation());
        }
        return "driver not authenticated";
    }
    @PostMapping("driver/arriverToUserDest")
    public String arriverToUserDest()
    {
        if(driver.authenticated&&!driver.suspended) {
            return (driver.arrivedToUserDest());
        }
        return "driver not authenticated";
    }
}
