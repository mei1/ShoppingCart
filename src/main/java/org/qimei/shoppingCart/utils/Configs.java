package org.qimei.shoppingCart.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Util class to load properties files
 * 
 * @author qimei
 *
 */
public class Configs {
	
	private static Properties products = new Properties();
	private static Properties deals = new Properties();

	public Configs(String productsPropFile, String dealsPropFile){
		try {

			// load a properties file
			products.load(new FileInputStream(productsPropFile));
			deals.load(new FileInputStream(dealsPropFile));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public Properties products(){
		return products;
	}
	
	public Properties deals(){
		return deals;
	}
}
