package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class PlayList
{
	public static LimitedQueue<Song> songsQueued = new LimitedQueue<Song>(5);
	private SongList fullList = new SongList();
	private ArrayList<Song> help = new ArrayList<Song>();
	private static ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();

	public Song find(String songName)
	{
		help = fullList.getList();
		for(int x = 0; x < help.size(); x++)
			if(help.get(x).getSongName() == songName)
				return help.get(x);
		
		return null;	
	}
	
	public static void playSong()
	{
		if(songsQueued.size() != 0)
		{
			SongPlayer.playFile(waiter, baseDir + songsQueued.peek().getSongExt());
		}
	}
	
	private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent)
		{
			songsQueued.pop();
			System.out.print("Finished " + eosEvent.fileName());
			GregorianCalendar finishedAt = eosEvent.finishedTime();
			System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
					+ finishedAt.get(Calendar.MINUTE) + ":"
					+ finishedAt.get(Calendar.SECOND));
			playSong();
		}
	}

	/**
	 * baseDir will be the fully qualified path to the directory in which this
	 * program is running on any machine. System.getProperty("file.separator")
	 * returns "\" when running on Unix or "/" when running on windows.
	 */
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");
}
