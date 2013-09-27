package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

		JButton songSelect = new JButton("Queue Song");
		JPanel panel = new JPanel();
		panel.add(songSelect);
		add(panel, BorderLayout.WEST);
		songSelect.addActionListener(new ButtonListener());
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
				
				queued.songsQueued.add(queued.find(songTableModel.getValueAt(modelRow, 0).toString()));
				System.out.println(queued.songsQueued.pop().getSongName());
			}
		}
	}
}
