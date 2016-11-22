import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUIfinal extends DbC implements ActionListener {
	private JFrame frmTheFourHats;
	private JTextField textField, // beds
	textField_1, //price per night
	textField_2, //nights
	textField_3, //first name
	textField_4, //last name
	textField_5, //phone number
	textField_6, //address
	textField_7, //city
	textField_8, //state
	textField_9, //zip
	textField_10, //cardnumber
	textField_11, //
	textField_12, //
	textField_13, //
	textField_14, //
	textField_15; //

	// Need this reference to Prices Tab
	JPanel panel_2;

	// For Check In
	private JComboBox dropDown;
	private List<DbC.RoomNumAndPrice> rnapList;

	// For Check Out
	private JComboBox dropDown2, dropDown3, dropDown4, dropDown5;
	private List<String> occupiedRoomList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIfinal window = new GUIfinal();
					window.frmTheFourHats.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIfinal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTheFourHats = new JFrame();
		frmTheFourHats.setTitle("The Four Hats Hotel");
		frmTheFourHats.setBounds(100, 100, 450, 450);
		frmTheFourHats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTheFourHats.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panel_2 = new JPanel();
		tabbedPane.addTab("Prices", null, panel_2, null);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Check In", null, panel, null);
		panel.setLayout(null);

		JLabel lblRoomSearch = new JLabel("Room Search");
		lblRoomSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoomSearch.setBounds(10, 11, 115, 14);
		panel.add(lblRoomSearch);

		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(10, 36, 46, 14);
		panel.add(lblRoom);

		JLabel lblBeds = new JLabel("Beds");
		lblBeds.setBounds(117, 36, 46, 14);
		panel.add(lblBeds);

		JLabel lblPricePerNight = new JLabel("Price Per Night");
		lblPricePerNight.setBounds(173, 36, 80, 14);
		panel.add(lblPricePerNight);

		JLabel lblNights = new JLabel("Nights");
		lblNights.setBounds(263, 36, 46, 14);
		panel.add(lblNights);

		dropDown = new JComboBox(); //room drop down
		dropDown.setActionCommand("ciROOMSELECT");
		dropDown.addActionListener(this);
		dropDown.setBounds(10, 53, 97, 20);
		panel.add(dropDown);

		textField = new JTextField(); //beds
		textField.setBounds(117, 53, 46, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); //price per night
		textField_1.setBounds(173, 53, 80, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField(); //nights
		textField_2.setBounds(263, 53, 46, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("Refresh");
		btnNewButton.setActionCommand("ciREFRESH");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(330, 52, 89, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Check In");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 91, 115, 14);
		panel.add(lblNewLabel);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 116, 81, 14);
		panel.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(111, 116, 86, 14);
		panel.add(lblLastName);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(239, 116, 82, 14);
		panel.add(lblPhoneNumber);

		textField_3 = new JTextField(); //first name
		textField_3.setBounds(10, 141, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField(); //last name
		textField_4.setBounds(111, 141, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField(); //phone number
		textField_5.setBounds(239, 141, 106, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 177, 46, 14);
		panel.add(lblAddress);

		textField_6 = new JTextField(); //address
		textField_6.setBounds(10, 202, 278, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("City");
		lblNewLabel_1.setBounds(10, 243, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("State");
		lblNewLabel_2.setBounds(117, 243, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Zip Code");
		lblNewLabel_3.setBounds(189, 243, 46, 14);
		panel.add(lblNewLabel_3);

		textField_7 = new JTextField(); //city
		textField_7.setBounds(10, 268, 86, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField(); //state
		textField_8.setBounds(108, 268, 55, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField(); //zip code
		textField_9.setBounds(189, 268, 86, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Card Number");
		lblNewLabel_4.setBounds(10, 312, 97, 14);
		panel.add(lblNewLabel_4);

		textField_10 = new JTextField(); //card number
		textField_10.setBounds(10, 337, 177, 20);
		panel.add(textField_10);
		textField_10.setColumns(10);

		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.setActionCommand("CHECKIN");
		btnCheckIn.addActionListener(this);
		btnCheckIn.setBounds(330, 336, 89, 23);
		btnCheckIn.addActionListener(this);
		panel.add(btnCheckIn);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Check Out", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblRoomSearch_1 = new JLabel("Room Search");
		lblRoomSearch_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRoomSearch_1.setBounds(10, 11, 113, 14);
		panel_1.add(lblRoomSearch_1);

		JLabel lblRooms = new JLabel("Rooms");
		lblRooms.setBounds(10, 36, 46, 14);
		panel_1.add(lblRooms);

		dropDown2 = new JComboBox(); //rooms drop down
		dropDown2.setActionCommand("coROOMSELECT");
		dropDown2.addActionListener(this);
		dropDown2.setBounds(10, 55, 86, 20);
		panel_1.add(dropDown2);

		JLabel lblNewLabel_5 = new JLabel("First Name");
		lblNewLabel_5.setBounds(116, 36, 67, 14);
		panel_1.add(lblNewLabel_5);

		JLabel lblLastName_1 = new JLabel("Last Name");
		lblLastName_1.setBounds(217, 36, 67, 14);
		panel_1.add(lblLastName_1);

		textField_11 = new JTextField(); //first name
		textField_11.setColumns(10);
		textField_11.setBounds(115, 55, 86, 20);
		textField_11.setEditable(false);
		panel_1.add(textField_11);

		textField_12 = new JTextField(); //last name
		textField_12.setColumns(10);
		textField_12.setBounds(217, 55, 86, 20);
		textField_12.setEditable(false);
		panel_1.add(textField_12);

		JLabel lblExtraCharges = new JLabel("Extra Charges");
		lblExtraCharges.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExtraCharges.setBounds(8, 102, 115, 20);
		panel_1.add(lblExtraCharges);

		dropDown3 = new JComboBox(); //HorseBack Rides
		dropDown3.setBounds(10, 150, 86, 20);
		dropDown3.addItem(" ");
		dropDown3.addItem("Yes");
		dropDown3.addItem("No");
		panel_1.add(dropDown3);

		JLabel lblHorsebackRides = new JLabel("HorseBack Rides");
		lblHorsebackRides.setBounds(10, 133, 96, 14);
		panel_1.add(lblHorsebackRides);

		JLabel lblSkyDive = new JLabel("Sky Dive");
		lblSkyDive.setBounds(111, 133, 72, 14);
		panel_1.add(lblSkyDive);

		dropDown4 = new JComboBox(); //Sky Dive
		dropDown4.setBounds(106, 150, 86, 20);
		dropDown4.addItem(" ");
		dropDown4.addItem("Yes");
		dropDown4.addItem("No");
		panel_1.add(dropDown4);

		JLabel lblRoomService = new JLabel("Room Service");
		lblRoomService.setBounds(204, 133, 80, 14);
		panel_1.add(lblRoomService);

		dropDown5 = new JComboBox(); //Room Service
		dropDown5.setBounds(198, 150, 86, 20);
		dropDown5.addItem(" ");
		dropDown5.addItem("Yes");
		dropDown5.addItem("No");
		panel_1.add(dropDown5);
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.setActionCommand("CHECKOUT");
		btnCheckOut.addActionListener(this);
		btnCheckOut.setBounds(217, 349, 89, 23);
		panel_1.add(btnCheckOut);

		JButton btnMarkReady = new JButton("Calc Total");
		btnMarkReady.setActionCommand("coCALCTOTAL");
		btnMarkReady.addActionListener(this);
		btnMarkReady.setBounds(118, 349, 89, 23);
		panel_1.add(btnMarkReady);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 229, 80, 14);
		panel_1.add(lblTotal);

		textField_14 = new JTextField(); //Display Total
		textField_14.setColumns(10);
		textField_14.setBounds(8, 246, 86, 20);
		textField_14.setEditable(false);
		panel_1.add(textField_14);

		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPayment.setBounds(10, 198, 115, 20);
		panel_1.add(lblPayment);

		JRadioButton checkBox = new JRadioButton("Cash");
		checkBox.setBounds(10, 288, 56, 23);
		panel_1.add(checkBox);

		JRadioButton chckbxCredit = new JRadioButton("Credit");
		chckbxCredit.setActionCommand("coPAYSELECT");
		chckbxCredit.addActionListener(this);
		chckbxCredit.setBounds(86, 288, 56, 23);
		panel_1.add(chckbxCredit);

		// Radio Button Group
		ButtonGroup rbg = new ButtonGroup();
		rbg.add(checkBox);
		rbg.add(chckbxCredit);

		textField_15 = new JTextField(); //credit card number
		textField_15.setColumns(10);
		textField_15.setBounds(148, 289, 188, 20);
		panel_1.add(textField_15);

		checkOutRefresh();
	}


	/**
	 *	CHECK IN
	 */
	private void checkInRefresh() {
		// Get "Beds" Input
		String bcText = textField.getText();
		int bc = Integer.parseInt( bcText );

		// Get the List for the jComboBox
		rnapList = searchAvailableRoomsByBedCount( bc );

		// Populate jComboBox
		dropDown.setModel( new DefaultComboBoxModel( rnapList.toArray() ) );
	}
	private void checkInRoomSelectChange() {
		int ppn;
		RoomNumAndPrice selection;

		// Get price per night for selection
		selection = (RoomNumAndPrice)dropDown.getSelectedItem();
		ppn = (int)selection.price;

		// Set PPN
		textField_1.setText( Integer.toString(ppn) );
	}
	private void doCheckIn() {
		int roomNum, customerNum, zip, cardNum, nights;
		String fname, lname, address, phone, city, state;
		Random rn = new Random();

		RoomNumAndPrice rnap = (RoomNumAndPrice)dropDown.getSelectedItem();

		// Get data from UI
		roomNum = rnap.room;
		customerNum = rn.nextInt(1000) + 1;
		fname = textField_3.getText();
		lname = textField_4.getText();
		address = textField_6.getText();
		phone = textField_5.getText();
		city = textField_7.getText();
		state = textField_8.getText();
		zip = Integer.parseInt( textField_9.getText() );
		cardNum = Integer.parseInt( textField_10.getText() );
		nights = Integer.parseInt( textField_2.getText() );

		saveCheckIn(roomNum, customerNum, fname, lname, address, phone, city, state, zip, cardNum, nights);
		markRoomOccupied(roomNum);
		checkOutRefresh();
	}

	/**
	 *	CHECK OUT
	 */
	private void checkOutRefresh() {
		// Get the List for the jComboBox
		occupiedRoomList = searchUnavailableRooms();

		// Populate jComboBox
		dropDown2.setModel( new DefaultComboBoxModel( occupiedRoomList.toArray() ) );
	}
	private void checkOutRoomSelectChange() {
		// Populate First and Last Name attached to the room
		int selectedRoom;
		String fname, lname;

		// Get selected room
		selectedRoom = Integer.parseInt( dropDown2.getSelectedItem().toString() );


		// Get First and Last Names from Room#
		String[] name = searchNameByRoomNum( selectedRoom );

		// Set TextField Text
		textField_11.setText( name[0] );
		textField_12.setText( name[1] );

	}
	private void checkOutCalculateTotal() {
		int selectedRoom;
		float total = 0;

		selectedRoom = Integer.parseInt( dropDown2.getSelectedItem().toString() );

		// Nights * Price Per Night
		total += roomPricexNights( selectedRoom );

		// Add-Ons
		if( String.valueOf( dropDown3.getSelectedItem() ) == "Yes" ) {	total += 50; }
		if( String.valueOf( dropDown4.getSelectedItem() ) == "Yes" ) {	total += 50; }
		if( String.valueOf( dropDown5.getSelectedItem() ) == "Yes" ) {	total += 50; }

		// Legal Theft (10% Tax)
		total *= 1.1;

		// Display Total
		textField_14.setText( Float.toString(total) );
	}
	private void checkOutCreditSelected() {
		// populate CC# field
		int selectedRoom, ccn;

		// Get Selected Room
		selectedRoom = Integer.parseInt( dropDown2.getSelectedItem().toString() );

		// Pull CCN attached to CCN
		ccn = ( searchCCNfromRoomNum( selectedRoom ) );

		// Display CCN in UI
		textField_15.setText( Integer.toString(ccn) );
	}
	private void doCheckOut() {
		/* Create a JPanel with the following:
			First Name, Last Name, Room #, Nights, add-ons prices, total price. */
		String fname, lname, rnum;
		float rtotal;

		fname = textField_11.getText();
		lname = textField_12.getText();

		rnum = (String)dropDown2.getSelectedItem();
		rtotal = Float.parseFloat( textField_14.getText() );

		// Dump Checkout Stats, use for invoice
		System.out.println( "First Name: " + fname );
		System.out.println( "Last Name: " + lname );
		System.out.println( "Room #: " + rnum );
		System.out.println( "Total: " + String.valueOf(rtotal) );

		markRoomVacant( Integer.parseInt(rnum) );
	}

	/**
	 *	ACTION LISTENER
	 */
	@Override
	public void actionPerformed( ActionEvent e ) {
		String actionCmd = e.getActionCommand();

		switch (actionCmd) {
			case "ciREFRESH":
				checkInRefresh();
				break;
			case "ciROOMSELECT":
				checkInRoomSelectChange();
				break;
			case "CHECKIN":
				doCheckIn();
				break;
			case "coROOMSELECT":
				checkOutRoomSelectChange();
				break;
			case "coPAYSELECT":
				checkOutCreditSelected();
				break;
			case "coCALCTOTAL":
				checkOutCalculateTotal();
				break;
			case "CHECKOUT":
				doCheckOut();
				break;
			default:
				break;
		}
	}
}