package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Review;

public interface IReviewService {

	
	Page<Review> findPaginated(int pageNo, int pageSize,String sortBy);
	
	List<Review> findAll();

	Optional<Review> findById(long id);

	Review save(Review review);

	Review updateReview(long id, Review updatedReview);

	void deleteById(long id);
	
	Optional<Product>  findByProductId(long id);


}
