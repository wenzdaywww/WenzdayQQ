package www.ctrl.dbdao;

import java.sql.*;

import javax.swing.JOptionPane;

import www.util.DBConnect;

public class BaseDao {

	private Statement stmt;

	public BaseDao(){
		super();
		if (stmt==null) {
			try {
				stmt=DBConnect.getCon().createStatement();
			} catch (ClassNotFoundException e) {
				JOptionPane.showConfirmDialog(null, "◊¢≤· ß∞‹£°", "Ã· æ", JOptionPane.CLOSED_OPTION);
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, "◊¢≤· ß∞‹£°", "Ã· æ", JOptionPane.CLOSED_OPTION);
			}
		}
	}
	public ResultSet select(String sql){
		ResultSet set=null;
		if (stmt!=null) {
			try {
				set=stmt.executeQuery(sql);
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, "◊¢≤· ß∞‹£°", "Ã· æ", JOptionPane.CLOSED_OPTION);
			}
		}
		return set;
	}
	public int update(String sql){
		int row=0;
		if (stmt!=null) {
			try {
				row=stmt.executeUpdate(sql);
			} catch (SQLException e) {
				JOptionPane.showConfirmDialog(null, "◊¢≤· ß∞‹£°", "Ã· æ", JOptionPane.CLOSED_OPTION);
			}
		}
		return row;
	}
}
