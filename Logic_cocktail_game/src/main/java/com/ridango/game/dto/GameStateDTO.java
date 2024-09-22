package com.ridango.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

enum Hints{ingredient,category,image}


public class GameStateDTO {

    @JsonProperty("maskedWord")
    private String maskedWord;

    @JsonProperty("attemptsLeft")
    private int attemptsLeft;

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("imageAddress")
    private String imageAddress;

    @JsonProperty("ingredient")
    private String ingredient;

//    postavi jsonIhonre
    @JsonProperty
    private String originalWord;
    @JsonProperty
    private String status;
    @JsonProperty
    private String category;
    @JsonProperty
    private int score;
    @JsonProperty
    private int hintStep;


    public GameStateDTO(String maskedWord, String originalWord, int attemptsLeft, String instructions, String imageAddress, String ingredient, String status, String category, int score, int hintStep ) {
        this.maskedWord = maskedWord;
        this.originalWord = originalWord;
        this.attemptsLeft = attemptsLeft;
        this.instructions = instructions;
        this.imageAddress = imageAddress;
        this.ingredient = ingredient;
        this.status = status;
        this.category = category;
        this.score = score;
        this.hintStep = 0;
    }
    public void setHintStep(int hintStep) {
        this.hintStep = hintStep;
    }
    public int getHintStep() {
        return hintStep;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getOriginalWord() {
        return originalWord;
    }


    public String getMaskedWord() {
        return maskedWord;
    }

    public void setMaskedWord(String maskedWord) {
        this.maskedWord = maskedWord;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
    public String getIngredient() {
        return ingredient;
    }

}