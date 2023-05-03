package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Review;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class ReviewService implements IReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	 @Autowired
	 private ProductRepository productRepository;

	@Override
	public List<Review> findAll() {
		return null ;
	}

	@Override
	public Optional<Review> findById(long id) {
		return reviewRepository.findById(id);
	}
	
	
	@Override
	public Optional<Product> findByProductId(long id) {
		return productRepository.findById(id);
	}


	@Override
	public Review save(Review review) {
		return reviewRepository.save(review);
	}

	@Override
	public Review updateReview(long id, Review updatedReview) {
		Optional<Review> optionalReview = reviewRepository.findById(id);

		if (optionalReview.isPresent()) {
			Review existingReview = optionalReview.get();
			existingReview.setActive(updatedReview.isActive());
			existingReview.setComment(updatedReview.getComment());
			existingReview.setNote(updatedReview.getNote());
			existingReview.setProduct(updatedReview.getProduct());
			existingReview.setUserName(updatedReview.getUserName());
			existingReview.setUserPhoto(updatedReview.getUserPhoto());
			return reviewRepository.save(existingReview);
		} else {
			return null;
		}
	}

	@Override
	public void deleteById(long id) {
		reviewRepository.deleteById(id);

	}

	@Override
	public Page<Review> findPaginated(int pageNo, int pageSize,String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Review> pagedResult = reviewRepository.findAll(paging);
        return pagedResult;
	}

}
