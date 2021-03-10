package org.oakparkoak.mapper;

import org.apache.ibatis.annotations.*;
import org.oakparkoak.model.Coffee;

/**
 * 1.MybatisAutoConfiguration -> scanner.setAnnotationClass(Mapper.class)
 * <p>
 * 2.@MapperScan("org.oakparkoak.mapper")
 *
 * @package: org.oakparkoak.mapper
 * @author: Captain
 * @time: 3/10/2021 9:00 PM
 */
@Mapper
public interface CoffeeMapper {
    /**
     * UseGeneratedKeys: set id to coffee
     *
     * @param coffee
     * @return
     */
    @Insert("insert into T_COFFEE (NAME, PRICE, CREATE_TIME, UPDATE_TIME) " +
            "values (#{name}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    int save(Coffee coffee);

    /**
     * create_time => createTime
     * <p>
     * 1.Use result annotation
     * <p>
     * 2.Use map-underscore-to-camel-case
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM T_COFFEE where ID = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime")
    })
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Coffee findById(Long id);
}
