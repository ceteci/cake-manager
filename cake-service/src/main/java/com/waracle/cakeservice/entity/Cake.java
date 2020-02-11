package com.waracle.cakeservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(value = "cakes")
public class Cake implements Serializable {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Setter
    @Column(value = "TITLE")
    private String title;

    @Setter
    @Column(value = "DESCRIPTION")
    private String desc;

    @Setter
    @Column(value = "IMAGE")
    private String image;
}
