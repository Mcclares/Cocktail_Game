import React from "react";
import  "./CocktailImage.css";
interface CocktailImageProps {
    source: string;
}


const CocktailImage: React.FC<CocktailImageProps> = ({source}) => {
    return (
        <div>
            <img className="cocktailImage" src={source} alt="Cocktail"/>
        </div>)
}
export default CocktailImage;