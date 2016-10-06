package com.shop.dreampizza.dao.impl.mongo.sequence;

import com.shop.dreampizza.bean.sequence.SequenceId;
import execption.SequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by Dmytro_Druppov on 10/1/2016.
 */
public class SequencePizzaDaoMongo implements SequenceDao {

    @Autowired private MongoOperations mongoOperations;

    public int getNextSequenceId(String id) {
        Query query = new Query(Criteria.where("id").is(id));

        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happened.
        SequenceId seqId =
                mongoOperations.findAndModify(query, update, options, SequenceId.class);

        //if no id, throws SequenceException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (seqId == null) {
            throw new SequenceException("Unable to get sequence id for key : " + id);
        }

        return seqId.getSeq();

    }

}
