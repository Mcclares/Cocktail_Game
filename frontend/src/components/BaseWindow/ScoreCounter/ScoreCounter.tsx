import React from "react";
import "./ScoreCounter.css";
interface ScoreCounterProps {
    score: number;
}

const ScoreCounter: React.FC<ScoreCounterProps> = ({score}) => {
    return (
        <div className="scoreText">
            <p><b>Score: {score}</b></p>
        </div>
    )
}
export default ScoreCounter;