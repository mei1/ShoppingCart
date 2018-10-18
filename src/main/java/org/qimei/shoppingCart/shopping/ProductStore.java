package org.qimei.shoppingCart.shopping;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.qimei.shoppingCart.models.Deal;
import org.qimei.shoppingCart.models.Product;
import org.qimei.shoppingCart.utils.Configs;

/**
 * Setting up this ProductStore is the first step in the app, 
 * it reads through the properties files for both products and deals,
 * then store the data in memory to be used across the app
 * 
 * @author qimei
 *
 */
public class ProductStore {

	Map<String, Product> products = new HashMap<String, Product>();
	Map<String, Deal> deals = new HashMap<String, Deal>();

	@SuppressWarnings("unchecked")
	public ProductStore(Configs config) {

		Enumeration<String> productsEnum = (Enumeration<String>) config.products().propertyNames();
		Enumeration<String> dealsEnum = (Enumeration<String>) config.deals().propertyNames();

		while (productsEnum.hasMoreElements()) {
			String key = productsEnum.nextElement();
			String[] values = config.products().getProperty(key).split("\\|");
			Product product = new Product(values[0], values[1], Double.valueOf(values[2]), Double.valueOf(values[2]));
			products.put(key, product);
		}

		while (dealsEnum.hasMoreElements()) {
			String key = dealsEnum.nextElement();
			String[] values = config.deals().getProperty(key).split("\\|");
			Deal deal = new Deal(key, values[0], Arrays.copyOfRange(values, 1, values.length));
			deals.put(key, deal);
		}
	}

	public Product getProduct(String productSku) {
		return products.get(productSku);
	}

	public Deal getDeal(String productSku) {
		return deals.get(productSku);
	}

}
