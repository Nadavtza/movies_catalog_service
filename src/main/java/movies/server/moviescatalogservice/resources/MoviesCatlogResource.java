package movies.server.moviescatalogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import movies.server.moviescatalogservice.models.CatalogItem;

@RestController
public class MoviesCatlogResource {

    public List<CatalogItem> getCatalog(String userId) {
        return Collections.singletonList(
            new CatalogItem("Transformers", "Test description", 4));
            
    }
}