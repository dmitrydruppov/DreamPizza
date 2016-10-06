package com.shop.dreampizza.dao.impl.mongo.sequence;

/**
 * Created by Dmytro_Druppov on 10/1/2016.
 */
public interface SequenceDao {

    public int getNextSequenceId(String id);

}
