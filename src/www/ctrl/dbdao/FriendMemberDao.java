package www.ctrl.dbdao;

public class FriendMemberDao extends BaseDao {

	/**
	 * �޺ú��ѱ�ע
	 * @param userGroupId
	 * @param friendId
	 * @param friendName
	 * @return
	 */
	public int setFriendName(String userGroupId,String friendId,String friendName){
		int row=0;
		String sql="update table_friend_member set friend_name='"+friendName+"' where group_id="+userGroupId+" and friend_id="+friendId;
		row=update(sql);
		return row;
	}
	/**
	 * ɾ������
	 * @param userGroupId
	 * @param friendId
	 * @return
	 */
	public int deleteFriend(String userGroupId,String friendId){
		int row=0;
		String sql="delete from table_friend_member where group_id="+userGroupId+" and friend_id="+friendId;
		row=update(sql);
		return row;
	}
	/**
	 * ��Ӻ���
	 * @param userGroupId
	 * @param friendId
	 * @return
	 */
	public int addFriend(String userGroupId,String friendId){
		int row=0;
		String sql="insert into table_friend_member values (null,"+userGroupId+","+friendId+",null)";
		row=update(sql);
		return row;
	}
	/**
	 * �ƶ�����
	 * @param nowGroupId
	 * @param newGroupId
	 * @param friendId
	 * @return
	 */
	public int moveFriend(String nowGroupId,String newGroupId,String friendId){
		int row=0;
		String sql="update table_friend_member set group_id="+newGroupId+" where group_id="+nowGroupId+" and friend_id="+friendId;
		row=update(sql);
		return row;
	}
}
