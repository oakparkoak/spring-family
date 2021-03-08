package org.oakparkoak.repo;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @package: org.oakparkoak.repo
 * @author: Captain
 * @time: 3/8/2021 10:20 AM
 */
@NoRepositoryBean
public interface BaseRepo<T, Long> extends PagingAndSortingRepository<T, Long> {
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
