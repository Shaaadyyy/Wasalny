package com.example.demo;
import com.example.demo.database.FileBase;
import com.example.demo.ridePlanning.Area;
import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Driver;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
@RestController
public class driverController {
    Driver driver;
    @PostMapping("driver/register")
    public Driver driverRegister(@RequestBody Driver driver) throws IOException {
        return driver.register();
    }
    FileBase fb=FileBase.getInstance();
    @GetMapping("driver/login/{username}/{password}")
    public String driverLogin(@PathVariable String username,@PathVariable String password)
    {
        Authentication auth=new Authentication();
        driver=auth.driverAuthentication(username,password);
        if(driver!=null)
        {
            driver.authenticated=true;
            return "login successfull";
        }
        else
        {
            driver.authenticated=false;
            return " driver not authenticated";
        }
    }
    @PostMapping("driver/addFavoriteArea/{a}")
    public String addFavoriteArea(@PathVariable String  a)
    {
        Area area=new Area(a);
        FileBase fb=FileBase.getInstance();
        if(driver.authenticated)
        {
            return driver.AddFavoriteArea(area).area;
        }
        return "driver not authenticated";
    }
    @GetMapping("driver/listRatings")
    public ArrayList<Integer> listRating()
    {
        if(driver.authenticated) {
            return (driver.ListRatings());
        }
        return null;
    }
    @GetMapping("driver/get")
    public Driver getDriver()
    {
        FileBase fb=FileBase.getInstance();
        return fb.DriversAccounts.get(0);
    }
}
