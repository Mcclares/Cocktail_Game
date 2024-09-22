import React from "react";
import "./RecordCounter.css";
interface RecordCounterProps {
    record: number;
}

const RecordCounter: React.FC<RecordCounterProps> = ({record}) => {
    return (
        <div className="recordText">
            <p><b>Record: {record}</b></p>
        </div>
    )
}
export default RecordCounter;