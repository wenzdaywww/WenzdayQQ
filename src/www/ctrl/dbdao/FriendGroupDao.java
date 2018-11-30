package www.ctrl.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import www.model.FriendGroup;
import www.ui.MainDialog;

public class FriendGroupDao extends BaseDao {

	/**
	 * ��ӷ���
	 * @param userId
	 * @param groupName
	 * @return
	 */
	public int addGroup(String userId,String groupName){
		int row=0;
		if (isSameName(userId, groupName)==false) {
			String sql="insert into table_friend_group values (null,'"+groupName+"',"+userId+")";
			row=update(sql);
		}
		return row;
	}
	/**
	 * ɾ������
	 * @param userId
	 * @param groupName
	 * @return
	 */
	public int deleteGroup(String userId,String groupName){
		int row=0;
		String sql="delete from table_friend_group where group_name='"+groupName+"' and account_id="+userId;
		row=update(sql);
		return row;
	}
	/**
	 * �޸ķ�����
	 * @param userId
	 * @param groupName
	 * @param newGroupName
	 * @return
	 */
	public int reviseGroupName(String userId,String groupName,String newGroupName){
		int row=0;
		if (!newGroupName.equals(groupName)) {
			String sql="update table_friend_group set group_name='"+newGroupName+"' where group_name='"+groupName+"' and account_id="+userId;
			row=update(sql);
		}
		return row;
	}
	/**
	 * ��ȡ�û����ѷ���
	 * @param userId
	 * @return
	 */
	public ArrayList<FriendGroup> showUserGroup(String userId){
		ResultSet set=null;
		String sql="select group_id,group_name,account_id from table_friend_group where account_id="+userId;
		set=select(sql);
		return getUserGroup(set);
	}
	private ArrayList<FriendGroup> getUserGroup(ResultSet set){
		ArrayList<FriendGroup> fGroupList=new ArrayList<FriendGroup>();
		try {
			while (set.next()) {
				FriendGroup fGroup=new FriendGroup();
				fGroup.setGroupId(set.getString("group_id"));
				fGroup.setGroupName(set.getString("group_name"));
				fGroup.setUserId("b.account_id");
				fGroupList.add(fGroup);
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(MainDialog.getMain(), "���غ��ѷ���ʧ�ܣ�", "��ʾ", JOptionPane.CLOSED_OPTION);
		}
		return fGroupList;
	}
	/**
	 * �ж��û����ѷ������Ƿ���ͬ
	 * @param userId
	 * @param groupName
	 * @return trueΪ��ͬ��������falseΪ��ͬ������
	 */
	private boolean isSameName(String userId,String groupName){
		boolean isSameName=true;
		ResultSet set=null;
		String sql="select group_id from table_friend_group where group_name='"+groupName+"' and account_id="+userId;
		set=select(sql);
		try {
			if (set.next()) {
				isSameName=true;
			}else {
				isSameName=false;
			}
		} catch (SQLException e) {
		}
		return isSameName;
	}
}
