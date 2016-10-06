package com.shop.dreampizza.dao;

import com.shop.dreampizza.bean.Recipe;

import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public interface RecipeDao {

    Recipe[] getRecipeByPizaId(int id);
}
