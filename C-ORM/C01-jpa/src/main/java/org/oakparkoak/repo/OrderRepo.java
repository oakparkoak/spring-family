package org.oakparkoak.repo;

import org.oakparkoak.model.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/7/2021 8:34 PM
 */
public interface OrderRepo extends CrudRepository<Order, Long> {
}