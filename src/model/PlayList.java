package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PlayList
{
	public LimitedQueue<Song> songsQueued = new LimitedQueue<Song>(5);
	private SongList fullList = new SongList();
	private ArrayList<Song> help = new ArrayList<Song>();
	
	public Song find(String songName)
	{
		help = fullList.getList();
		for(int x = 0; x < help.size(); x++)
			if(help.get(x).getSongName() == songName)
				return help.get(x);
		
		return null;
				
	}
}
