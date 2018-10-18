package org.qimei.shoppingCart.rules;

import java.util.List;

import org.qimei.shoppingCart.models.Deal;
import org.qimei.shoppingCart.models.Product;

/**
 * BulkDiscountDeal - Price will drop to X when buy more than Y
 * Format in properties file - sku=BulkDiscountDeal|minQty|finalPrice
 * 
 * @author qimei
 *
 */
public class BulkDiscountDeal implements PricingDeal {

	@Override
	public void applyDeal(List<Product> products, Deal deal) {

		if (!products.isEmpty()) {
			
			int bulkQtyRequired = Integer.valueOf(deal.getDealArgs()[0]);
			double finalPrice = Double.valueOf(deal.getDealArgs()[1]);

			// update price for all items when min required qty is purchased
			if (products.size() > bulkQtyRequired) {
				for (int i = 0; i < products.size(); i++) {
					products.get(i).setFinalPrice(finalPrice);
				}
			}

		}

	}

}
