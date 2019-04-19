import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserRegistration extends JFrame implements ActionListener , MouseListener
{
	
	 JLabel registration,name,id, password, address,mobile,email,gender, nationality;
	 JPanel panel;
     JComboBox cgender,city;	
	 JTextField tname,tid,taddress,tmobile,temail, tnationality;
	 JButton back,reg;
	 JPasswordField ppassword;
	 Connection connection=null;
	 String UserId;
	 JLabel limg;
	ImageIcon img;
	
	
	public UserRegistration()
	{
		super("User Registration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,750);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		registration = new JLabel("REGISTRATION");
		registration.setBounds(300,30,500,50);
		registration.setFont(new Font("Consolas",Font.BOLD,50));
	    panel.add(registration);
		
		id = new JLabel("User ID");
		id.setBounds(200,110,100,50);
		id.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(id);
		
		tid = new JTextField();
		tid.setBounds(350,110,350,50);
		tid.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tid);
		
		password = new JLabel("Password");
		password.setBounds(200,170,100,50);
		password.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(password);
		
		ppassword = new JPasswordField();
		ppassword.setBounds(350,170,350,50);
		ppassword.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(ppassword);
		
		name = new JLabel("Full Name");
		name.setBounds(200,230,100,50);
		name.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(name);
		
		tname = new JTextField();
		tname.setBounds(350,230,350,50);
		tname.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tname);
		
		
		
		address = new JLabel("Address");
		address.setBounds(200,290,100,50);
		address.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(address);
		
		taddress = new JTextField();
		taddress.setBounds(350,290,350,50);
		taddress.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(taddress);
		
		String s[] = {"City", "Dhaka", "Chitagong","Khulna","Borishal","Noakhali","Comilla","Rajshahi","Rangpur"};
		city = new JComboBox(s);
		city.setBounds(750,290,200,50);
		panel.add(city);
		
		mobile = new JLabel("Mobile No");
		mobile.setBounds(200,350,100,50);
		mobile.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(mobile);
		
		tmobile = new JTextField();
		tmobile.setBounds(350,350,350,50);
		tmobile.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tmobile);
		
		email = new JLabel("Email");
		email.setBounds(200,410,100,50);
		email.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(email);
		
		temail = new JTextField();
		temail.setBounds(350,410,350,50);
		temail.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(temail);
		
		gender = new JLabel("Gender");
		gender.setBounds(200,470,100,50);
		gender.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(gender);
		
		String s2[] = {"Select One", "Male", "Female","Others"};
		cgender = new JComboBox(s2);
		cgender.setBounds(350,470,350,50);
		panel.add(cgender);
		
		nationality = new JLabel("Nationality");
		nationality.setBounds(200,530,150,50);
		nationality.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(nationality);
		
		tnationality = new JTextField();
		tnationality.setBounds(350,530,350,50);
		tnationality.setFont(new Font("Consolas",Font.BOLD,20));
	    panel.add(tnationality);
		
		reg = new JButton("Registration");
		reg.setBounds(550,620,140,50);
		reg.setFont(new Font("Consolas",Font.BOLD,15));
		reg.setBackground(Color.DARK_GRAY);
		reg.setForeground(Color.white);
		reg.addActionListener(this);
		reg.addMouseListener(this);
	    panel.add(reg);
		
		back = new JButton("Back");
		back.setBounds(370,620,140,50);
		back.setFont(new Font("Consolas",Font.BOLD,15));
		back.setBackground(Color.DARK_GRAY);
		back.setForeground(Color.white);
		back.addActionListener(this);
	    panel.add(back);
		
		img = new ImageIcon(".//Image//back.jpg");
		limg = new JLabel(img);
		limg.setBounds(0,0,1000,700);
		panel.add(limg);
		
		//reg.setEnabled(false);
		
		this.add(panel);
		
	}
	
	
	public void mousePressed(MouseEvent e)
	{
		
	}
	public void mouseReleased(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}
	public void mouseClicked(MouseEvent e)
	{
		
	}
	public void mouseEntered(MouseEvent e)
	{
		System.out.println("Out");
		if(e.getSource()==reg)
		{
			if(tname.getText().equals("") || tid.getText().equals("") || taddress.getText().equals("") || tmobile.getText().equals("") || temail.getText().equals("") || tnationality.getText().equals("") || ppassword.getText().equals("") )
			{
				reg.setEnabled(false);
			}
			else if(city.getSelectedItem().toString().equals("City"))
			{
				reg.setEnabled(false);
			}
			else if(cgender.getSelectedItem().toString().equals("Select One"))
			{
				reg.setEnabled(false);
			}
			else
			{
				reg.setEnabled(true);
			}
			System.out.println("IN");
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String text = event.getActionCommand();
		
		if(text.equals("Registration"))
		{
			try
			{
				
				connection=DatabaseConnection.DbConnector();
				String mysql="SELECT * FROM `user`";
				Statement st = connection.createStatement();
				System.out.println("statement created");
				ResultSet rs = st.executeQuery(mysql);
				System.out.println("results received");
				String userId;
				int x=0;
				while(rs.next())
				{
					userId = rs.getString("UserID");
					if(userId.equals(tid.getText()))
					{
						JOptionPane.showMessageDialog(null,"UserID already exist");
						tid.setText("");
						x=1;
					}
				}
				if(x==0)
				{
					String  Name, Address, City, Email, Mobile, Gender, Nationality,Password;
					UserId=tid.getText();
					Name=tname.getText();
					Address=taddress.getText();
					City=city.getSelectedItem().toString();
					Email=temail.getText();
					Mobile=tmobile.getText();
					Gender=cgender.getSelectedItem().toString();
					Nationality=tnationality.getText();
					Password=ppassword.getText();
					
					System.out.println(UserId+"	"+ Name+"	" +Address+"	"+ City+"	"+Email+"	" +Mobile+"	"+ Gender+"	"+ Nationality+"	"+Password);
					
					String query = "INSERT INTO user (`UserId`, `Name`, `Address`, `City`, `Email`, `Mobile`, `Gender`, `Nationality`) VALUES ('"+UserId+"', '"+Name+"', '"+Address+"', '"+City+"', '"+Email+"', '"+Mobile+"', '"+Gender+"', '"+Nationality+"')";
					st.executeUpdate(query);
					System.out.println("Info Added");
					String query1 = "INSERT INTO `login` (`UserId`, `Password`, `UserType`) VALUES ('"+UserId+"', '"+Password+"', 'user')";
					st.executeUpdate(query1);
					System.out.println("Info Added2");
					this.setVisible(false);
					User u = new User(UserId);
					u.setVisible(true);
					JOptionPane.showMessageDialog(null,"Welcome "+ UserId);
					
					try
					{
						if(rs!=null)
							rs.close();

						if(st!=null)
							st.close();

						if(connection!=null)
							connection.close();
					}
					catch(Exception ex)
					{
						System.out.println("Exception : " +ex.getMessage());
					}
				}
				
				
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Please Enter a valid mobile number");
				tmobile.setText("");
				System.out.println("Exception : " +ex.getMessage());
			}
			
			
		}
		
		else if(text.equals("Back"))
		{
			this.setVisible(false);
			Member u = new Member();
			u.setVisible(true);
		}
	}
}