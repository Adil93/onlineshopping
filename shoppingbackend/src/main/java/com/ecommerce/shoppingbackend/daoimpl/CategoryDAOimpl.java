package com.ecommerce.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.shoppingbackend.dao.CategoryDAO;
import com.ecommerce.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOimpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();
	static {
		// first category
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is 32 inch Full HD televison displays");
		category.setImageUrl("CAT_1.png");

		categories.add(category);

		// second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("All Android Smartphones");
		category.setImageUrl("CAT_2.png");

		categories.add(category);

		// third category
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("All Android Smartphones");
		category.setImageUrl("CAT_3.png");

		categories.add(category);

	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		for (Category category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}
		return null;
	}

}
