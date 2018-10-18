package org.qimei.shoppingCart.rules;

import java.util.List;

import org.qimei.shoppingCart.models.Deal;
import org.qimei.shoppingCart.models.Product;

/**
 * BundleDeal - Get item X for free for each purchase of sku
 * Format in properties file - sku=BundleDeal|freeItemSku
 * 
 * @author qimei
 *
 */
public class BundleDeal implements PricingDeal {

	@Override
	public void applyDeal(List<Product> products, Deal deal) {

		if (!products.isEmpty()) {
			
			String purchaseItems = deal.getSku();
			String freeItems = deal.getDealArgs()[0];
			int purchaseItemsCount = 0;
			int freeItemCount = 0;
			
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getSku().equals(purchaseItems)) {
					purchaseItemsCount++;
				}
				if (products.get(i).getSku().equals(freeItems)) {
					freeItemCount++;
				}
				
				// ensure the cart has both the freeItems and purchaseItems at the same time
				// update price to 0 for each freeItems for every purchase of purchaseItems
				if (products.get(i).getSku().equals(freeItems) && purchaseItemsCount >= freeItemCount) {
					products.get(i).setFinalPrice(0);
				}
			}
		}

	}

}
