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

import model.SongList;

public class JukeboxGUI extends JFrame{


	public static void main(String[] args)
	{
		String username = JOptionPane.showInputDialog("Please enter username");
		
		String password = JOptionPane.showInputDialog("Please enter password for user " + username);
		
		if(true)
			new JukeboxGUI().setVisible(true);
	}
	
	private TableModel modelOfSongs;
	private JTable tableOfSongs;
	
	public JukeboxGUI()
	{
			layoutGUI();
	}
	
	private void layoutGUI()
	{
		setTitle("University of Arizona Student Jukebox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		setBackground(Color.BLUE);

		this.setSize(700, 500);
		this.setLocation(200, 200);
		
		modelOfSongs = new SongList();
		tableOfSongs = new JTable(modelOfSongs);
		tableOfSongs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(tableOfSongs);
		
		
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelOfSongs);
		
		tableOfSongs.setRowSorter(sorter);
		
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
			int viewRow = tableOfSongs.getSelectedRow();
			
			if (viewRow < 0)
				System.out.println("index " + viewRow
						+ " means no row is selected");
			else
			{
				int modelRow = tableOfSongs.convertRowIndexToModel(viewRow);
				System.out.println("index " + viewRow + " has the name '"
						+ modelOfSongs.getValueAt(modelRow, 0) + "'");
			}
		}
	}
}
