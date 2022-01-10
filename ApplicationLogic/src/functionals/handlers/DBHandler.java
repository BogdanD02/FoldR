package functionals.handlers;

import java.sql.*;

import functionals.containers.Bank;
import functionals.containers.Partner;
import functionals.containers.Person;		

/**
 * This class moves data to and from the database.
 * 
 * @version 1.0.0
 * @author David Bogdan
 * @since 1.0.0
 */
public final class DBHandler {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/Model";
	private static Connection conn;
	private Statement st;
	
	/**
	 * Instantiate a new database handler
	 */
	public DBHandler() {
		// Connect to database
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "1234");
			st = conn.createStatement();
		} catch(Exception e) {
			// Error connecting to database
			System.out.println(1);
		}
	}

	/**
	 * This function puts a string within quotes.
	 * 
	 * @param	obj		The string to be quoted
	 * @return	A new string with quotes.
	 */
	private String appendQuotes(String obj) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append('"');
		buffer.append(obj);
		buffer.append('"');
		
		return buffer.toString();
	}
	
	/**
	 * Clears the content of an SQL Statement.
	 */
	private void resetStatement() {
		try {
			st.clearBatch();
		} catch(Exception e) {
			
		}
	}
	
	/**
	 * Execute the SQL Query.
	 * 
	 * @param	querry	The SQL command to execute.
	 */
	private void exec(String querry) {
		try {
			st.executeUpdate(querry);
			resetStatement();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Executes a SELECT querry.
	 * 
	 * @param	querry	The query to be executed
	 * @return	A result obtained from the database.
	 */
	private ResultSet execSelect(String querry) {
		try {
			ResultSet s = st.executeQuery(querry);
			resetStatement();
			return s;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * Builds the command specific for insertion into the address table.
	 * 
	 * @param	dummy	The data container.
	 * @return	A string denoting the command.
	 */
	private String AdressInsertSQL(Partner dummy) {
		StringBuffer querryBuilder = new StringBuffer();
		
		querryBuilder.append("INSERT INTO Adrese (CIF_Firma, Adresa, Oras, CodPostal) VALUES (");
		
		querryBuilder.append(dummy.CIF);
		querryBuilder.append(", ");
	
		querryBuilder.append(appendQuotes(dummy.Address));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.Loc));
		querryBuilder.append(", ");
		
		querryBuilder.append(dummy.postalCode);
		querryBuilder.append(");");
		
		return querryBuilder.toString();
	}
	
	private String getAdressSQL(int CIF) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("SELECT ID FROM Adrese WHERE CIF_Firma = ");
		buffer.append(CIF);
		buffer.append(";");
		
		return buffer.toString();
	}
	
	/**
	 * Builds the command specific for partner insertion.
	 * 
	 * @param	dummy	The data of the partner
	 * @return	A string containing the command.
	 */
	private String PartnerInsertSQL(Partner dummy) {
		StringBuffer querryBuilder = new StringBuffer();
		
		querryBuilder.append("INSERT INTO Parteneri VALUES (");
		
		querryBuilder.append(dummy.CIF);
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.Name));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.RegCom));
		querryBuilder.append(", 0, ");
		
		querryBuilder.append(appendQuotes("01/01/21"));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes("01/01/21"));
		querryBuilder.append(", 0, ");
		
		querryBuilder.append(appendQuotes(dummy.ITM));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.CASS));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.CAEN));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes("01/01/21"));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.Type));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.Activity));
		querryBuilder.append(", ");
		
		querryBuilder.append(appendQuotes(dummy.TVA));
		querryBuilder.append(", ");
		
		if(dummy.Sal.equals("Are"))
			querryBuilder.append(1);
		else
			querryBuilder.append(0);
		querryBuilder.append(", ");
		
		ResultSet rs = execSelect( getAdressSQL(Integer.parseInt(dummy.CIF)) );
		
		try {
			if(rs.next()) {
				querryBuilder.append(rs.getString("ID"));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		querryBuilder.append(");");
		
		System.out.println(querryBuilder.toString());
		
		return querryBuilder.toString();
	}
	
	/**
	 * Builds the command specific for person insertion
	 * 
	 * @param	dummy	A person-type container.
	 * @param	CIF		The CIF code.
	 * 
	 * @return	A string denoting the command.
	 */
	private String PersonInsertSQL(Person dummy, String CIF) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("INSERT INTO PersContact VALUES (");
		
		buffer.append(CIF);
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Name));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Job));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.PhoneNumber));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Email));
		buffer.append(", ");
		
		buffer.append(dummy.CNP);
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.ID.Series));
		buffer.append(", ");
		
		buffer.append(dummy.ID.Number);
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.ID.Date));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.ID.Inst));
		buffer.append(", ");
		
		if(dummy.isNotified)
			buffer.append(1);
		else
			buffer.append(0);
		buffer.append(");");
		
		return buffer.toString();
	}
	
	/**
	 * Creates the command specific for bank insertion.
	 * 
	 * @param	dummy	A Bank-type filled container.
	 * @param 	CIF		The CIF code.
	 * 
	 * @return	A string denoting the SQL query.
	 */
	private String BankInsertSQL(Bank dummy, String CIF) {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("INSERT INTO Banks (CIF_Firma, Moneda, Denumire, Filiala, Cont) VALUES (");
		
		buffer.append(CIF);
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Coin));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Name));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Loc));
		buffer.append(", ");
		
		buffer.append(appendQuotes(dummy.Acc));
		buffer.append(");");
		
		return buffer.toString();
	}
	
	private String DeclSelectSQL() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("SELECT S1.CIF, S1.Denumire, S1.Cod_CASS, S2.Oras, S1.Salariati, S1.TVA FROM Parteneri S1, Adrese S2 WHERE S2.CIF_Firma = S1.CIF;");
		
		return buffer.toString();
	}
	
	/**
	 * Performs the corresponding insert statements.
	 * 
	 * @param	dummy	The data container.
	 */
	public void insertData(Partner dummy) {
		exec(AdressInsertSQL(dummy));
		exec(PartnerInsertSQL(dummy));
		
		for ( Person p : dummy.contactList ) {
			exec(PersonInsertSQL(p, dummy.CIF));
		}
		
		for ( Bank b : dummy.bankList ) {
			exec(BankInsertSQL(b, dummy.CIF));
		}
		
	}
	
	/**
	 * Get the list of documents
	 * 
	 * @return	A result set.
	 */
	@SuppressWarnings("exports")
	public ResultSet getDecl() {
		return execSelect(DeclSelectSQL());
	}
}
