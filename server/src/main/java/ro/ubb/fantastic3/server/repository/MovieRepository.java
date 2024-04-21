package ro.ubb.fantastic3.server.repository;

import org.springframework.stereotype.Repository;
import ro.ubb.fantastic3.common.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends GenericRepository<Movie,Long>{

    List<Movie> findByOrderByRentalCounterDesc();

    List<Movie> findByTitle(String name);
}
