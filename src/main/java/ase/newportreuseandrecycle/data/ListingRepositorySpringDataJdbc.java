package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingRepositorySpringDataJdbc extends CrudRepository<Listing, Integer>{
    List<Listing> findListingsByCategory(String category);
}
