package org.oakparkoak.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/10/2021 9:01 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coffee {
    private Long id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
