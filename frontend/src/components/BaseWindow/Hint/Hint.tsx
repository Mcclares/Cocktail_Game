
import React from "react";

interface HintProps {
    ingredient: string
}


const Hint: React.FC<HintProps> = ({ingredient}) => {
    return <div className="hint">
        <h3>Hints</h3>
        <p>One ingredient of cocktail: {ingredient}</p>
    </div>
}
export default Hint;