package GestiónMédicos;

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

import VerCitas.VerCitas;
import services.Conexion;

public class GestionMedicos extends JFrame{
	
	private JPanel contentPane;
	private JTable table;
	String [][] list = new String [99][99];

	public GestionMedicos(String nom) throws IOException, ClassNotFoundException, SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/hospital.png"));
		setTitle("Gesti\u00F3n M\u00E9dicos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 20, 0, 0, 0, 0, 20, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 20, 0, 0, 15, 0, 20, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnNewButton_2 = new JButton("Actualizar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						
						String dato=String.valueOf(table.getValueAt(table.getSelectedRow(),0));
						
						try {	
							
							Connection conexion = Conexion.obtener();
							String query = "UPDATE med SET nom='"+table.getValueAt(table.getSelectedRow(),1)+"', foto='"+table.getValueAt(table.getSelectedRow(),2)+"', dirección='"+table.getValueAt(table.getSelectedRow(),3)+"' WHERE cod_med="+dato;
							Statement stmt = conexion.createStatement();
							stmt.executeUpdate(query);
							
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							GestionMedicos frame = new GestionMedicos(nom);
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
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String dato=String.valueOf(table.getValueAt(table.getSelectedRow(),0));
				
				try {	
					
					Connection conexion = Conexion.obtener();
					String query = "DELETE FROM med WHERE cod_med="+dato;
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
							GestionMedicos frame = new GestionMedicos(nom);
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
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {	
					
					Connection conexion = Conexion.obtener();
					String query = "INSERT INTO med (cod_med, nom, foto, dirección) VALUES (null, '"
									+table.getValueAt(table.getSelectedRow(),1)+"', '"
									+table.getValueAt(table.getSelectedRow(),2)+"', '"
									+table.getValueAt(table.getSelectedRow(),3)+"')";
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
							GestionMedicos frame = new GestionMedicos(nom);
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
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 2;
		gbc_separator_2.gridy = 1;
		contentPane.add(separator_2, gbc_separator_2);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 1;
		gbc_separator_3.gridy = 2;
		contentPane.add(separator_3, gbc_separator_3);
		
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
		
		String sql = "SELECT * FROM med";
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			int i=0;
			
			while (result.next()) {
				list[i][0]=result.getString(1);
				list[i][1]=result.getString(2);
				list[i][2]=result.getString(3);
				list[i][3]=result.getString(4);
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//conexion.close();
		
		//Leer leer = new Leer();
		table = new JTable();
		table.setModel(new DefaultTableModel(list,
				new String[] { "cod_med", "Nombre Completo", "Foto", "Dirección"})
		{
			boolean[] columnEditables = new boolean[] {
					false, true, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		scrollPane.setViewportView(table);
		
		JSeparator separator_4 = new JSeparator();
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.insets = new Insets(0, 0, 5, 5);
		gbc_separator_4.gridx = 6;
		gbc_separator_4.gridy = 2;
		contentPane.add(separator_4, gbc_separator_4);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 2;
		gbc_separator_1.gridy = 4;
		contentPane.add(separator_1, gbc_separator_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 5;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 5;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ver Citas");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							VerCitas frame = new VerCitas();
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
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 5;
		gbc_btnNewButton_3.gridy = 5;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 6;
		contentPane.add(separator, gbc_separator);
	}
	
}
