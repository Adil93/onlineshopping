package com.ecommerce.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ecommerce.shoppingbackend.dao.CategoryDAO;
import com.ecommerce.shoppingbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ecommerce.shoppingbackend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
		// TODO Auto-generated method stub

	}

	/*@Test
	public void testAddCategory() {
		category = new Category();

		category.setName("Mobile");
		category.setDescription("All Android Smartphones");
		category.setImageUrl("CAT_2.png");
		
		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));
	}
	
	@Test
	public void testGetCategory() {
		category = categoryDAO.get(2);
		assertEquals("Successfully fetched a single category based on Id","Mobile",category.getName());
	}
	
	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(2);
		category.setDescription("All Type of Mobile including Android , IOS and other OS available");
		assertEquals("Successfully updated a single category in the table",true,categoryDAO.update(category));
	}

	
	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(2);
		assertEquals("Successfully deleted a single category in the table",true,categoryDAO.delete(category));
	}
	
	@Test
	public void testListCategory() {
		
		assertEquals("Successfully fetched active categories from table",1,categoryDAO.list().size());
	}*/
	
	@Test
	public void testCRUDCategory() {
		
		// Add Test case
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is a description of a Laptop");
		category.setImageUrl("CAT_3.png");
		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));
		
		category = new Category();
		category.setName("TV");
		category.setDescription("This is a description of a TV");
		category.setImageUrl("CAT_4.png");
		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));
		
		//Fetching and updating the category
		category = categoryDAO.get(1);
		category.setDescription("This is a description of Television");
		assertEquals("Successfully updated a single category in the table",true,categoryDAO.update(category));
		
		//delete the category
		category = categoryDAO.get(4);
		assertEquals("Successfully deleted a single category in the table",true,categoryDAO.delete(category));
		
		//fetching the list of Active Categories
		assertEquals("Successfully fetched active categories from table",2,categoryDAO.list().size());
		
		
	}
}
