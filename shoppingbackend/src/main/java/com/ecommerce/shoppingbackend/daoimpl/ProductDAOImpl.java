package com.ecommerce.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.shoppingbackend.dao.ProductDAO;
import com.ecommerce.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Product product;

	@Override
	public Product get(int productId) {
		try {
			product = sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> listProducts() {
		List<Product> products = sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class)
				.getResultList();
		return products;
	}

	@Override
	public boolean add(Product product) {
		try {

			sessionFactory.getCurrentSession().persist(product);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> getActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active= :active";
		List<Product> activeProducts = sessionFactory.getCurrentSession()
				.createQuery(selectActiveProducts, Product.class).setParameter("active", true).getResultList();
		return activeProducts;
	}

	@Override
	public List<Product> getActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active= :active AND categoryId = :categoryId";
		List<Product> activeProductsByCategory = sessionFactory.getCurrentSession()
				.createQuery(selectActiveProductsByCategory, Product.class).setParameter("active", true)
				.setParameter("categoryId", categoryId).getResultList();
		return activeProductsByCategory;
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectLatestActiveProducts = "FROM Product WHERE active= :active ORDER BY id";
		List<Product> latestActiveProducts = sessionFactory.getCurrentSession()
				.createQuery(selectLatestActiveProducts, Product.class).setParameter("active", true).setFirstResult(0)
				.setMaxResults(count).getResultList();
		return latestActiveProducts;
	}

}
