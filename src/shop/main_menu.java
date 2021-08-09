package shop;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class main_menu{   

	JFrame frame;
	private JTextField textField_mobile_login;
	private JTextField textField_pass_login;
	private JTextField txt_reg_name;
	private JTextField txt_reg_pass;
	private JTextField txt_reg_mobile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_menu window = new main_menu();								////////////////////// Object //////////////////
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main_menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection con;
	PreparedStatement insert;
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 723, 491);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 271, 491);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField_mobile_login = new JTextField();
		textField_mobile_login.setBounds(89, 202, 162, 20);
		panel_1.add(textField_mobile_login);
		textField_mobile_login.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mobile No");
		lblNewLabel.setBounds(22, 208, 66, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(22, 256, 66, 14);
		panel_1.add(lblNewLabel_1);
		
		textField_pass_login = new JTextField();
		textField_pass_login.setColumns(10);
		textField_pass_login.setBounds(89, 250, 162, 20);
		panel_1.add(textField_pass_login);
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");                                                            //////////////////////Database connectivity//////////////////
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
					Statement stmt = con.createStatement();
					String sql = "Select * from persons where Mobile_no = '"+ textField_mobile_login.getText()  +"' and Password = '"+textField_pass_login.getText() +"' ";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next())
					{
						frame.setVisible(false);
						home x = new home(textField_mobile_login.getText());
						x.setVisible(true);
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Username/Password Error","Error",JOptionPane.ERROR_MESSAGE);
						textField_mobile_login.setText(null);
						textField_pass_login.setText(null);
						
					}
				}
				catch(Exception e1){																					////////////////////// Exception handling //////////////////
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btn_login.setBounds(110, 305, 89, 23);
		panel_1.add(btn_login);
		
		JLabel lblNewLabel_2 = new JLabel("Contacts");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(78, 87, 126, 51);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBackground(new Color(0, 153, 255));
		panel_2.setBounds(269, 0, 454, 491);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		txt_reg_name = new JTextField();
		txt_reg_name.setColumns(10);
		txt_reg_name.setBounds(170, 132, 162, 20);
		panel_2.add(txt_reg_name);
		
		txt_reg_pass = new JTextField();
		txt_reg_pass.setColumns(10);
		txt_reg_pass.setBounds(170, 180, 162, 20);
		panel_2.add(txt_reg_pass);
		
		txt_reg_mobile = new JTextField();
		txt_reg_mobile.setColumns(10);
		txt_reg_mobile.setBounds(170, 227, 162, 20);
		panel_2.add(txt_reg_mobile);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(98, 132, 46, 14);
		panel_2.add(lblName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(98, 180, 62, 14);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mobile No");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setBounds(98, 227, 62, 14);
		panel_2.add(lblNewLabel_1_1_1);
		
		JButton btn_reg = new JButton("Register");
		btn_reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txt_reg_name.getText();
				
				String pass = txt_reg_pass.getText();
				
				String mob = txt_reg_mobile.getText();
				
				
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/contacts","root","");
					insert = con.prepareStatement("insert into persons(Mobile_no,Name,Password)values(?,?,?)");
					
					insert.setString(1, mob);
					insert.setString(2, name);
					insert.setString(3, pass);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Registration Complete");
					
					txt_reg_name.setText("");
					
					txt_reg_pass.setText("");
					
					txt_reg_mobile.setText("");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_reg.setBounds(192, 291, 89, 23);
		panel_2.add(btn_reg);
	}
	
}
