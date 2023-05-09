import { Card, Image } from "semantic-ui-react";

// Props are spreaded prior to pass them to the PersonCard component
const PersonCard = ({image, firstName, lastName, birthDate, nationality, mail, phoneNumber, salary}) => {
    return (
        <>
            <Card style={{ margin: "10px" }}>
                <Image src={image} wrapped ui={false} />

                {/* Define the Card contents */}
                <Card.Content>
                    <Card.Header>
                        {lastName}, {firstName}
                    </Card.Header>
                    <Card.Meta>
                        <p>Birthdate: {birthDate}</p>
                        <p>Nationality: {nationality}</p>
                    </Card.Meta>
                    {/* TODO: API doesn't return the role of the person, not included in the DB entry */}
                    <Card.Description>
                        <p><b>Phone:</b> { phoneNumber || "Unknown" }</p>
                        { mail ? <p><b>Mail:</b> { mail }</p> :''}
                        { salary ? <p><b>Salary:</b> { salary }€</p> :''}
                    </Card.Description>
                </Card.Content>
            </Card>
        </>
    );
};

export default PersonCard;