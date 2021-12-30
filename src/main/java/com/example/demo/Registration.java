package com.example.demo;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Driver;
import com.example.demo.admin.Admin;
import com.example.demo.database.FileBase;
//import org.springframework.web.bind.annotation.PostMapping;
// org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Registration {
	Admin admin= Admin.getInstance();
	FileBase fb= FileBase.getInstance();
	//PostMapping("client/add")
	public boolean clientRegiter(Client client) throws IOException//(@RequestBody Client client) throws IOException {
	{
		fb.addClient(client);
		return true;
	}
	public void driverRegister(Driver driver) throws IOException {
		driver.authenticated=false;
		admin.addPendingDriver(driver);
	}
}