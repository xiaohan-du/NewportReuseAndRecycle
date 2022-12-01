package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepositorySpringDataJdbc extends CrudRepository<Listing, Integer>{

}
