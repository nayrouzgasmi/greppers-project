package tn.esprit.pidev.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Review;
import tn.esprit.pidev.Services.IReviewService;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

@RestController
@CrossOrigin
@RequestMapping("/api/reviews")
public class ReviewController {

	private final Path root = Paths.get("src/main/resources/static/uploads");

	@Autowired
	private IReviewService reviewService;

	// to add new review
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Review> createReview(@RequestParam("file") MultipartFile file,
			@RequestParam("comment") String comment,@RequestParam("userName") String userName,@RequestParam("active") String active
			,@RequestParam("note") String note) {
		Review r = new Review();
		Product p = new Product();
		p.setId(2);
		r.setActive(Boolean.parseBoolean(active));
		r.setComment(comment);
		r.setNote(Integer.parseInt(note));
		r.setUserName(userName);
		r.setProduct(p);
		r.setUserPhoto(file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {

			}

			throw new RuntimeException(e.getMessage());
		}
		Review createdReview = reviewService.save(r);
		return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
	}

	// to get list of reviews
	@GetMapping("")
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.findAll();
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	// list pagination

	@GetMapping("list")
	public ResponseEntity<Map<String, Object>> getAllReviewsPagination(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<Review> listReviews = new ArrayList<Review>();
		Page<Review> reviews = reviewService.findPaginated(pageNo, pageSize, sortBy);
		listReviews = reviews.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("reviews", listReviews);
		response.put("currentPage", reviews.getNumber());
		response.put("totalItems", reviews.getTotalElements());
		response.put("totalPages", reviews.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// to get review by id
	@GetMapping("/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable("id") long id) {
		Optional<Review> review = reviewService.findById(id);
		if (review.isPresent()) {
			return new ResponseEntity<>(review.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// to update review
	@PutMapping("/{id}")
	public ResponseEntity<Review> updateReview(@PathVariable("id") long id, @RequestBody Review review) {
		Review updatedReview = reviewService.updateReview(id, review);
		if (updatedReview != null) {
			return new ResponseEntity<>(updatedReview, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable("id") long id) {
		reviewService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
