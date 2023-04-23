package org.study.gallery.backend.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    private int id;

    @Column
    private int memberId;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String payment;

    @Column(length = 50)
    private String cardNumber;

    @Column(length = 500)
    private String items;

}
