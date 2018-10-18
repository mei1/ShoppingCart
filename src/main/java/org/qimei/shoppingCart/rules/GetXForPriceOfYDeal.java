package org.qimei.shoppingCart.rules;

import java.util.ArrayList;
import java.util.List;

import org.qimei.shoppingCart.models.Deal;
import org.qimei.shoppingCart.models.Product;

/**
 * GetXForPriceOfYDeal - Get X qty for the price of Y qty 
 * Format in properties file - sku=GetXForPriceOfYDeal|saleQty|chargeQty
 * 
 * @author qimei
 *
 */
public class GetXForPriceOfYDeal implements PricingDeal {

	@Override
	public void applyDeal(List<Product> products, Deal deal) {

		List<List<Product>> subList = new ArrayList<>();

		if (!products.isEmpty()) {

			int saleQuantity = Integer.valueOf(deal.getDealArgs()[0]);
			int chargeQuantity = Integer.valueOf(deal.getDealArgs()[1]);

			// create subList to keep track of qty of item 
			// eg 3 for 2 deal: when buy 6 should get price of 4
			subList = createSubList(products, saleQuantity);
			for (int i = 0; i < subList.size(); i++) {
				for (int j = 0; j < subList.get(i).size(); j++) {

					if ((j + 1) > chargeQuantity) {
						// when the item counts reach to configured qty
						// it update the price to 0
						subList.get(i).get(j).setFinalPrice(0);
					}
				}
			}

		}

	}

	private <T> List<List<T>> createSubList(List<T> list, final int L) {
		List<List<T>> parts = new ArrayList<List<T>>();
		final int N = list.size();
		for (int i = 0; i < N; i += L) {
			parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
		}
		return parts;
	}

}
