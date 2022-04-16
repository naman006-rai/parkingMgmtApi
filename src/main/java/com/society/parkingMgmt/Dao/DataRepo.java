package com.society.parkingMgmt.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.parkingMgmt.Model.ParkingInfo;


public interface DataRepo extends JpaRepository<ParkingInfo,Integer>{

	ParkingInfo findByOwner(String owner);

}
