package com.veselkov.hakatom.Repositories;

import com.veselkov.hakatom.Entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    @Query(value = "SELECT COUNT(DISTINCT (UPPER(VENDOR_NAME))) FROM Vendor", nativeQuery = true)
    Long countDistinctVendorBy();
}
