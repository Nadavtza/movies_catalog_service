package movies.server.moviescatalogservice.resources;

import movies.server.moviescatalogservice.models.CatalogItem;
import movies.server.moviescatalogservice.models.Movie;
import movies.server.moviescatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MoviesCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<CatalogItem> getCatalog(@PathVariable String userId) {


        List<Rating> ratings = Arrays.asList(
                new Rating("34321", 3),
                new Rating("554321", 7),
                new Rating("621", 2)
        );


        return ratings
                .stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

//                    Movie movie = webClientBuilder
//                            .build()
//                            .get()
//                            .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                            .retrieve()
//                            .bodyToMono(Movie.class)
//                            .block();

                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
