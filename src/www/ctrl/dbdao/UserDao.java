package www.ctrl.dbdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import www.model.User;
import www.ui.MainDialog;
import www.util.Constants;
import www.util.DBConnect;

public class UserDao extends BaseDao {

	/**
	 * 用户登入
	 * @param userId
	 * @param password
	 * @return
	 */
	public User login(String userId,String password){
		ResultSet set=null;
		String sql="select * from table_account_info where account_id= ? and account_password= ?";
		try {
			PreparedStatement pStmt=DBConnect.getCon().prepareStatement(sql);
			pStmt.setString(1, userId);
			pStmt.setString(2, password);
			set=pStmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(MainDialog.getMain(), "登入失败！", "提示", JOptionPane.CLOSED_OPTION);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return getUser(set);
	}
	
	private User getUser(ResultSet set){
		User user=null;
		try {
			if(set.next()) {
				user=new User();
				user.setUserId(set.getString("account_id"));
				user.setUserPwd(set.getString("account_password"));
				user.setUserName(set.getString("account_name"));
				user.setUserAge(set.getString("account_age"));
				user.setUserSex(set.getString("account_sex"));
				user.setUserBirthday(set.getString("account_birthday"));
				return user;
			}
		} catch (SQLException e) { 
			JOptionPane.showConfirmDialog(MainDialog.getMain(), "获取用户失败！", "提示", JOptionPane.CLOSED_OPTION);
		}
		return user;
	}
	/**
	 * 显示好友
	 */
	public ArrayList<User> getFriend(String userId){
		ResultSet set=null;
		String sql="select friend_id,account_name,account_age,account_sex,account_birthday "
				+ "from (select friend_id from table_friend_group a left join "
				+ "table_friend_member b on a.group_id=b.group_id where account_id='"+userId+"') "
				+ "a left join table_account_info b on a.friend_id=b.account_id order by friend_id desc";
		set=select(sql);
		return getUserFriend(set);
	}
	
	private ArrayList<User> getUserFriend(ResultSet set){
		ArrayList<User> friendList=new ArrayList<User>();
		try {
			while(set.next()) {
				User friend=new User();
				friend.setUserId(set.getString("friend_id"));
				friend.setUserName(set.getString("account_name"));
				friend.setUserAge(set.getString("account_age"));
				friend.setUserSex(set.getString("account_sex"));
				friend.setUserBirthday(set.getString("account_birthday"));
				friendList.add(friend);
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(MainDialog.getMain(), "加载好友失败！", "提示", JOptionPane.CLOSED_OPTION);
		}
		return friendList;
	}
	/**
	 * 注册账号
	 * @param name
	 * @param password
	 * @param age
	 * @param sex
	 * @param birthday
	 * @return
	 */
	public int registUser(String name,String password,String age,String sex,String birthday){
		String sql="insert into table_account_info values ("+getLastUserId()+",'"+password+"','"+name+"',"+age+",'"+sex+"','"+birthday+"')";
		int row=update(sql);
		return row;
	}
	
	private String getLastUserId(){
		String strId=null;
		ResultSet idSet=null;
		ArrayList<String> strIdList=new ArrayList<String>();
		String sql="select account_id from table_account_info";
		idSet=select(sql);
		try {
			while (idSet.next()) {
				strIdList.add(idSet.getString("account_id"));
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(MainDialog.getMain(), "注册失败！", "提示", JOptionPane.CLOSED_OPTION);
		}
		strId=Integer.toString(10001+strIdList.size());
		Constants.REGIST_ID=strId;
		return strId;
	}
}
