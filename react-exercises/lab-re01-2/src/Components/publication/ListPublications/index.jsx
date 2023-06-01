import React, { useState, useEffect } from "react";
import axios from 'axios';
import { Grid } from 'semantic-ui-react';
import PublicationCard from "../PublicationCard";

const ListPublications = () => {

    const [ publications, setPublications ] = useState([]);
    const [ isLoading, setIsLoading ] = useState(true);

    // useEffect hook ensures that the PersonList component stays connected to the SpringBoot API while displayed on the page
    useEffect(() => {
        // define the fetchPublications function
        const fetchPublications = async() => {
            const response = await axios.get('http://localhost:9090/api/publications/all')
            setPublications(response.data);
            setIsLoading(false);
        };
        // useEffect invokes the fetchPublications function
        fetchPublications();
        // TODO: return a clean code function with cleanup code that disconnects from the SpringBoot API
    }, []);

    // Render the component
    return (
        <>
            <h1>Publications - Grid without spread operator</h1>
            {/** Conditional rendering depending on the isLoading value */}
            { isLoading ? (
                <p>Loading...</p>
            ) : (
                <Grid 
                    columns={4}
                    doubling
                    stackable
                    style={{ padding: "20px", backgroundcolor: "#F0F0F0" }}
                >
                    {/** Iterate the array of publications with the map function */}
                    { publications.map((publication, index) => (
                        <Grid.Column key={index}>
                            <PublicationCard
                                publication={publication}
                            />
                        </Grid.Column>
                    ))}
                </Grid>
            )}
        </>
    );
};

export default ListPublications;