package com.ridango.game.service;

import com.ridango.game.dto.GameStateDTO;
import com.ridango.game.model.Cocktail;
import com.ridango.game.model.CocktailResponse;
import com.ridango.game.model.Result;
import com.ridango.game.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class CocktailService {

    private final RestTemplate restTemplate;
    private final ResultRepository resultRepository;
    private final Set<String> usedCocktails = new HashSet<>();

    @Autowired
    public CocktailService(RestTemplateBuilder restTemplateBuilder, ResultRepository gameResultRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.resultRepository = gameResultRepository;
    }

    public GameStateDTO getRandomCocktail() {
        String apiUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
        Cocktail cocktail;
        do {
            ResponseEntity<CocktailResponse> response = restTemplate.getForEntity(apiUrl, CocktailResponse.class);
            cocktail = response.getBody().getDrinks().get(0);


        } while (usedCocktails.contains(cocktail.getStrDrink()));


        return new GameStateDTO(
                "_ ".repeat(cocktail.getStrDrink().length()).trim(),
                cocktail.getStrDrink(),
                5,
                cocktail.getStrInstructions(),
                cocktail.getStrDrinkThumb(),
                cocktail.getStrIngredient1(),
                "",
                cocktail.getStrCategory(),
                0,
                0
        );
    }

    public void resetUsedCocktails() {
        usedCocktails.clear();
    }
    public Result getHighScore() {
        return resultRepository.findFirstByOrderByScoreDesc();
    }
    public void saveResult(Result result) {
        resultRepository.save(result);
    }
}