package movies.server.moviescatalogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movies.server.moviescatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MoviesCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        return Collections.singletonList(
                new CatalogItem("Transformers", "Test description", 4));
    }
}
