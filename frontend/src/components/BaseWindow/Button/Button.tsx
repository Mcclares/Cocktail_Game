import React, {useState} from "react";
import "./Button.css";

interface ButtonProps {
    title: string;
    handleClick: (event: React.MouseEvent<HTMLButtonElement>) => void;
    className: string;

}

const Button: React.FC<ButtonProps> = ({title,handleClick,className}) => {
    return <button className={className} onClick={handleClick}>{title}</button>
}

export default Button