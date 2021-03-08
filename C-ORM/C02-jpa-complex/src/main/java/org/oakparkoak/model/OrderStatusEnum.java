package org.oakparkoak.model;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/8/2021 10:14 AM
 */
public enum OrderStatusEnum {
    /**
     * Just create order
     */
    INIT,

    /**
     * Already pay
     */
    PAID,

    /**
     * Is making coffee
     */
    BREWING,

    /**
     * Already make
     */
    BREWED,

    /**
     * Already take by customer
     */
    TAKEN,

    /**
     * Cancel by customer
     */
    CANCELLED
}
