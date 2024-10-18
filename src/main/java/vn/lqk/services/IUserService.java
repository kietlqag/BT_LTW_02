package vn.lqk.services;

import vn.lqk.models.UserModel;

public interface IUserService {

	UserModel login (String username, String password);
	UserModel findByUsername (String username);
	void insert(UserModel user);
	boolean register(String username, String password);
	boolean checkExistUsername(String username);
	boolean resetpass(String username, String password);
	
}
