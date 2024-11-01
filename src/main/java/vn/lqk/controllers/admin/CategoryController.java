package vn.lqk.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.lqk.models.CategoryModel;
import vn.lqk.services.*;
import static vn.lqk.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
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
		} else if (url.contains("delete")){
			int categoryid = Integer.parseInt(req.getParameter("id"));
			cateService.delete(categoryid);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			CategoryModel category = new CategoryModel();
			String categoryname = req.getParameter("catename");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			String images = "";
			String uploadPath = DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					
					images = filename;
					
					part.write(uploadPath + "/" + filename);
					
					category.setImages(images);
				} else {
					category.setImages("avatar.png");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")){
			CategoryModel category = new CategoryModel();
			int categoryid = Integer.parseInt(req.getParameter("cateid"));
			String categoryname = req.getParameter("catename");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(statuss);
			
			CategoryModel cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();
			
			String images = "";
			String uploadPath = DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					
					images = filename;
					
					part.write(uploadPath + "/" + filename);
					
					category.setImages(images);
				} else {
					category.setImages(fileold);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} 
	}
}
