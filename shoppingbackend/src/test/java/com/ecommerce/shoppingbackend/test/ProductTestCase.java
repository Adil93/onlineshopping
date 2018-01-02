package com.ecommerce.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ecommerce.shoppingbackend.dao.ProductDAO;
import com.ecommerce.shoppingbackend.dto.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.ecommerce.shoppingbackend");
		context.refresh();

		productDAO = (ProductDAO) context.getBean("productDAO");
		// TODO Auto-generated method stub
	}
	
	@Test
	public void testCRUDCategory() {
		product = new Product();
		
		//added the product
		product.setName("Oppo Selfie S53");
		product.setBrand("OPPO");
		product.setDescription("This is description of oppo mobile");
		product.setUnitPrice(150000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong when added the product",true,productDAO.add(product));
		
		//updated the product
		product = productDAO.get(1);
		product.setUnitPrice(50000.00);
		
		assertEquals("Something went wrong when updated the product",true,productDAO.update(product));
		
		
		//delete the product
		product = productDAO.get(6);
		assertEquals("Something went wrong when deleted the product",true,productDAO.delete(product));
		
		//list the products
		assertEquals("Something went wrong when listed the products",6,productDAO.listProducts().size());
		
		//getActiveProductsByCategory
		assertEquals("Something went wrong when listed the products with respective of category id",3,productDAO.getActiveProductsByCategory(3).size());
		
		//getLatestActiveProducts
		assertEquals("Something went wrong when listed latest said number of products",4,productDAO.getLatestActiveProducts(4).size());
		

	}
}
