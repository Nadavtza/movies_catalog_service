package movies.server.moviescatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import movies.server.moviescatalogservice.models.Movie;
import movies.server.moviescatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.server.moviescatalogservice.models.CatalogItem;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MoviesCatalogResource {

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        RestTemplate restTemplate = new RestTemplate();

        List<Rating> ratings =Arrays.asList(
                new Rating("34321", 3),
                new Rating("554321", 7),
                new Rating("621", 2)
                );

        return ratings
                .stream()
                .map(rating ->  {
                    Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
