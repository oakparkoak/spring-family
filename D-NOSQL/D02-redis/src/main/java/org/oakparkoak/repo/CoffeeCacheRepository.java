package org.oakparkoak.repo;

import java.util.Optional;

import org.oakparkoak.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/13/2021 8:43 PM
 */
public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {
    /**
     * By name: must set the secondary index
     *
     * @param name
     * @return
     * @see org.springframework.data.redis.core.index.Indexed
     */
    Optional<CoffeeCache> findOneByName(String name);
}
