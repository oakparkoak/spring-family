package org.oakparkoak.redis;

import java.util.Arrays;

import io.lettuce.core.ReadFrom;
import org.oakparkoak.converter.ReadMoneyConverter;
import org.oakparkoak.converter.WriteMoneyConverter;
import org.oakparkoak.model.Coffee;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;

/**
 * @package: org.oakparkoak.redis
 * @author: Captain
 * @time: 3/13/2021 8:07 PM
 */
@Configuration
public class RedisConfig {
    /**
     * Config template for hash
     *
     * @param redisConnectionFactory
     * @return redisTemplate
     * @see org.springframework.data.redis.core.StringRedisTemplate
     */
    @Bean
    public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Coffee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * For redis repo save/get
     *
     * @return
     */
    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(new ReadMoneyConverter(), new WriteMoneyConverter()));
    }

    /**
     * How to use customizer config cluster: master read/write
     *
     * @return LettuceClient
     */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }
}
