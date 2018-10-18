package org.qimei.shoppingCart.rules;

import java.util.List;

import org.qimei.shoppingCart.models.Deal;
import org.qimei.shoppingCart.models.Product;

/**
 * every deal needs to implement the applyDeal method by updating the 
 * final price of each product in the list based on deal criteria
 * 
 * @author qimei
 */
public interface PricingDeal {

    void applyDeal(List<Product> products, Deal deal);

}
