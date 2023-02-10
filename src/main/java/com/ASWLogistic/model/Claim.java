package com.ASWLogistic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "claim" , uniqueConstraints = @UniqueConstraint(columnNames = "container_code"))
@Data
@NoArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "container_code")
    private String containerCode;

    @Column(name = "receiving_number")
    private String receivingNumber;

    @Column(name = "claim")
    private String claim;

    @Column(name = "quantity")
    private String qty;

    @Column(name = "price")
    private String price;

    @Column(name = "rate")
    private String rate;

    @Column(name = "amount")
    private String amount;

    @Column(name = "remark")
    private String remark;

    @Column(name = "files")
    private String files;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User userId;

}
