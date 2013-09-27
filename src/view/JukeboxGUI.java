package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.JukeboxModel;
import model.PlayList;
import model.SongList;

public class JukeboxGUI extends JFrame
{

	private SongList songList;
	private TableModel songTableModel;
	private JTable songTable;
	private PlayList queued = new PlayList();
	private static String[] displayQueue = new String[5];
	private static JList halp = new JList(displayQueue);


	public JukeboxGUI()
	{
		layoutGUI();
	}

	private void layoutGUI()
	{
		setTitle("University of Arizona Student Jukebox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setBackground(Color.BLUE);

		this.setSize(700, 500);
		this.setLocation(200, 200);

		songList = new SongList();
		songTableModel = songList;
		songTable = new JTable(songTableModel);
		songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(songTable);

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(songTableModel);

		songTable.setRowSorter(sorter);

		add(scrollPane, BorderLayout.CENTER);

		JPanel panel2 = new JPanel();
		JButton songSelect = new JButton("Queue Song");
		panel2.add(songSelect);
		add(panel2, BorderLayout.NORTH);
		songSelect.addActionListener(new ButtonListener());		

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 5, 5));
		

		
		JLabel queueLabel = new JLabel("  Up next...  ", JLabel.CENTER);
		panel.add(queueLabel);
		queueLabel.setVerticalAlignment(JLabel.BOTTOM);
		queueLabel.setVerticalTextPosition(JLabel.BOTTOM);
		
		
		panel.add(halp);
		panel.setSize(new Dimension(100, 500));
		halp.setBackground(Color.lightGray);
		add(panel, BorderLayout.WEST);

	}

	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			int viewRow = songTable.getSelectedRow();
			
			if (viewRow < 0)
				System.out.println("index " + viewRow + " means no row is selected");
			else
			{
				int modelRow = songTable.convertRowIndexToModel(viewRow);
				System.out.println("index " + viewRow + " has the name '"
						+ songTableModel.getValueAt(modelRow, 0) + "'");
//				 + songTable.getValueAt(songTable.getSelectedRow(),songTable.convertColumnIndexToModel(0))+"'");
//				this is another way to so it
				
				// Adds song to queue
				PlayList.songsQueued.add(queued.find(songTableModel.getValueAt(modelRow, 0).toString()));
				
				displayQueue[PlayList.songsQueued.size()-1] = songTableModel.getValueAt(modelRow, 0).toString();
				System.out.println(displayQueue[PlayList.songsQueued.size()-1]);
				halp.setListData(displayQueue);
				
				//testing for printing the song name
				System.out.println(PlayList.songsQueued.peek().getSongName());
				
				// plays song if this item is the only thing in the list
				if(PlayList.songsQueued.size() == 1)
				{
					displayQueue[0] = "";
					PlayList.playSong();
				}
			}
		}
	}
	
	public static void updateDisplayQueue()
	{
		int size = PlayList.songsQueued.size();
		for(int x = 0; x < size; x ++)
			displayQueue[x] = displayQueue[x+1];
		
		if(size != 0)
			for(int x = size-1; x < 5; x++)
				displayQueue[x] = "";
		
		halp.setListData(displayQueue);
	}
}
