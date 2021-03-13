package org.oakparkoak.repo;

import java.util.List;

import org.oakparkoak.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/13/2021 4:47 PM
 */
public interface CoffeeRepository extends MongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
