package VerCitas;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import InformeFinal.InformeFinal;
import services.Conexion;

public class VerCitas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String[][] list = new String[99][99];

	public VerCitas() throws IOException, SQLException, ClassNotFoundException {
		setTitle("Ver Citas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 20, 0, 0, 20, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 20, 0, 15, 0, 20, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 3;
		gbc_separator_4.gridy = 1;
		contentPane.add(separator_4, gbc_separator_4);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 2;
		contentPane.add(separator_2, gbc_separator_2);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		Connection conexion = Conexion.obtener();

		String sql = "SELECT * FROM cita ORDER BY fecha ASC";

		Statement statement;
		statement = conexion.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int i = 0;
		int q = 0;

		while (result.next()) {
			list[i][0] = result.getString(1);
			list[i][1] = result.getString(2);
			list[i][2] = result.getString(3);
			list[i][3] = result.getString(4);
			list[i][4] = result.getString(5);
			i++;
			if (result.getBoolean(6) == false) {
				list[q][5] = "No";
			}else {
				list[q][5] = "Si";
			}
			q++;
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(list,
				new String[] { "Cod_Cita", "Cod_Usuario", "Cod_Médico", "Resumen", "Fecha", "Realizada" }));
		scrollPane.setViewportView(table);

		// conexion.close();

		// Leer leer = new Leer();
		/*table = new JTable();
		table.setModel(new DefaultTableModel(list,
				new String[] { "Cod_Cita", "Cod_Usuario", "Cod_Médico", "Resumen", "Fecha", "Realizada" }));
		scrollPane.setViewportView(table);*/

		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 4;
		gbc_separator_3.gridy = 2;
		contentPane.add(separator_3, gbc_separator_3);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 3;
		gbc_separator.gridy = 3;
		contentPane.add(separator, gbc_separator);

		JButton btnNewButton = new JButton("Informe Final");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							InformeFinal frame = new InformeFinal();
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
							setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});

		JComboBox<String> comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 4;

		String sql2 = "SELECT nom FROM med";
		
		Statement statement2;
		statement2 = conexion.createStatement();
		ResultSet result2 = statement2.executeQuery(sql2);

		comboBox.addItem("Todos");
		
		while (result2.next()) {
			comboBox.addItem(result2.getString(1));
		}
		
		contentPane.add(comboBox, gbc_comboBox);
		
		comboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
		
				int cod = comboBox.getSelectedIndex();
				String sql3 = "SELECT * FROM cita WHERE cod_med="+cod+" ORDER BY fecha ASC";
				
				Statement statement3 = null;
				try {
					statement3 = conexion.createStatement();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ResultSet result3 = null;
				try {
					result3 = statement3.executeQuery(sql3);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String [][] list2=new String[99][99];
				String [][] list3=new String [99][99];
				
				if (comboBox.getSelectedIndex()==0) {
					list2=list;
				}
				
				for (int j = 1; j < 6; j++) {
					if (comboBox.getSelectedIndex()==j) {
						try {
							while (result3.next()) {
								list3[0][0]=result3.getString(1);
								list3[0][1]=result3.getString(2);
								list3[0][2]=result3.getString(3);
								list3[0][3]=result3.getString(4);
								list3[0][4]=result3.getString(5);
								if (result3.getBoolean(6) == false) {
									list3[0][5] = "No";
								}else {
									list3[0][5] = "Si";
								}
							}
							list2=list3;
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
				table = new JTable();
				table.setModel(new DefaultTableModel(list2,
						new String[] { "Cod_Cita", "Cod_Usuario", "Cod_Médico", "Resumen", "Fecha", "Realizada" }));
				scrollPane.setViewportView(table);
			}
		});

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 5;
		contentPane.add(separator_1, gbc_separator_1);
	}

}
