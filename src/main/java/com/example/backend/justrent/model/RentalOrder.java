package com.example.backend.justrent.model;

import java.util.Date;

import com.example.backend.justrent.services.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class RentalOrder {
    @Id
    @Column(name = "order_id", unique = true, nullable = false)
    private String orderId;

    @Column(name = "rental_id", nullable = false)
    private String rentalId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "delivery_option", nullable = false)
    private String deliveryOption;

    @Column(name = "message", length = 1000)
    private String message;
    @Column(name = "status", nullable = false)
    private OrderStatus status;
    private String userName;

    public RentalOrder() {

    }

    public RentalOrder(String rentalId, String userId,String userName, Date startDate, Date endDate, String deliveryOption, String message) {
        Common common = new Common();
        this.orderId = common.generateUserId("RO-");
        this.rentalId = rentalId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userName = userName;
        this.deliveryOption = deliveryOption;
        this.message = message;
        this.status = OrderStatus.PENDING;
    }
}