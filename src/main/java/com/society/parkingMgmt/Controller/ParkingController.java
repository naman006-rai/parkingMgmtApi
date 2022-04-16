package com.society.parkingMgmt.Controller;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.parkingMgmt.Model.ParkingInfo;
import com.society.parkingMgmt.Service.ParkingMgmtService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	@Autowired
	private ParkingMgmtService service;
	
	@PostMapping("/insert")
	public ResponseEntity<ParkingInfo> insertResident(@RequestBody ParkingInfo parkingInfo) {

		ParkingInfo response = service.insertResident(parkingInfo);

		try{
			return new ResponseEntity<ParkingInfo>(response, HttpStatus.CREATED);
		}
		catch(ServiceException dataAlreadyExistsException ){
			return new ResponseEntity("Exception Occured", HttpStatus.CONFLICT);
		}

	}
	
	@GetMapping("/parking/{owner}")
	public ResponseEntity getDatabyName(@PathVariable (required= true) String owner ) {
		try{
			return new ResponseEntity(service.getDatabyOwner(owner), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/parking/{owner}/{status}")
	public ResponseEntity<ParkingInfo> updateStatus(@PathVariable (required= true)String owner ,@PathVariable(required= true) boolean status) {
		ParkingInfo response= service.updateStatus(owner,status);
		
		try{
			return new ResponseEntity<ParkingInfo>(response, HttpStatus.CREATED);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.CONFLICT);
		}

	}
	
	@DeleteMapping("/parking/{owner}")
	public ResponseEntity deletebyOwner(@PathVariable (required= true) String owner ) {
		try{
			return new ResponseEntity(service.deleteByOwner(owner), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping("/")
	public ResponseEntity deleteAll() {
		try{
			return new ResponseEntity(service.deleteAll(), HttpStatus.OK);
		}
		catch(ServiceException dataNotFoundException ){
			return new ResponseEntity(dataNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	

}
