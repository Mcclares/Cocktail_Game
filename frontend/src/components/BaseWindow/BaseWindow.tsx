import "./BaseWindow.css"
import Header from "./Header/Header";
import GuessInputField from "./InputField/GuessInputField";
import NumberAttempts from "./NumberAttempts/NumberAttempts";
import ScoreCounter from "./ScoreCounter/ScoreCounter";
import Button from "./Button/Button";
import React, {useEffect, useState} from "react";
import Instruction from "./Instruction/Instruction";
import RecordCounter from "./RecordCounter/RecordCounter";
import CocktailImage from "./CocktailImage/CocktailImage";
import Word from "./Word/Word";
import Hint from "./Hint/Hint";
import Category from "./Category/Category";

function BaseWindow() {
    const [attempts, setAttempts] = useState(0);
    const [score, setScore] = useState(0);
    const [record, setRecord] = useState(0);
    const [hint, setHint] = useState(0);

    // GuessWord
    const [guess, setGuess] = useState("");
    const handleTextChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setGuess(event.target.value);
    };

    const [cocktail, setCocktail] = useState({
        maskedWord: "",
        attemptsLeft: 5,
        instructions: "",
        imageAddress: "",
        ingredient: "",
        status: "",
        category: "",
        originalWord: '',
        score: 0,
        hintStep: 0

    });

    useEffect(() => {
      fetch("http://localhost:8080/api/cocktail/state")
          .then((response) => response.json())
          .then((data) => {
              setCocktail(data);
              setAttempts(data.attemptsLeft);
              setScore(data.score)
              setHint(data.hintStep)
          })
          .catch((error) => console.error("Error fetching game state:", error))

      updateRecord()

    }, []);


    const updateRecord = () => {
        // Fetch the high score on page load
        fetch("http://localhost:8080/api/cocktail/highScore")
            .then((response) => response.json())
            .then((data) => {
                setRecord(data.score); // Assuming 'score' is the field in the high score object
            })
            .catch((error) => console.error("Error fetching high score:", error));
    }

    const handleNewGame = () => {
        updateRecord();
        fetch("http://localhost:8080/api/cocktail/new", {
        })
            .then((response) => response.json())
            .then((data) => {
                setCocktail(data);
                setAttempts(data.attemptsLeft);
                setHint(data.hintStep);
                setScore(data.score)
            })
            .catch((error) => console.error("Error fetching new game:", error));

    };
    const handleSkip = () => {
        fetch("http://localhost:8080/api/cocktail/skip", {

        })
            .then((response) => response.json())
            .then((data) => {
                setCocktail(data);
                setAttempts(data.attemptsLeft);
                setHint(data.hintStep)
                if (data.status === "Game Over") {
                    alert("Game Over");
                    updateRecord();
                    handleNewGame();
                }

            })
            .catch((error) => console.error("Error fetching new game:", error));
    }

    const handleGuess = () => {
        fetch("http://localhost:8080/api/cocktail/guess", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
            },
            body: JSON.stringify({ word: guess })
        })
            .then((response) => response.json())
            .then((data) => {
                setCocktail(data);
                setAttempts(data.attemptsLeft)
                setHint(data.hintStep)
                setScore(data.score)
                if (data.status === "Game Over") {
                    alert("Game Over");
                    updateRecord()
                    handleNewGame()

                } else if (data.status === "Correct") {
                    alert("Correct!");
                    setScore(data.score)
                    setGuess("");
                }
            })
            .catch((error) => console.error("Error checking guess:", error));

    };

    const answerClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        handleGuess();
    }
    const skipClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        handleSkip();
    }


    const imageUrl = cocktail.imageAddress + '/preview';

    return (
        <div className="base">
            <Header title={"CocktailGame"}/>
            <GuessInputField text={guess} onTextChange={handleTextChange}/>
            <NumberAttempts attempts={attempts}/>
            <ScoreCounter score={score}/>
            <Button title="Answer" handleClick={answerClick} className="answerButton"/>
            <Button title="Skip" handleClick={skipClick} className="skipButton"/>
            {hint >= 3 && <CocktailImage source={imageUrl}/>}
            <Word guessedWord={cocktail.maskedWord}/>
            <RecordCounter record={record}/>
            <div className="hint-container">
                <Instruction description={cocktail.instructions}/>
                {hint >= 1 && <Hint ingredient={cocktail.ingredient}/>}
                {hint >= 2 && <Category category={cocktail.category}/>}
            </div>

        </div>
    )
}

export default BaseWindow;
