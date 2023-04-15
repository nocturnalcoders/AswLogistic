package com.ASWLogistic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "invoice_detail")
@Data
@NoArgsConstructor
public class InvoiceDetail {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String invoiceId;

    private String goodsName;

    private String summary;

    private String unit;

    private String price;

    private String discount;

    private String subtotal;

}
