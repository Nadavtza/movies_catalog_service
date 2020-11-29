package movies.server.moviescatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserCatalog {

    private List<CatalogItem> catalogItems;
}
