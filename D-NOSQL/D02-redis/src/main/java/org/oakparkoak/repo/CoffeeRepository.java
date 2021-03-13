package org.oakparkoak.repo;

import org.oakparkoak.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/13/2021 8:03 PM
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}

