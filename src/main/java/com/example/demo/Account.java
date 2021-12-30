package com.example.demo;

import com.example.demo.ridePlanning.Driver;

public abstract class Account {
	public String userName ; 
	public String mobileNumber; 
	public String email ; 
	public String password ;

	public Account()
	{

	}
	public Account(String userName,String email,String mobileNumber,String password)
	{
		this.userName = userName ;
		this.email =email ;
		this.mobileNumber =mobileNumber ;
		this.password = password ;
		
	}

}