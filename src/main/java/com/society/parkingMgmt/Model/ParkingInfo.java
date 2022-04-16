package com.society.parkingMgmt.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParkingInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@NotNull
	private String owner;
	
	@NotNull
	private int slotNumber;
	
	@NotNull
	private String towerNumber;
	
	@NotNull
	private String vehicleNumber;
	
	@NotNull
	private String vehicleName;
	
	@NotNull
	private String vehicleType;
	
	@NotNull
	private Boolean parkingStatus;
	

}
