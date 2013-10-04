package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;
import view.JukeboxGUI;

public class PlayList implements Serializable
{
	public static LimitedQueue<Song> songsQueued = new LimitedQueue<Song>(5);
	private SongList fullList = new SongList();
	private ArrayList<Song> help = new ArrayList<Song>();
	private StudentList students = new StudentList();
	private static ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();

	/*
	 * This find method makes it a very simple task to find a song from the full
	 * list of songs stored in the arraylist. This is done by getting the name from
	 * the table on the GUI and comparing it to the songs stored. The Song object
	 * will be returned to the caller.
	 * 
	 * INPUT: String songName
	 * 			This is from the table on the GUI
	 * 
	 * OUTPUT: Song object
	 */
	public int find(String songName)
	{
		help = fullList.getList();
		for(int x = 0; x < help.size(); x++)
			if(help.get(x).getSongName() == songName)
				return x;
		
		return -1;	
	}
	
	public int findStudent(String studentName, StudentList list)
	{
		StudentList rawr = new StudentList();
		students.getStudents();
		for(int x = 0; x < rawr.getStudents().size(); x++)
		{
			if(rawr.getStudents().get(x).getId().compareTo(studentName) == 0)
			{
				return x;
			}
		}
		return -1;
	}
	
	/*
	 * This method calls for the SongPlayer class to play each song.
	 * This method also makes sure that there is something in the list
	 * before it tries to play the song
	 */
	public static void playSong()
	{
		if(songsQueued.size() != 0)
			SongPlayer.playFile(waiter, baseDir + songsQueued.peek().getSongExt());
	}
	
	/*
	 * This method is a Listener to listen for the end of each song. This method
	 * also implements the popping the song off the queue and calls the method
	 * to play each song if the queue is backed up.
	 */
	private static class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent)
		{
			// Song popped from queue when done playing
			songsQueued.pop();
			
			// Liberated from the demoSongPlayer that we received
			System.out.print("Finished " + eosEvent.fileName());
			GregorianCalendar finishedAt = eosEvent.finishedTime();
			System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
					+ finishedAt.get(Calendar.MINUTE) + ":"
					+ finishedAt.get(Calendar.SECOND));
			
			// plays songs when the queue has more than 1 item
			JukeboxGUI.updateDisplayQueue();
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
