import React from "react";


interface InstructionProps {
    description: string;
}

const Instruction: React.FC<InstructionProps> = ({description}) => {
    return (
        <div className="instruction">
            <h3>Description</h3>
            <p>{description}</p>
        </div>)
}
export default Instruction;