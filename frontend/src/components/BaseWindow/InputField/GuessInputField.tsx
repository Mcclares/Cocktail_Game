import React, {useState} from "react";
import "./GuessInputField.css";

interface GuessInputFieldProps {
    text: string;
    onTextChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const GuessInputField: React.FC<GuessInputFieldProps> = ({ text, onTextChange }) => {

    return (
        <div>
            <input className="input-classic"
                type="text"
                value={text}
                onChange={onTextChange}
                placeholder="Write guess cocktail">
            </input>
        </div>
    )
}
export default GuessInputField;