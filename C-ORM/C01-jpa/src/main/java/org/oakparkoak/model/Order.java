package org.oakparkoak.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/7/2021 8:30 PM
 */
@Entity
@Table(name = "T_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = -4313454708548916066L;

    @Id
    @GeneratedValue
    private Long id;

    private String customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_ORDER_COFFEE",
            joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "COFFEE_ID", referencedColumnName = "ID")
    )
    private List<Coffee> coffees;

    @Column(nullable = false)
    private Integer state;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}
