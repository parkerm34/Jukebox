package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SongList implements TableModel {

	private ArrayList<Song> songs = new ArrayList<Song>();
	
	public SongList()
	{
		setUpDefaultList();
	}
	
	private void setUpDefaultList()
	{
		songs.add(new Song("Blue Ridge Mountain Mist", "Ralph Schuckett", 38));
		songs.add(new Song("Determined Tumbao", "FreePlay Music", 20));
		songs.add(new Song("Flute", "Sun Microsystems", 5));
		songs.add(new Song("Space Music", "Unknown", 6));
		songs.add(new Song("Swing Cheese", "FreePlay Music", 15));
		songs.add(new Song("Tada", "Microsoft", 2));
		songs.add(new Song("Untamable Fire", "Pierre Langer", 282));
	}

	public ArrayList<Song> getList()
	{
		return songs;
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		if(columnIndex == 0 || columnIndex == 1)
			return String.class;
		else
			return Integer.class;
	}

	@Override
	public int getColumnCount()
	{
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex)
	{
		
		if(columnIndex == 0)
			return "Song Name";
		else if(columnIndex == 1)
			return "Artist";
		else
			return "Time";
	}

	@Override
	public int getRowCount() {
		return songs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Song temp = songs.get(rowIndex);
		if(columnIndex == 0)
			return temp.getSongName();
		else if(columnIndex == 1)
			return temp.getArtistName();
		else
			return temp.getSongTime();
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
}
