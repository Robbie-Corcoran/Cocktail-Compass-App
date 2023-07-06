package com.example.cocktailcompass.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class CocktailRepository implements ICocktailRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public CocktailRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Cocktail> searchCocktails(String searchQuery) {
        String apiUrl = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + searchQuery;
        CocktailResponse response = restTemplate.getForObject(apiUrl, CocktailResponse.class);
        return response.getCocktails();
    }

    public List<Cocktail> randomCocktail() {
        String apiRandomUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        CocktailResponse response = restTemplate.getForObject(apiRandomUrl, CocktailResponse.class);
        return response.getCocktails();
    }
}
