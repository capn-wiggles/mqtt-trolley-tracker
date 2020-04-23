package com.capnwiggles.springmqttdemo.domain;

import com.capnwiggles.springmqttdemo.domain.deserializer.TrolleyDeserializer;
import com.capnwiggles.springmqttdemo.domain.serializer.TimestampSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "current_location")
@JsonDeserialize(using = TrolleyDeserializer.class)
@Data
public class Trolley {

    @Id
    @Column(name = "board_nr")
    private Integer board;

    @Column(name = "lat")
    private double latitude;

    @Column(name = "lon")
    private double longitude;

    private Integer speed;
    
    private Integer direction;

    @JsonSerialize(using= TimestampSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @JsonIgnore
    @Column(name = "rtu_id")
    private Integer rtuId;

    private Integer route;
}
