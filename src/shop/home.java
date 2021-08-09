package shop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class home extends JFrame {													//////////////////////Inheritance//////////////////

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_address;
	private JTextField txt_mob;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public home() {
		initialize();
		
	}
	
	String num;
	
	private JTable table;
	
	public home(String s)
	{
		
		this.num = s;
		initialize();
		
	}
	
	Connection con;
	PreparedStatement insert;
	public void initialize()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 723, 491);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 255));
		panel_1.setBounds(0, 0, 271, 491);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(85, 172, 162, 20);
		panel_1.add(txt_name);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(85, 220, 162, 20);
		panel_1.add(txt_address);
		
		txt_mob = new JTextField();
		txt_mob.setColumns(10);
		txt_mob.setBounds(85, 267, 162, 20);
		panel_1.add(txt_mob);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(29, 175, 46, 14);
		panel_1.add(lblName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Address");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(29, 223, 57, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mobile No");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(29, 270, 57, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		String value = null;
        Statement stmt = null;
		try 
        {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Name FROM `persons` WHERE Mobile_no = '"+ num +"'");

            while (rs.next())
                value = rs.getString("Name");
        } 

        catch (SQLException e ) 
        {
            e.printStackTrace();
        } 
		lblNewLabel.setText("Hi, "+value);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel.setBounds(63, 56, 184, 67);
		panel_1.add(lblNewLabel);
		
		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txt_name.getText();
				
				String mob = txt_mob.getText();
				
				String ad = txt_address.getText();
				
				
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
					insert = con.prepareStatement("insert into info(User_mobile_no,Contact_mobile_no,Contact_name,Contact_address)values(?,?,?,?)");
					
					insert.setString(1, num);
					insert.setString(2, mob);
					insert.setString(3, name);
					insert.setString(4, ad);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Added");
					
					txt_name.setText("");
					
					txt_mob.setText("");
					
					txt_address.setText("");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				table_update();
				
			}
		});
		btn_add.setBounds(8, 322, 78, 23);
		panel_1.add(btn_add);
		
		JButton btn_edit = new JButton("EDIT");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				
				
				
				try {
					
					
					String name = txt_name.getText();
					
					String mob = txt_mob.getText();
					
					String ad = txt_address.getText();
					
					
					
					Class.forName("com.mysql.jdbc.Driver");
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
					insert = con.prepareStatement("update info set  Contact_mobile_no='"+ mob +"',Contact_name='"+ name +"',Contact_address='"+ ad +"' where User_mobile_no = '"+ num +"'AND Contact_mobile_no='"+ mob+"'");
					
					
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Updated");
					
					txt_name.setText("");
					
					txt_mob.setText("");
					
					txt_address.setText("");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				table_update();
				
			}
				
				
				
				
				
			
		});
		btn_edit.setBounds(95, 322, 82, 23);
		panel_1.add(btn_edit);
		
		JButton btn_delete = new JButton("DELETE");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?","Warning",JOptionPane.YES_NO_OPTION);
				
				if(dialogResult==JOptionPane.YES_OPTION) {
					
					try {
						
						
						String name = txt_name.getText();
						
						String mob = txt_mob.getText();
						
						String ad = txt_address.getText();
						
						
						
						Class.forName("com.mysql.jdbc.Driver");
						
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
						insert = con.prepareStatement("delete from info  where User_mobile_no = '"+num+"'AND Contact_mobile_no='"+ mob+"'");
						
						
						insert.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Record Deleted");
						
						txt_name.setText("");
						
						txt_mob.setText("");
						
						txt_address.setText("");
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				
				table_update();
				
			}
				
			
		});
		btn_delete.setBounds(187, 322, 82, 23);
		panel_1.add(btn_delete);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				main_menu m = new main_menu();
				m.frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 57, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(270, 0, 453, 491);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				
				
				
			}
		});
		scrollPane.setBounds(10, 11, 433, 469);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override															////////////////////// polymorphism, method override  //////////////////
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				
				txt_name.setText(Df.getValueAt(selectedIndex, 0).toString());
				txt_mob.setText(Df.getValueAt(selectedIndex, 1).toString());
				txt_address.setText(Df.getValueAt(selectedIndex, 2).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					 "Name","Mobile", "Address"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_update(); 
	}
	public void table_update() {
		int c;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
			insert = con.prepareStatement("Select * from info where User_mobile_no = '"+num+"'");
			
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss = rs.getMetaData();
			c = Rss.getColumnCount();
			
			DefaultTableModel Df = (DefaultTableModel)table.getModel();
			
			Df.setRowCount(0);
			while(rs.next())
			{
				Vector v2 = new Vector();
				
				for(int a=1;a<=c;a++) {
					
					v2.add(rs.getString("Contact_name"));
					v2.add(rs.getString("Contact_mobile_no"));
					v2.add(rs.getString("Contact_address"));
				}
				Df.addRow(v2);
			}
			
			
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
