import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Grid } from 'semantic-ui-react';
import PersonCard from './Components/PersonCard';

const PersonList = () => {
    const [persons, setPersons] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    // useEffect hook ensures that the PersonList component stays connected to the SpringBoot API while displayed on the page
    useEffect(() => {
        // Define the fetchPersons function
        const fetchPersons = async() => {
            const response = await axios.get(
                'http://localhost:9090/api/librarymembers/all'
            )

            const personsData =response.data;
            setPersons(personsData);
            setIsLoading(false);
        };
        // useEffect invokes the fetchPersons function
        fetchPersons();
        // TODO: return a clean code function with cleanuup code that disconnects from the SpringBoot API

    }, []); // No dependencies required nor values from the component used inside of those functions

    // Render the component
    return (
        <>
            <h1>Persons - Grid with spread operator</h1>
            {/* Conditional rendering depending on the isLoading value */}
            { isLoading ? (
                <p>Loading...</p>
            ) : (
                <Grid
                    columns={4}
                    doubling
                    stackable
                    style={{ padding: "20px", backgroundcolor: "#F0F0F0" }}
                >
                    {/* Iterate the array of persons with the map function */}
                    {persons.map((person, index) => (
                        <Grid.Column key={index}>
                            <PersonCard {...person} />
                        </Grid.Column>

                    ))}
                </Grid>
            )
            }
        </>
    )
};

export default PersonList;