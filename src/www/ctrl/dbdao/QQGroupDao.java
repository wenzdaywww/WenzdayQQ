package www.ctrl.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import www.model.QQGroup;

public class QQGroupDao extends BaseDao {

	/**
	 * 创建QQ群
	 * @param groupName
	 * @param creatorId
	 * @return
	 */
	public int addGroup(String groupName,String creatorId){
		int row=0;
		String sql="insert into table_qq_group values (null,'"+groupName+"',"+creatorId+")";
		row=update(sql);
		return row;
	}
	/**
	 * 解散QQ群
	 * @param creatorId
	 * @param qqgroupId
	 * @return
	 */
	public int deleteGroup(String creatorId,String qqgroupId){
		int row=0;
		String sql="delete from table_qq_group where qq_group_id='"+qqgroupId+"' and account_id="+creatorId;
		row=update(sql);
		return row;
	}
	/**
	 * 修改QQ群名
	 * @param creatorId
	 * @param qqgroupId
	 * @param newGroupName
	 * @return
	 */
	public int reviseGroupName(String creatorId,String qqgroupId,String newGroupName){
		int row=0;
		String sql="update table_qq_group set qq_group_name='"+newGroupName+"' where qq_group_id='"+qqgroupId+"' and account_id="+creatorId;
		row=update(sql);
		return row;
	}
	/**
	 * 获取用户创建的所有群
	 * @param creatorId
	 * @return
	 */
	public ArrayList<QQGroup> getCreatorGroup(String creatorId){
		ResultSet set=null;
		String sql="select qq_group_id,qq_group_name,account_id from table_qq_group where account_id="+creatorId;
		set=select(sql);
		return getGroup(set);
	}
	/**
	 * 获取所有群
	 * @param creatorId
	 * @return
	 */
	public ArrayList<QQGroup> getAllGroup(){
		ResultSet set=null;
		String sql="select qq_group_id,qq_group_name,account_id from table_qq_group";
		set=select(sql);
		return getGroup(set);
	}
	private ArrayList<QQGroup> getGroup(ResultSet set){
		ArrayList<QQGroup> qGroupList=new ArrayList<QQGroup>();
		try {
			while (set.next()) {
				QQGroup qqGroup=new QQGroup();
				qqGroup.setQqgroupId(set.getString("qq_group_id"));
				qqGroup.setQqgroupName(set.getString("qq_group_name"));
				qqGroup.setCreatorId(set.getString("account_id"));
				qGroupList.add(qqGroup);
			}
		} catch (SQLException e) {
		}
		return qGroupList;
	}
}
