package com.ecommerce.onlineshopping.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.onlineshopping.exception.ProductNotFoundException;
import com.ecommerce.shoppingbackend.dao.CategoryDAO;
import com.ecommerce.shoppingbackend.dao.ProductDAO;
import com.ecommerce.shoppingbackend.dto.Category;
import com.ecommerce.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/index", "/home" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);
		
		logger.info("Inside Page Controller Index method 	- INFO");
		logger.debug("Inside Page Controller Index method - DEBUG");
		
		// Passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);

		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);

		return mv;
	}

	/**
	 * Methods to load all the products
	 */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);

		// Passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		return mv;
	}

	/**
	 * Show categories
	 * 
	 * @return
	 */
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		Category category = null;
		category = categoryDAO.get(id);
		// CategoryDAO to fetch a single category
		mv.addObject("title", category.getName());

		mv.addObject("category", category);
		// Passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickCategoryProducts", true);

		return mv;
	}

	/**
	 * method to view a single product
	 */
	
	@RequestMapping(value="/show/{id}/product")
	public  ModelAndView showSingleProductPage(@PathVariable("id") int id) throws ProductNotFoundException
	{
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		//update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct", true);
		return mv;
	}

}
