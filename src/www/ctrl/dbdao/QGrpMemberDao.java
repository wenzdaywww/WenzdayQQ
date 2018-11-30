package www.ctrl.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import www.model.QQGroupMember;

public class QGrpMemberDao extends BaseDao {
	
	/**
	 * 添加群成员
	 * @param groupId
	 * @param memberId
	 * @return
	 */
	public int addGroupMember(String groupId,String memberId){
		int row=0;
		String sql="insert into table_qq_group_member values (null,"+groupId+","+memberId+")";
		row=update(sql);
		return row;
	}
	/**
	 * 删除群成员
	 * @param groupId
	 * @param memberId
	 * @return
	 */
	public int deleteGroupMember(String groupId,String memberId){
		int row=0;
		String sql="delete from table_qq_group_member where qq_group_id="+groupId+" and qq_group_account_id="+memberId;
		row=update(sql);
		return row;
	}
	/**
	 * 获取群成员
	 * @param groupId
	 * @return
	 */
	public ArrayList<QQGroupMember> getGroupMember(String groupId){
		ResultSet set=null;
		String sql="select qq_group_id,qq_group_account_id from table_qq_group_member where qq_group_id="+groupId;
		set=select(sql);
		return getMember(set);
	}
	private ArrayList<QQGroupMember> getMember(ResultSet set){
		ArrayList<QQGroupMember> qMemberList=new ArrayList<QQGroupMember>();
		try {
			while (set.next()) {
				QQGroupMember qMember=new QQGroupMember();
				qMember.setQqgrouId(set.getString("qq_group_id"));
				qMember.setMemberId(set.getString("qq_group_account_id"));
				qMemberList.add(qMember);
			}
		} catch (SQLException e) {
		}
		return qMemberList;
	}
}
