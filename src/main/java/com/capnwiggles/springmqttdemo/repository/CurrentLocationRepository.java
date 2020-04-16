package com.capnwiggles.springmqttdemo.repository;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CurrentLocationRepository extends JpaRepository<Trolley, Integer> {

    Trolley findByBoard(Integer boardNr);

}
