package model;

public class Student
{

	private String id = "";
	private String password = "";

	private int timeLeft;
	private int dayCount;

	private boolean activeStudent;

	public Student(String id, String password)
	{
		this.id = id;
		this.password = password;
	}

	public Boolean allowedToPlay()
	{
		// TODO IMPLEMENT METHOD

		return true;
	}

	public Boolean activeStudent()
	{
		// TODO implement method
		return true;
	}

	/*******************************************************************************************/
	/* SETTERS AND GETTERS FOR CLASS Student												   */
	/*******************************************************************************************/
	
	public String getName()
	{
		return id;
	}

	public void setName(String name)
	{
		this.id = name;
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

	public boolean isActiveStudent()
	{
		return activeStudent;
	}

	public void setActiveStudent(boolean activeStudent)
	{
		this.activeStudent = activeStudent;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
