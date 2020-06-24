package com.cab.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cab.book.model.Driver;

@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> { 
	Driver findByDriverName(String driverName);	
	Driver findByStatus(String status);
	
    @Modifying
    @Query("UPDATE Driver d SET d.status = :status WHERE d.did = :did")
    int updateStatus(@Param("did") Long did, @Param("status") String status);

    /*
     * Used Vincenty formula to find-out the nearest driver. 
     * Reference: https://www.plumislandmedia.net/mysql/vicenty-great-circle-distance-formula/
    */
    
	@Query(value = "SELECT *, (ATAN2(SQRT("
			+ "POW(COS(RADIANS(d.latitude))*SIN(RADIANS(d.longitude-:longitude)),2) + "
			+ "POW(COS(RADIANS(:latitude))*SIN(RADIANS(d.latitude)) - "
			+ "(SIN(RADIANS(:latitude))*COS(RADIANS(d.latitude)) * "
			+ "COS(RADIANS(d.longitude-:longitude))) ,2)), "
			+ "SIN(RADIANS(:latitude))*SIN(RADIANS(d.latitude)) +"
			+ "COS(RADIANS(:latitude))*COS(RADIANS(d.latitude))*COS(RADIANS(d.longitude-:longitude)))) as distance "
			+ "from driver_details d HAVING d.status='AVAILABLE' ORDER BY distance LIMIT 1", nativeQuery = true)
	Driver findNearByDriver(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
	
	@Query(value = "select d.driver_name as driverName, IF(d.status='BUSY', b.customer_name, '') as customerName, d.status as status from driver_details d "
			+ "LEFT OUTER JOIN booking_details b ON d.did=b.driver_did", nativeQuery = true)	
	List<DriverView> findAllDrivers();
}