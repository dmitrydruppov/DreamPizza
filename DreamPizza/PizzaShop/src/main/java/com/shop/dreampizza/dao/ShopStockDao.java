package com.shop.dreampizza.dao;

import com.shop.dreampizza.bean.Recipe;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public interface ShopStockDao {

    void update(Recipe[] recipe);

    Recipe[] getProductsByPizzaId(int id);

}

