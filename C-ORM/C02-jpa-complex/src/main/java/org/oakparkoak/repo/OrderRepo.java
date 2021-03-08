package org.oakparkoak.repo;

import java.util.List;

import org.oakparkoak.model.Order;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/8/2021 9:30 AM
 */
public interface OrderRepo extends BaseRepo<Order, Long> {
    List<Order> findByCustomerOrderById(String customer);

    List<Order> findByCoffees_Name(String name);

    List<Order> findByCoffeesName(String name);
}