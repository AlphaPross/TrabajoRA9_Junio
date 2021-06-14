package InformeFinal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import services.Conexion;

public class InformeFinal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private String[][] list = new String[99][99];

	public InformeFinal() throws IOException, SQLException, ClassNotFoundException {
		setTitle("Informe Final");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 20, 0, 20, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 20, 0, 20, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 2;
		gbc_separator_3.gridy = 1;
		contentPane.add(separator_3, gbc_separator_3);

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

		Connection conexion = Conexion.obtener();

		String sql = "SELECT m.nom, fecha FROM med m, cita c WHERE m.cod_med=c.cod_med ORDER BY fecha ASC";

		Statement statement;
		statement = conexion.createStatement();
		ResultSet result = statement.executeQuery(sql);

		String[][] list1 = new String[99][99];
		int i = 0;
		
		ArrayList<String> list2 = new ArrayList<>();

		while (result.next()) {
			list1[i][0] = result.getString(1);
			list1[i][1] = result.getString(2);
			list2.add(result.getString(2));
			i++;
		}
		
		int [] list3 = new int[list2.size()+1];
		
		Statement statement2;
		statement2 = conexion.createStatement();
		ResultSet result2 = statement2.executeQuery(sql);
		
		int j=0;
		while (result2.next()) {
			list3[j]=Collections.frequency(list2, result2.getString(2));
			j++;
		}
		
		String[] strArray = new String[list3.length];
		 
        for (int h = 0; h < list3.length; h++) {
            strArray[h] = String.valueOf(list3[h]);
        }
		
		for (int j2 = 0; j2 < strArray.length-1; j2++) {
			if (strArray[j2]==strArray[j2+1]) {
				
			}else {
				list1[j2][1]=strArray[j2];
			}
			
		}
		
		// Leer leer = new Leer();
		table = new JTable();
		table.setModel(new DefaultTableModel(list1, new String[] { "Nombre Médico", "Media citas/dia" }));
		scrollPane.setViewportView(table);

		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 3;
		gbc_separator_2.gridy = 2;
		contentPane.add(separator_2, gbc_separator_2);

		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 2;
		gbc_separator_1.gridy = 3;
		contentPane.add(separator_1, gbc_separator_1);
	}

}
