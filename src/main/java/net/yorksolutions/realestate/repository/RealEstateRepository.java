package net.yorksolutions.realestate.repository;

import net.yorksolutions.realestate.model.RealEstate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//actual connection to the table row
@Repository
public interface RealEstateRepository extends CrudRepository<RealEstate, Long> {

}

