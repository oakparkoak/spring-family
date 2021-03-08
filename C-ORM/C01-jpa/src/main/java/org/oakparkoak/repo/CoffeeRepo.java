package org.oakparkoak.repo;

import org.oakparkoak.model.Coffee;
import org.springframework.data.repository.CrudRepository;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/7/2021 8:34 PM
 */
public interface CoffeeRepo extends CrudRepository<Coffee, Long> {
}
