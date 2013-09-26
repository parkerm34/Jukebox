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

public class JukeboxGUI extends JFrame
{

	public static void main(String[] args)
	{
		String username = JOptionPane.showInputDialog("Please enter username");

		String password = JOptionPane.showInputDialog("Please enter password for user " + username);

		if (true) new JukeboxGUI().setVisible(true);
	}

	private TableModel songTableModel;
	private JTable songTable;

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

		songTableModel = new SongList();
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
			}
		}
	}
}
