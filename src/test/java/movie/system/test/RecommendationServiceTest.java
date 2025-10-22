package movie.system.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import movie.system.entity.Movie;
import movie.system.entity.Rating;
import movie.system.repository.MovieRepository;
import movie.system.repository.RatingRepository;
import movie.system.service.RecommendationService;
import movie.system.serviceimple.RecommendationServiceImple;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RecommendationServiceImple recommendationService;
    

	@BeforeEach
	void setUp() {
	    recommendationService = new RecommendationServiceImple();
	}
    
    @Test
    void testCalculateAverageRating_MultipleRatings() {
        Rating r1 = new Rating(); r1.setRating(4.0f);
        Rating r2 = new Rating(); r2.setRating(5.0f);
        Rating r3 = new Rating(); r3.setRating(3.0f);

        Movie movie = new Movie();
        movie.setRatings(Set.of(r1, r2, r3));

        double average = recommendationService.calculateAverageRating(movie);
        assertEquals(4.0, average, 0.001);
    }

    @Test
    void testCalculateAverageRating_SingleRating() {
        Rating r = new Rating(); r.setRating(4.5f);

        Movie movie = new Movie();
        movie.setRatings(Set.of(r));

        double average = recommendationService.calculateAverageRating(movie);
        assertEquals(4.5, average, 0.001);
    }

    @Test
    void testCalculateAverageRating_NoRatings() {
        Movie movie = new Movie();
        movie.setRatings(new HashSet<>());

        double average = recommendationService.calculateAverageRating(movie);
        assertEquals(0.0, average, 0.001);
    }

    @Test
    void testCalculateAverageRating_NullRatings() {
        Movie movie = new Movie();
        movie.setRatings(null);

        double average = recommendationService.calculateAverageRating(movie);
        assertEquals(0.0, average, 0.001);
    }

    @Test
    void testCalculateAverageRating_BoundaryValues() {
        Rating r1 = new Rating(); r1.setRating(0.0f);
        Rating r2 = new Rating(); r2.setRating(5.0f);

        Movie movie = new Movie();
        movie.setRatings(Set.of(r1, r2));

        double average = recommendationService.calculateAverageRating(movie);
        assertEquals(2.5, average, 0.001);
    }
    
    @AfterEach
    void cleanUp() {
        // Optional cleanup logic
    }

}

