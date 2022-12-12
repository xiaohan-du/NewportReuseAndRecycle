package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ListingRepositorySpringDataJdbc extends CrudRepository<Listing, Integer>{
    List<Listing> findListingsByCategory(String category);
    Optional<Listing> findListingById(Integer id);
    List<Listing> findListingsByUserId(Integer userId);
}
