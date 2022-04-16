package com.society.parkingMgmt.Service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.util.MultiValueMap;

import com.society.parkingMgmt.Model.ParkingInfo;

public interface ParkingMgmtService {

	ParkingInfo insertResident(ParkingInfo parkingInfo) throws ServiceException;

	ParkingInfo getDatabyOwner(String owner) throws ServiceException;

	ParkingInfo updateStatus(String owner, boolean status) throws ServiceException;

	String deleteByOwner(String owner) throws ServiceException;

	String deleteAll() throws ServiceException;

}
