package org.qimei.shoppingCart.models;

/**
 * product class representing every products in store configured in products.properties
 * 
 * @author qimei
 */
public class Product {

	private final String sku;
	private final String name;
	private final double price;
	private double finalPrice;

	public Product(String sku, String name, double price, double finalPrice) {
		this.sku = sku;
		this.name = name;
		this.price = price;
		this.finalPrice = finalPrice;
	}

	// this is the object constructor used to clone/ create new object from the original object
	// original object created from products.properties
	public Product(Product productObj) {
		this(productObj.getSku(), productObj.getName(), productObj.getPrice(), productObj.getFinalPrice());
	}

	public String getSku() {
		return sku;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String toString() {
		return ("[sku:" + sku + ",name:" + name + ",price:" + price + ",final price:" + finalPrice + "]");
	}

}
