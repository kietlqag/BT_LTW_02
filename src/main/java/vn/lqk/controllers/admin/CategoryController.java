package vn.lqk.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.lqk.models.CategoryModel;
import vn.lqk.services.*;

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ICategoryService cateService = new CategoryServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("categories")) {
			List<CategoryModel> listcate = cateService.findAll();
			req.setAttribute("listcate", listcate);
			req.getRequestDispatcher("/views/admin/listcategory.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/addcategory.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/editcategory.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			String categoryname = req.getParameter("catename");
			String images = "https://cdn11.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture//Apro/Apro_product_34981/oppo-a3-6gb128g_multi_0_556_450.png.webp";//req.getParameter("images");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")){
			int categoryid = Integer.parseInt(req.getParameter("cateid"));
			String categoryname = req.getParameter("catename");
			String images = "https://cdn11.dienmaycholon.vn/filewebdmclnew/DMCL21/Picture//Apro/Apro_product_34981/oppo-a3-6gb128g_multi_0_556_450.png.webp";//req.getParameter("images");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(statuss);
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
