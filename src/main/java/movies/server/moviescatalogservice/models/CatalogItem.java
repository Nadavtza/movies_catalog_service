package movies.server.moviescatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CatalogItem {
    public CatalogItem(String string, String string2, int i) {
	}
	private String name; 
    private String description; 
    private int rating; 
}
