package movie.system.serviceimple;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.system.dto.MovieWithRatingDTO;
import movie.system.entity.Movie;
import movie.system.entity.Rating;
import movie.system.repository.MovieRepository;
import movie.system.repository.RatingRepository;
import movie.system.service.RecommendationService;

@Service
public class RecommendationServiceImple implements RecommendationService{

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    // Recommend top N movies sorted by average rating
//    public List<Movie> recommendTopMovies(int count) {
//        List<Movie> allMovies = movieRepository.findAll();
//
//        return allMovies.stream()
//                .sorted(Comparator.comparingDouble(this::calculateAverageRating).reversed())
//                .limit(count)
//                .collect(Collectors.toList());
//    }
    
    @Override
    public List<MovieWithRatingDTO> recommendTopMovies(int count) {
        List<Movie> allMovies = movieRepository.findAll();

        return allMovies.stream()
                .map(movie -> {
                    Set<Rating> ratings = movie.getRatings();
                    int totalRatings = (ratings != null) ? ratings.size() : 0;
                    double average = (ratings != null && !ratings.isEmpty())
                            ? ratings.stream().mapToDouble(Rating::getRating).average().orElse(0.0)
                            : 0.0;

                    String formattedRating = String.format("%.1f (%d)", average, totalRatings);

                    return new MovieWithRatingDTO(
                            movie.getMovieId(),
                            movie.getTitle(),
                            movie.getGenre(),
                            movie.getReleaseYear(),
                            movie.getDuration(),
                            movie.getDirector(),
                            movie.getDescription(),
                            formattedRating
                    );
                })
                .sorted(Comparator.comparingDouble((MovieWithRatingDTO dto) ->
                Double.parseDouble(dto.getRating().split(" ")[0]))
                .reversed())
                .limit(count)
                .collect(Collectors.toList());
    }
    
    public double calculateAverageRating(Movie movie) {
        Set<Rating> ratings = movie.getRatings();
        
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }

        return ratings.stream()
                      .mapToDouble(Rating::getRating)
                      .average()
                      .orElse(0.0);
    }

}
