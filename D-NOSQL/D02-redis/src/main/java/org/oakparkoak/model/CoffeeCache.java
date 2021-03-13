package org.oakparkoak.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/13/2021 8:39 PM
 */
@RedisHash(value = "starbucks", timeToLive = 300)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeCache {
    @Id
    private Long id;

    /**
     * Secondary index
     *
     * @see org.oakparkoak.repo.CoffeeCacheRepository#findOneByName(String)
     */
    @Indexed
    private String name;

    private Money price;
}
