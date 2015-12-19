package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import entity.BuyerInterests;
import entity.Buyers;
import entity.SalesAgent;
import entity.Sellers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import jdbc.JDBCConnection;

public class InterestDelete {

	private JFrame frame;
	private JComboBox buyer = new JComboBox();
	private JComboBox property = new JComboBox();


	/**
	 * Launch the application.
	 */
			public void run() {
				try {
					InterestDelete window = new InterestDelete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the application.
	 */
	public InterestDelete() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 626);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddAgent = new JLabel("Delete Buyer Interest");
		lblAddAgent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAgent.setForeground(new Color(0, 255, 255));
		lblAddAgent.setFont(new Font("Lucida Grande", Font.PLAIN, 28));
		lblAddAgent.setBounds(29, 20, 340, 44);
		frame.getContentPane().add(lblAddAgent);
		
		String sqlstr = "select buyerid from buyers";
		JDBCConnection.executeQuery(sqlstr);
	    buyer = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				buyer.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		buyer.setToolTipText("");
		buyer.setBounds(209, 183, 134, 27);
		frame.getContentPane().add(buyer);
		
		String sqlstr2 = "select propertyid from properties";
		JDBCConnection.executeQuery(sqlstr2);
		property = new JComboBox();
		while(JDBCConnection.next())
		{
			try {
				property.addItem(JDBCConnection.resultSet.getString(1));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		property.setToolTipText("");
		property.setBounds(209, 310, 134, 27);
		frame.getContentPane().add(property);
		
		JButton submit = new JButton("Delete");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				Object[] options ={ "YES", "NO" };  
				int m = JOptionPane.showOptionDialog(null, "Are you sure to delete the record", "Delete?",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);  
				
				BuyerInterests bi = new BuyerInterests();
				bi.setBuyerid(buyer.getSelectedItem().toString());
				bi.setPropertyid(property.getSelectedItem().toString());
				
				String sql = "delete from buyer_interests where BUYERS_BUYERID =" + bi.getBuyerid() + "and" + "PROPERTIES_PROPERTYID =" + bi.getPropertyid();
				if(m == 0)
				{
					JDBCConnection.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Delete Success", "Success", JOptionPane.ERROR_MESSAGE);	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Delete Cancel", "Cancel", JOptionPane.ERROR_MESSAGE);	

				}
				
			}
		});
		submit.setBounds(67, 554, 117, 29);
		frame.getContentPane().add(submit);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancel.setBounds(226, 554, 117, 29);
		frame.getContentPane().add(cancel);
		
		JLabel lblContactid = new JLabel("PropertyID");
		lblContactid.setBounds(86, 309, 98, 26);
		frame.getContentPane().add(lblContactid);
		
		JLabel lblBuyerid = new JLabel("BuyerID");
		lblBuyerid.setBounds(86, 182, 98, 26);
		frame.getContentPane().add(lblBuyerid);
		
	    
		
		
	}
}
