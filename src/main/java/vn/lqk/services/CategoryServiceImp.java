package vn.lqk.services;

import java.util.List;

import vn.lqk.daos.CategoryDaoImp;
import vn.lqk.models.CategoryModel;
import vn.lqk.daos.*;

public class CategoryServiceImp implements ICategoryService {

	public ICategoryDao cateDao = new CategoryDaoImp();

	@Override
	public List<CategoryModel> findAll() {
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findById(int categoryid) {
		return cateDao.findById(categoryid);
	}

	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);

	}

	@Override
	public void update(CategoryModel category) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(category.getCategoryid());
		if (cate != null) {
			cateDao.update(category);
		}
	}

	@Override
	public void delete(int categoryid) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(categoryid);
		if (cate != null) {
			cateDao.delete(categoryid);
		}

	}

	@Override
	public List<CategoryModel> findByName(String keyword) {
		return cateDao.findByName(keyword);

	}

}
