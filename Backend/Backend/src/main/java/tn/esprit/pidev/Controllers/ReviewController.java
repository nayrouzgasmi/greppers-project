package tn.esprit.pidev.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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

import net.bytebuddy.utility.RandomString;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Review;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Services.IObjectStorageService;
import tn.esprit.pidev.Services.IReviewService;
import tn.esprit.pidev.Services.ProductService;

import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;

@RestController
@CrossOrigin
@RequestMapping("/api/reviews")
public class ReviewController {

	private static final String ThreadLocalRandom = null;

	private final Path root = Paths.get(
			"C:\\Users\\mohamed\\Documents\\workspace-sts-3.8.4.RELEASE\\greppers-project\\Frontend\\Admin\\src\\assets\\imgs\\people");

	@Autowired
	private IReviewService reviewService;
	@Autowired
	private IObjectStorageService objectStorageService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;

	// to add new review
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Review> createReview(@RequestParam(value = "file",required = false) MultipartFile file,
			@RequestParam("comment") String comment, @RequestParam("userName") String userName,
			@RequestParam("active") String active, @RequestParam("note") String note,
			@RequestParam("product") String product_id) {
			Review r = new Review();
			Product p = new Product();
			p.setId(Long.parseLong(product_id));

			r.setActive(Boolean.parseBoolean(active));
			r.setComment(comment);
			r.setNote(Integer.parseInt(note));
			r.setUserName(userName);
			r.setProduct(p);

			// generate random string for photo
			String generatedString = UUID.randomUUID().toString().concat(".");

			// String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			// String filename = generatedString.concat(ext);
			if(file!=null){
				String picture=objectStorageService.saveFileAlone(file, "");
				r.setUserPhoto(picture);}
			Review createdReview = reviewService.save(r);
			return new ResponseEntity<>(createdReview, HttpStatus.CREATED);


	}
	@PostMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Review> createReviewFront(@RequestParam(value = "file",required = false) MultipartFile file,
			@RequestParam("comment") String comment, @RequestParam("userName") String userName,
			@RequestParam("active") String active, @RequestParam("note") String note,
			@RequestParam("product") String product_id,@PathVariable("id") Long id) {
			Review r = new Review();
			Product product = productRepository.findById(id).get();

			r.setActive(Boolean.parseBoolean(active));
			r.setComment(comment);
			r.setNote(Integer.parseInt(note));
			r.setUserName(userName);
			r.setProduct(product);

			// generate random string for photo
			String generatedString = UUID.randomUUID().toString().concat(".");

			// String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			// String filename = generatedString.concat(ext);
			if(file!=null){
				String picture=objectStorageService.saveFileAlone(file, "");
				r.setUserPhoto(picture);}
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
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Review> updateReview(@PathVariable("id") long id,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("comment") String comment, @RequestParam("userName") String userName,
			@RequestParam("active") String active, @RequestParam("note") String note,
			@RequestParam("userPhoto") String userPhoto, @RequestParam("product") String product_id) {

		Review r = new Review();
		Product p = new Product();
		p.setId(Long.parseLong(product_id));
		r.setActive(Boolean.parseBoolean(active));
		r.setComment(comment);
		r.setNote(Integer.parseInt(note));
		r.setUserName(userName);
		r.setProduct(p);
		if (file != null) {
			// String generatedString = UUID.randomUUID().toString().concat(".");
			// String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			// String filename = generatedString.concat(ext);

			try {
				// Files.copy(file.getInputStream(), this.root.resolve(filename));
				String picture=objectStorageService.saveFileAlone(file, "");
				r.setUserPhoto(picture);
				// r.setUserPhoto(filename);

			} catch (Exception e) {
				if (e instanceof FileAlreadyExistsException) {

				}
				throw new RuntimeException(e.getMessage());
			}

		} else {
			r.setUserPhoto(userPhoto);
		}

		// ici
		Review updatedReview = reviewService.updateReview(id, r);
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
	// getReviewsByProductId

	@GetMapping("/front/{id}")
	public ResponseEntity<Map<String, Object>> getAllReviewsByProductPagination(@PathVariable("id") long id,
			@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<Review> listReviews = new ArrayList<Review>();
		Page<Review> reviews = reviewService.findByProductId(pageNo, pageSize, sortBy, id);
		listReviews = reviews.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("reviews", listReviews);
		response.put("currentPage", reviews.getNumber());
		response.put("totalItems", reviews.getTotalElements());
		response.put("totalPages", reviews.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
