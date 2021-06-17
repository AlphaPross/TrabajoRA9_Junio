package GestiónCitas;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GestiónMédicos.GestionMedicos;
import services.Conexion;

public class GestionCitas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String[][] list=new String[99][99];

	public GestionCitas(String nom) throws IOException, ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/hospital.png"));
		setTitle("Gesti\u00F3n Citas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 20, 0, 0, 0, 20, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 20, 0, 15, 0, 20, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 3;
		gbc_separator_4.gridy = 1;
		contentPane.add(separator_4, gbc_separator_4);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		contentPane.add(separator, gbc_separator);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		String sql2 = "SELECT nomUser, clave FROM user";

		Connection conexion2 = Conexion.obtener();
		Statement statement2;
		statement2 = conexion2.createStatement();
		ResultSet result2 = statement2.executeQuery(sql2);
		
		String [] list2 = new String [99];
		int l=0;
		
		while (result2.next()) {
			list2[l] = result2.getString(1);
			l++;
			list2[l] = result2.getString(2);
			l++;
		}
		int cod_user = 1;
		
		for (int j = 3; j < list2.length; j++) {
			if (list2[j]==nom) {
				cod_user=j;
			}
		}
		
		Connection conexion = Conexion.obtener();

		String sql = "SELECT cod_cita, cod_user, cod_med, resumen, fecha, realizada FROM cita WHERE cod_user = "+cod_user+" ORDER BY fecha ASC";

		Statement statement;
		statement = conexion.createStatement();
		ResultSet result = statement.executeQuery(sql);

		int i = 0;

		while (result.next()) {
			list[i][0] = result.getString(1);
			list[i][1] = result.getString(2);
			list[i][2] = result.getString(3);
			list[i][3] = result.getString(4);
			list[i][4] = result.getString(5);

			if (result.getBoolean(6) == false) {
				list[i][5] = "No";
			} else {
				list[i][5] = "Si";
			}
			i++;
		}

		// conexion.close();

		// Leer leer = new Leer();
		table = new JTable();
		table.setModel(new DefaultTableModel(list,
				new String[] { "cod_cita", "cod_user", "cod_médico", "Resumen", "Fecha", "Realizado" })
		{
			boolean[] columnEditables = new boolean[] {
					false, false, false, true, true, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		scrollPane.setViewportView(table);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 5;
		gbc_separator_1.gridy = 2;
		contentPane.add(separator_1, gbc_separator_1);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 3;
		gbc_separator_2.gridy = 3;
		contentPane.add(separator_2, gbc_separator_2);

		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						if (table.getValueAt(table.getSelectedRow(),0)!=null) {
							String dato=String.valueOf(table.getValueAt(table.getSelectedRow(),0));
							if (dato!=null) {
								try {	
									
									Connection conexion = Conexion.obtener();
									String query = "INSERT INTO med (cod_med, nom, foto, dirección) VALUES (null, null, null, '"
											+table.getValueAt(table.getSelectedRow(),3)+"', '"
											+table.getValueAt(table.getSelectedRow(),4)+"', null)";
									Statement stmt = conexion.createStatement();
									stmt.executeUpdate(query);
									
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
						
						
						
						try {
							GestionCitas frame = new GestionCitas(nom);
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
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("Anular");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dato=String.valueOf(table.getValueAt(table.getSelectedRow(),0));
				
				try {	
					
					Connection conexion = Conexion.obtener();
					String query = "DELETE FROM cita WHERE cod_cita="+dato;
					Statement stmt = conexion.createStatement();
					stmt.executeUpdate(query);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							GestionCitas frame = new GestionCitas(nom);
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
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 4;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 3;
		gbc_separator_3.gridy = 5;
		contentPane.add(separator_3, gbc_separator_3);
	}

}
