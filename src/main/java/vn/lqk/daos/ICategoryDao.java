package vn.lqk.daos;
import java.util.List;

import vn.lqk.models.*;

public interface ICategoryDao {

	List<CategoryModel> findAll();
	CategoryModel findById(int categoryid);
	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int categoryid);
	List<CategoryModel> findByName(String keyword);
}
