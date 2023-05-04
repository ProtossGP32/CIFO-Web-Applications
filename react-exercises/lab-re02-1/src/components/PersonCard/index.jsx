import { Card, Image } from "semantic-ui-react";

// Props are spreaded prior to pass them to the PersonCard component
const PersonCard = ({image, firstName, lastName, birthDate, role}) => {
    return (
        <>
            <Card style={{ margin: "10px" }}>
                <Image src={image} wrapped ui={false} />

                {/* Define the Card contents */}
                <Card.Content>
                    <Card.Header>
                        {lastName}, {firstName}
                    </Card.Header>
                    <Card.Meta>Birthdate: {birthDate}</Card.Meta>
                    {/* TODO: API doesn't return the role of the person, not included in the DB entry */}
                    <Card.Description>{firstName} is a {role}</Card.Description>
                </Card.Content>
            </Card>
        </>
    );
};

export default PersonCard;