import React, { useState } from "react";
import axios from "axios";

function TriggerAPI() {
    const [responseData, setResponseData] = useState(null);

    const handleButtonClick = async () => {
        try {
            const response = await axios.get("http://localhost:9090/api/publications/all");
            setResponseData(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <button onClick={handleButtonClick}>Get Data</button>
            {responseData && (
                <div>
                    <h2>Response Data:</h2>
                    <pre>{JSON.stringify(responseData, null, 2)}</pre>
                </div>
            )}
        </div>
    );
}

export default TriggerAPI;