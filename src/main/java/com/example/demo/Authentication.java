package com.example.demo;

import com.example.demo.database.FileBase;
import com.example.demo.ridePlanning.Client;
import com.example.demo.ridePlanning.Driver;

public class Authentication
{
    private Driver driver;
    private Client client;
    public Driver driverAuthentication(String username, String passwoword)
    {
        FileBase fb=FileBase.getInstance();
        for(int i=0;i<fb.DriversAccounts.size();i++)
        {
            if(username.equals(fb.DriversAccounts.get(i).userName)&&passwoword.equals(fb.DriversAccounts.get(i).password))
            {
                return fb.DriversAccounts.get(i);
            }
        }
        return null;
    }
    public Client clientAuthentication(String username, String passwoword)
    {
        FileBase fb=FileBase.getInstance();
        for(int i=0;i<fb.clientsAccounts.size();i++)
        {
            if(username.equals(fb.clientsAccounts.get(i).userName)&&passwoword.equals(fb.clientsAccounts.get(i).password))
            {
                return fb.clientsAccounts.get(i);
            }
        }
        return null;
    }
}
