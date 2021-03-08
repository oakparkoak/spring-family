package org.oakparkoak.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/8/2021 9:30 AM
 */
@Entity
@Table(name = "T_COFFEE")
@Builder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -145132341816797239L;

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = @org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY"))
    private Money price;
}
