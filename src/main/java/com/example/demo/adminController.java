package com.example.demo;
import com.example.demo.admin.Admin;
import com.example.demo.ridePlanning.Area;
import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Driver;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
@RestController
public class adminController {
    @PostMapping("driver/verify")
    public String verifyDeriver(@RequestBody Driver driver) throws IOException {
        Admin admin=Admin.getInstance();
        return admin.verifyDriver(driver);
    }
    @PostMapping("driver/suspend")
    public String suspendDriver(@RequestBody Driver driver)
    {
        Admin admin=Admin.getInstance();
        return  admin.suspendDriver(driver);
    }
    @PostMapping("client/suspend")
    public String suspendClient(@RequestBody Client client)
    {
        Admin admin=Admin.getInstance();
        return  admin.suspendClient(client);
    }
    @PostMapping("driver/release")
    public String releaseDriverAccount(@RequestBody Driver driver) throws IOException {
        Admin admin=Admin.getInstance();
        return admin.releaseDriverAccount(driver);
    }
    @PutMapping("area/addDiscount/{area}")
    public String addDiscount(String area)
    {
        Admin admin=Admin.getInstance();
        Area a=new Area(area);
        admin.addDiscountToArea(a);
        return "discount added to area "+a.area;
    }
    @PostMapping("client/release")
    public String releaseClientAccount(@RequestBody Client client)
    {
        Admin admin=Admin.getInstance();
        return admin.releaseClientAccount(client);
    }
    @GetMapping("admin/listPendingRegistrations")
    public ArrayList<Driver> listPendingRegistrations()
    {
        Admin admin=Admin.getInstance();
        return admin.ListDriverPendingAccount();
    }
}
