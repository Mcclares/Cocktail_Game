import React from "react";
import "./NumberAttempts.css";

interface NumberAttemptsProps {
    attempts: number
}

const NumberAttempts: React.FC<NumberAttemptsProps> = ({attempts}) => {
    return (
        <div className="numberAttemptsText">
            <p>You have attempts: <b>{attempts}</b> </p>
        </div>
    )
}

export default NumberAttempts;