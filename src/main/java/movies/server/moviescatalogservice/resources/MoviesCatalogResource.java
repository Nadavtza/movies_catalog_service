package movies.server.moviescatalogservice.resources;

import movies.server.moviescatalogservice.models.CatalogItem;
import movies.server.moviescatalogservice.models.Movie;
import movies.server.moviescatalogservice.models.UserCatalog;
import movies.server.moviescatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MoviesCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserCatalog getCatalog(@PathVariable String userId) {

        UserRating userRating = restTemplate
                .getForObject("http://localhost:8083/ratingsdata/users/" + userId,
                        UserRating.class);

        return new UserCatalog(userRating.getRating()
                .stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList()));
    }
}


//    Movie movie = webClientBuilder
//            .build()
//            .get()
//            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//            .retrieve()
//            .bodyToMono(Movie.class)
//            .block();

