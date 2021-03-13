package org.oakparkoak.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/13/2021 12:05 PM
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {
    @Id
    private String id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;
}
