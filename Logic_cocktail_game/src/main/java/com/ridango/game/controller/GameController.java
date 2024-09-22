package com.ridango.game.controller;

import com.ridango.game.dto.GameStateDTO;
import com.ridango.game.dto.GuessDTO;
import com.ridango.game.model.Result;
import com.ridango.game.repository.ResultRepository;
import com.ridango.game.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum Hints{ingredient,category,image}

@RestController
@RequestMapping("/api/cocktail")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {

    private final CocktailService cocktailService;
    private final ResultRepository resultRepository;
    private GameStateDTO currentGame;
    private int attemptsLeft = 5;
    private int currentScore = 0;

    @Autowired
    public GameController(CocktailService cocktailService, ResultRepository resultRepository) {
        this.cocktailService = cocktailService;
        this.resultRepository = resultRepository;
        this.startNewGame();
    }
    @GetMapping("/state")
    public ResponseEntity<GameStateDTO> getGameState() {
        return ResponseEntity.ok(currentGame);
    }

    @PostMapping("/guess")
    public ResponseEntity<GameStateDTO> checkGuess (@RequestBody GuessDTO guessDTO) {
        if(guessDTO.getWord().equalsIgnoreCase(currentGame.getOriginalWord())) {
            currentScore += attemptsLeft;
            currentGame.setScore(currentScore);
            startNewRound();
            return ResponseEntity.ok(currentGame);
        } else {
            return skipRound();
        }
    }

    @GetMapping("/skip")
    public ResponseEntity<GameStateDTO> skipRound() {
        attemptsLeft--;
        if(attemptsLeft == 0) {
            saveResult(currentScore);
            currentScore = 0;
            currentGame.setStatus("Game Over");
            cocktailService.resetUsedCocktails();
            return ResponseEntity.ok(currentGame);
        } else {
            String updatedMaskedWord = revealRandomLetters(currentGame.getMaskedWord(), currentGame.getOriginalWord());
            currentGame.setMaskedWord(updatedMaskedWord);
            currentGame.setAttemptsLeft(attemptsLeft);
            currentGame.setStatus("Incorrect");

            int hintStep = currentGame.getHintStep();
            switch (hintStep) {
                case 0:
                    currentGame.setHintStep(Hints.ingredient.ordinal());
                    break;
                case 1:
                    currentGame.setHintStep(Hints.category.ordinal());
                    break;
                case 2:
                    currentGame.setHintStep(Hints.image.ordinal());
                    break;
                default:
                    currentGame.setHintStep(0); //
                    break;
            }
            currentGame.setHintStep(hintStep + 1); // Увеличиваем шаг подсказки

            return ResponseEntity.ok(currentGame);
        }
    }

    @GetMapping("/new")
    public ResponseEntity<GameStateDTO> startNewGame() {
        cocktailService.resetUsedCocktails();

        GameStateDTO cocktail = cocktailService.getRandomCocktail();
        this.currentGame = new GameStateDTO(
                cocktail.getMaskedWord(),
                cocktail.getOriginalWord(),
                5,
                cocktail.getInstructions(),
                cocktail.getImageAddress(),
                cocktail.getIngredient(),
                "New Game",
                cocktail.getCategory(),
                0,
                0
        );
        this.attemptsLeft = 5;
        this.currentScore = 0;
        return ResponseEntity.ok(currentGame);
    }

    @GetMapping("/highScore")
    public ResponseEntity<Result> getHighScore(){
        Result highScore = cocktailService.getHighScore();
        return new ResponseEntity<>(highScore, HttpStatus.OK);
    }

    //Other methods
    private void saveResult(int score) {
        Result result = new Result(score);
        resultRepository.save(result);
    }

    private String revealRandomLetters(String maskedWord, String originalWord) {
        char[] masked = maskedWord.replace(" ", "").toCharArray();
        char[] original = originalWord.toCharArray();

        List<Integer> hiddenIndices = new ArrayList<>();
        for (int i = 0; i < masked.length; i++) {
            if (masked[i] == '_') {
                hiddenIndices.add(i);
            }
        }
        if (hiddenIndices.isEmpty()) {
            return maskedWord;
        }

        Random random = new Random();
        int randomIndex = hiddenIndices.get(random.nextInt(hiddenIndices.size()));
        char letterToReveal = original[randomIndex];
        for (int i = 0; i < masked.length; i++) {
            if (original[i] == letterToReveal) {
                masked[i] = letterToReveal;
            }
        }
        return String.valueOf(masked).replaceAll(".", "$0 ");
    }
    private void startNewRound() {
        GameStateDTO cocktail = cocktailService.getRandomCocktail();
        this.currentGame = new GameStateDTO(
                cocktail.getMaskedWord(),
                cocktail.getOriginalWord(),
                5,
                cocktail.getInstructions(),
                cocktail.getImageAddress(),
                cocktail.getIngredient(),
                "Correct",
                cocktail.getCategory(),
                this.currentScore, // Сохраняем текущие очки
                0
        );
        this.attemptsLeft = 5;
    }
}