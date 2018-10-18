package org.qimei.shoppingCart.models;

import java.util.Arrays;

/**
 * deal class representing every deals available in store (sku specific) configured in deals.properties
 * 
 * @author qimei
 *
 */
public class Deal {
	private final String sku;
	private final String dealName;
	private final String[] dealArgs;

	public Deal(String sku, String dealName, String[] dealArgs) {
		this.sku = sku;
		this.dealName = dealName;
		this.dealArgs = dealArgs;
	}

	public String getSku() {
		return sku;
	}

	public String getDealName() {
		return dealName;
	}

	public String[] getDealArgs() {
		return dealArgs;
	}

	public String toString() {
		return "sku:" + sku + ",deal name:" + dealName + ",dealArgs:" + Arrays.toString(dealArgs);
	}
}
