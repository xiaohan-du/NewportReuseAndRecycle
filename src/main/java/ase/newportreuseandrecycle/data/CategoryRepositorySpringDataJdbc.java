package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepositorySpringDataJdbc extends CrudRepository<Category, Integer> {
}
