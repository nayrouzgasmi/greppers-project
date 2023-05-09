package tn.esprit.pidev.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


import tn.esprit.pidev.Entities.Review;

public interface ReviewRepository extends PagingAndSortingRepository <Review,Long> {

    @Query(value = "SELECT r FROM Review r where r.product.id= :id_product AND r.isActive= true ")
    Page<Review> findReviewsByProductId(Pageable pageable,@Param("id_product") long id);

}