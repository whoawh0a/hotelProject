import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DbC {
	int count=0, intpid;

/*
	Charges
		  roomNum  customerNum  checkoutroomPrice  Tax  Room Service  horse  skyDive  total
	Checkin
		  roomNum  customerNum  fname  lname  address  phone  city  zip  cardNum  nights
	Rooms
		  roomNum  Status  Beds  Price
*/

	public List<RoomNumAndPrice> searchAvailableRoomsByBedCount(int bc) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<RoomNumAndPrice> fResults = new ArrayList<RoomNumAndPrice>();

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "SELECT * FROM Rooms WHERE Rooms.Status = ? AND Rooms.Beds = ?" );
			stmt.setInt(1, 0);
			stmt.setInt(2, bc);
			rs = stmt.executeQuery();

			while( rs.next() ) {
				RoomNumAndPrice rnap = new RoomNumAndPrice();
				rnap.room = rs.getInt( "roomNum" );
				rnap.price = rs.getInt( "Price" );
				fResults.add( rnap );
			}

		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}

		return fResults;
	}

	public void saveCheckIn(int roomn, int custn, String fname, String lname, String addy,
							String phone, String city, String state, int zip, int ccn, int nights) {
		Connection c = null;
		PreparedStatement stmt = null;

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement(
							"INSERT INTO Checkin " +
							"(roomNum,customerNum,fname,lname,address,phone,city,state,zip,cardNum,nights) " +
							"VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, roomn);
			stmt.setInt(2, custn);
			stmt.setString(3, fname);
			stmt.setString(4, lname);
			stmt.setString(5, addy);
			stmt.setString(6, phone);
			stmt.setString(7, city);
			stmt.setString(8, state);
			stmt.setInt(9, zip);
			stmt.setInt(10, ccn);
			stmt.setInt(11, nights);
			stmt.executeUpdate();
		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}
	}


	public List<String> searchUnavailableRooms() {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> fResults = new ArrayList<String>();

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "SELECT * FROM Rooms WHERE Rooms.Status = ?" );
			stmt.setInt(1, -1);
			rs = stmt.executeQuery();

			// Walk Results
			int x;
			String y;
			while( rs.next() ) {
				x = rs.getInt( "roomNum" );
				y = Integer.toString(x);
				fResults.add( y );
			}

		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}

		return fResults;
	}

	public String[] searchNameByRoomNum(int roomNum) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String[] name = null;

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "SELECT Checkin.fname,Checkin.lname FROM Checkin WHERE Checkin.roomNum = ?" );
			stmt.setInt(1, roomNum);
			rs = stmt.executeQuery();

			// Get fname, lname
			if( rs.next() ) {
				String fn, ln;
				fn = rs.getString( "fname" );
				ln = rs.getString( "lname" );
				name = new String[]{fn, ln};
			}

		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}

		return name;
	}

	public float roomPricexNights( int roomNum ) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		float tot = 0;

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "SELECT Checkin.nights,Rooms.Price " +
										"FROM Checkin INNER JOIN Rooms ON Checkin.roomNum = Rooms.roomNum " +
										"WHERE Checkin.roomNum = ?" );
			stmt.setInt(1, roomNum);
			rs = stmt.executeQuery();

			// Add PPN*N to Total
			if( rs.next() ) {
				int p, n;
				p = rs.getInt( "Price" );
				n = rs.getInt( "nights" );
				tot += p*n;
			}
		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}

		return tot;
	}

	public int searchCCNfromRoomNum( int roomNum ) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ccn = 0;

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "SELECT Checkin.cardNum FROM Checkin WHERE Checkin.roomNum = ?" );
			stmt.setInt(1, roomNum);
			rs = stmt.executeQuery();

			// Add PPN*N to Total
			if( rs.next() ) {
				ccn = rs.getInt( "cardNum" );
			}
		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}

		return ccn;
	}

	public void markRoomVacant( int roomNum ) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ccn = 0;

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "UPDATE Rooms SET Status = ? WHERE roomNum = ?" );
			stmt.setInt(1, 0);
			stmt.setInt(2, roomNum);
			stmt.executeUpdate();
		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}
	}
	public void markRoomOccupied( int roomNum ) {
		Connection c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ccn = 0;

		try {
			c = DriverManager.getConnection( "jdbc:odbc:Hotel" );

			stmt = c.prepareStatement( "UPDATE Rooms SET Status = ? WHERE roomNum = ?" );
			stmt.setInt(1, -1);
			stmt.setInt(2, roomNum);
			stmt.executeUpdate();
		}
		catch ( SQLException sqlException ) {
			System.out.println( sqlException.getMessage() );
		}
	}

	public class RoomNumAndPrice {
		public int room, price;

		@Override
		public String toString() {
			return "Room #" + Integer.toString( room );
		}
	}

}  // end class DbConnection

