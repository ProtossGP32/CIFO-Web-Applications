import React, { useState, useEffect } from "react";
import { Card, Image } from "semantic-ui-react";


// Props are spreaded prior to pass them to the PersonCard component
const PublicationCard = (props) => {
    const [ type, setType ] = useState("Unknown");

    const publication = props.publication;
    console.log(publication);

    // Author is another object, treat it as so or React 
    const author = publication.author;

    useEffect(() => {
        function setPublicationType() {
            // Set the type of publication based on available fields
            if (publication.pages) {
                setType("Book");
            } else if (publication.numberOfTracks) {
                setType("CD");
            } else if (publication.duration) {
                setType("DVD");
            };
        };
        setPublicationType();
    }, );

    return (
        <>
            <Card style={{ margin: "10px" }}>
                <Image src={publication.image} wrapped ui={false} />

                {/* Define the Card contents */}
                <Card.Content>
                    <Card.Header>
                        {publication.title}
                    </Card.Header>
                    <Card.Meta>Type: {type}</Card.Meta>
                    <Card.Meta>Author: {author.firstName + " " + author.lastName}</Card.Meta>
                    <Card.Meta>Publication date: {publication.publicationDate}</Card.Meta>
                    <Card.Meta>Status: {publication.status}</Card.Meta>
                    <Card.Description>{}</Card.Description>
                </Card.Content>
            </Card>
        </>
    );
};

export default PublicationCard;