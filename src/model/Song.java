package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Song implements Serializable
{

	private String songExt;
	private String songName;
	private String artistName;
	private int songTime;
	private int dayCount;
	private int lastPlayed;
	
	
	
	public Song(String songExt, String songName, String artistName, int songTime)
	{
		this.songExt = songExt;
		this.songName = songName;
		this.artistName = artistName;
		this.songTime = songTime;
		this.dayCount = 0;
		
		//TODO set up daycount and full count
	}

	public boolean allowedToPlay()
	{
		if(this.lastPlayed < (new GregorianCalendar().get(Calendar.DAY_OF_YEAR)))
			this.dayCount = 0;
		if(dayCount < 5)
			return true;
		return false;
	}
	
	/*******************************************************************************************/
	/* SETTERS AND GETTERS FOR CLASS SONG													   */
	/*******************************************************************************************/
	
	public void setLastPlayed()
	{
		this.lastPlayed = new GregorianCalendar().get(Calendar.DAY_OF_YEAR);
	}
	
	public int getLastPlayed()
	{
		return this.lastPlayed;
	}
	
	public void setSongExt(String songExt)
	{
		this.songExt = songExt;
	}
	
	public void setSongName(String name)
	{
		this.songName = name;
	}
	
	public void setArtistName(String name)
	{
		this.artistName = name;
	}
	
	public void setSongTime(int time)
	{
		this.songTime = time;
	}
	
	public void setDayCount(int count)
	{
		this.lastPlayed = new GregorianCalendar().get(Calendar.DAY_OF_YEAR);
		this.dayCount = count;
	}
	
	public String getSongExt()
	{
		return this.songExt;
	}
	
	public String getSongName()
	{
		return this.songName;
	}
	
	public String getArtistName()
	{
		return this.artistName;
	}
	
	public int getSongTime()
	{
		return this.songTime;
	}
	
	public int getDayCount()
	{
		return this.dayCount;
	}
}
