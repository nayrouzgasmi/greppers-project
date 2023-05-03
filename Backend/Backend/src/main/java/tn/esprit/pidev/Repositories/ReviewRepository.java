package tn.esprit.pidev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import tn.esprit.pidev.Entities.Review;

public interface ReviewRepository extends PagingAndSortingRepository <Review,Long> {

}
