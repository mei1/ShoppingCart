package org.qimei.shoppingCart.shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.qimei.shoppingCart.models.Deal;
import org.qimei.shoppingCart.models.Product;
import org.qimei.shoppingCart.rules.BulkDiscountDeal;
import org.qimei.shoppingCart.rules.BundleDeal;
import org.qimei.shoppingCart.rules.GetXForPriceOfYDeal;
import org.qimei.shoppingCart.utils.Configs;

/**
 * CheckoutRegister that scans each item, group items by sku, 
 * apply deal by sku and sum up the total of final price
 * 
 * @author qimei
 *
 */
public class CheckoutRegister {

	ProductStore store = new ProductStore(
			new Configs("src/main/java/config/products.properties", "src/main/java/config/deals.properties"));

	List<Product> cart = new ArrayList<>();
	Map<String, List<Product>> groupedCart = new HashMap<>();

	public void read(String sku) {

		cart.add(new Product(store.getProduct(sku)));
	}

	public double total() {
		groupedCartBySku();
		applyDealsBySku();
		return getFinalPriceTotal();
	}

	private void groupedCartBySku() {

		for (int i = 0; i < cart.size(); i++) {
			String sku = cart.get(i).getSku();
			if (groupedCart.containsKey(sku)) {
				List<Product> list = groupedCart.get(sku);
				list.add(cart.get(i));
				groupedCart.put(sku, list);
			} else {
				List<Product> list = new ArrayList<>();
				list.add(cart.get(i));
				groupedCart.put(sku, list);
			}
		}
	}

	private void applyDealsBySku() {

		Iterator<String> groupedKeysFromCart = groupedCart.keySet().iterator();

		System.out.println("Checking out cart - " + groupedCart);

		while (groupedKeysFromCart.hasNext()) {

			String sku = groupedKeysFromCart.next();
			Deal dealForSku = store.getDeal(sku);
			String dealIdForSku = "";
			if (null != dealForSku) {
				dealIdForSku = dealForSku.getDealName();
			}
			List<Product> subList = groupedCart.get(sku);

			switch (dealIdForSku) {

			case "GetXForPriceOfYDeal":
				new GetXForPriceOfYDeal().applyDeal(subList, dealForSku);
				System.out.println("Applying deal " + dealIdForSku + " for sku " + sku + " - items " + subList);
				break;

			case "BulkDiscountDeal":
				new BulkDiscountDeal().applyDeal(subList, dealForSku);
				System.out.println("Applying deal " + dealIdForSku + " for sku " + sku + " - items " + subList);
				break;

			case "BundleDeal":
				new BundleDeal().applyDeal(cart, dealForSku);
				System.out.println("Applying deal " + dealIdForSku + " for sku " + sku + " - items " + cart);
				break;

			default:
				System.out.println("No deals applied for " + sku);
				break;
			}
		}
	}

	private double getFinalPriceTotal() {

		double finalPriceTotal = 0.00;
		Iterator<String> groupedKeysFromCart = groupedCart.keySet().iterator();

		while (groupedKeysFromCart.hasNext()) {

			String sku = groupedKeysFromCart.next();
			List<Product> subList = groupedCart.get(sku);

			for (int i = 0; i < subList.size(); i++) {
				finalPriceTotal = finalPriceTotal + subList.get(i).getFinalPrice();
			}

		}

		return finalPriceTotal;
	}

}
