package com.cab.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.book.model.BookingDetails;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<BookingDetails, Long> { 

}
