package org.oakparkoak.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.*;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/8/2021 9:30 AM
 */
@Entity
@Table(name = "T_ORDER")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4313454708548916066L;

    private String customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_ORDER_COFFEE",
            joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "COFFEE_ID", referencedColumnName = "ID")
    )
    private List<Coffee> coffees;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
}
