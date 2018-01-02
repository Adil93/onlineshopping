package com.ecommerce.shoppingbackend.dao;

import java.util.List;

import com.ecommerce.shoppingbackend.dto.Product;

public interface ProductDAO {
	
	Product get(int productId);
	
	List<Product> listProducts();

	boolean add(Product product);

	boolean update(Product product);

	boolean delete(Product product);

	// business mehtods
	List<Product> getActiveProducts();

	List<Product> getActiveProductsByCategory(int categoryId);

	List<Product> getLatestActiveProducts(int count);

}
