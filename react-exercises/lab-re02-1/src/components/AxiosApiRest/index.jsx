import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function AxiosApiRest() {
    // Declare the 'data' state variable
    const [data, setData] = useState([]);
    // Define the REST API URL to request data from
    const url = "https://jsonplaceholder.typicode.com/todos";

    // Define the 'useEffect' that will fetch the data
    useEffect(() => {
        // Configure the axios command to retrieve data from the API
        axios
            .get(url)
            // 'then' is executed only if data has been received from the GET request
            .then((response) => {
                // Assign the received data to the 'data' state variable -> This will trigger again the useEffect hook
                setData(response.data);
            })
            // If there's some kind of error on the GET request, catch it and print it as a console log
            .catch((error) => {
                console.log(error)
            });
    }, [setData]); // Set 'setData' as a dependency of the useEffect to trigger it

    // Return the rendered component
    return (
        <>
            <h2>My data todos from axios</h2>
            { /* Only render this part if data is not empty */}
            { data &&
                data.map((item) => {
                    return(
                        <spam key={item.id}>
                            id: {item.id}
                            <spam>userId:   {item.userId}</spam>
                            <spam>title:    {item.title}</spam>
                            <spam>completed:    {item.completed}</spam>
                            <br />
                        </spam>
                    );
                })
            }
        </>
    );
}