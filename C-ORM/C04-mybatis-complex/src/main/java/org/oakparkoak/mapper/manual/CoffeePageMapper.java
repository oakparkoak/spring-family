package org.oakparkoak.mapper.manual;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.oakparkoak.model.auto.Coffee;

/**
 * @package: org.oakparkoak.mapper.manual
 * @author: Captain
 * @time: 3/11/2021 11:36 AM
 */
public interface CoffeePageMapper {
    @Select("select * from T_COFFEE order by id")
    List<Coffee> listWithRowBounds(RowBounds rowBounds);

    @Select("select * from T_COFFEE order by id")
    List<Coffee> list(int pageNum, int pageSize);
}
