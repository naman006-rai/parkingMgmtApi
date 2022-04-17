package com.society.parkingMgmt.Service;

import java.util.Arrays;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.society.parkingMgmt.Dao.DataRepo;
import com.society.parkingMgmt.Model.ParkingInfo;

@Service
public class ParkingMgmtServiceImpl implements ParkingMgmtService{
	
	  @Autowired
	  private RestTemplate restTemplate;
	  
	  @Autowired
	  private DataRepo dataRepo;

		@Value("${society-service.hostname}")
		private String societServiceHostname;

	@Override
	public ParkingInfo insertResident(ParkingInfo parkingInfo) throws ServiceException {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
		 String ownerData = restTemplate.exchange("http://"+societServiceHostname+":9091/society/residents/"+parkingInfo.getOwner(), HttpMethod.GET, entity, String.class).getBody();
		if(ownerData!=null) {
			 return dataRepo.save(parkingInfo);
		}
		throw new ServiceException("Owner not found");
	}

	@Override
	public ParkingInfo getDatabyOwner(String owner) {
		ParkingInfo data = dataRepo.findByOwner(owner);
		if(data == null) {
			throw new ServiceException("No Record Found");
		}
			return data;
	}

	@Override
	public ParkingInfo updateStatus(String owner, boolean status) {
		ParkingInfo data = dataRepo.findByOwner(owner);
		if(data!=null) {
			data.setParkingStatus(status);
			return dataRepo.save(data);
		}
		 throw new ServiceException("No Active parking found");
	}

	@Override
	public String deleteByOwner(String owner) throws ServiceException {
		ParkingInfo data = dataRepo.findByOwner(owner);
		if(data!=null) {
			if(data.getParkingStatus().equals(false)) {
				dataRepo.deleteById(data.getId());
				 return " Deleted Successfully";
			}
			else {
				 throw new ServiceException("Active parking found,can't be deleted");
			}
		}
		 throw new ServiceException("No Active parking found");
	}
	
	@Override
	public String deleteAll() throws ServiceException {
		dataRepo.deleteAll();
		return "Deleted Succesfully";
	}

	  
	  
}
