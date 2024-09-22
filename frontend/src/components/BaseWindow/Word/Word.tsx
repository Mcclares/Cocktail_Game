import React, {useState} from "react";
import "./Word.css";
interface WordProps {
    guessedWord: string;
}

const Word: React.FC<WordProps> = ({guessedWord}) => {
    return (<div className="guessWord">
        <p>{guessedWord}</p>
    </div>)
}
export default Word;