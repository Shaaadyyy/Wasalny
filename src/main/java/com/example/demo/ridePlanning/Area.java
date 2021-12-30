package com.example.demo.ridePlanning;

public class Area {
	public String area;
	public boolean discount=false;
	public Area(String area)
	{
		this.area=area;
	}
	public void addDiscount(boolean discount)
	{
		this.discount=true;
	}
    public boolean checkDiscount()
	{
    	if(discount==true)
		{
			return true;
		}
		return false;
	 }
}
