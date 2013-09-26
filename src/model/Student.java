package model;

public class Student
{

	private String name = "";
	private int timeLeft;
	private int dayCount;
	private long ID;
	private boolean activeStudent;
	
	public Boolean allowedToPlay()
	{
		//TODO IMPLEMENT METHOD	
		
		return true;
	}
	
	public Boolean activeStudent(){
		//TODO implement method 
		return true;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getTimeLeft()
	{
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft)
	{
		this.timeLeft = timeLeft;
	}

	public int getDayCount()
	{
		return dayCount;
	}

	public void setDayCount(int dayCount)
	{
		this.dayCount = dayCount;
	}

	public long getID()
	{
		return ID;
	}

	public void setID(long iD)
	{
		ID = iD;
	}

	public boolean isActiveStudent()
	{
		return activeStudent;
	}

	public void setActiveStudent(boolean activeStudent)
	{
		this.activeStudent = activeStudent;
	}

}
