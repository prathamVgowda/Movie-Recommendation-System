package movie.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.system.dto.RatingDTO;
import movie.system.entity.Rating;
import movie.system.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController 
{

	@Autowired
	private RatingService ratingservice;
	
	@PostMapping("/post")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        // your logic to create a rating
        Rating createdRating = ratingservice.createRating(rating);
        return new ResponseEntity<Rating>(createdRating, HttpStatus.CREATED);
    }
	
	@GetMapping("/get")
	public ResponseEntity< List<RatingDTO>> GetallRatings()
	{
		 List<RatingDTO> ratingDTOs= ratingservice.getAllRating();
		 return new ResponseEntity<List<RatingDTO>>(ratingDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/get/{ratingId}")
	public ResponseEntity<RatingDTO> getbyRatingId(@PathVariable Long ratingId) {
	    Rating rating = ratingservice.getByIdRating(ratingId);
	    RatingDTO dto = new RatingDTO(rating);
	    return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	
	@PutMapping("/update/{ratingId}")
	public ResponseEntity<Rating> updatebyRatingId(@RequestBody Rating rating, @PathVariable Long ratingId)
	{
		Rating rating2 =  ratingservice.updateByRating(ratingId, rating);
		return new ResponseEntity<Rating>(rating2, HttpStatus.OK);
	}
	
	@DeleteMapping("{ratingId}")
	public ResponseEntity<String> deletebyRatingId(Long ratingId)
	{
		 ratingservice.deletByRating(ratingId);
		 return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);
	}
}
