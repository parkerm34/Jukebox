package model;

public class Song {

	private String songName;
	private String artistName;
	private int songTime;
	private int dayCount;
	private int fullCount;
	
	public boolean allowedToPlay()
	{
		if(dayCount < 5)
			return true;
		return false;
	}
	
	/*******************************************************************************************/
	/* SETTERS AND GETTERS FOR CLASS SONG													   */
	/*******************************************************************************************/
	
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
		this.dayCount = count;
	}
	
	public void setFullCount(int count)
	{
		this.fullCount = count;
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
	
	public int getFullCount()
	{
		return this.fullCount;
	}
}
