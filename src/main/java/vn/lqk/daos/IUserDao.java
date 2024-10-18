package vn.lqk.daos;

import vn.lqk.models.UserModel;

public interface IUserDao {

	UserModel findByUsername(String username);
	void insert(UserModel user);
	boolean checkExistUsername(String username);
	void update(String username, String password);
	
}
