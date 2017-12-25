package com.ecommerce.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.shoppingbackend.dao.CategoryDAO;
import com.ecommerce.shoppingbackend.dto.Category;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "/", "/index", "/home" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);

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

}
