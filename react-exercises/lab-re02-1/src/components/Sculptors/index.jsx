// useState example with arrays
import { useState } from "react";

// nextId is used to define the index of each artist. It's just a temporal variable, but it could be defined as an state
let nextId = 0;

export default function List() {
    const [name, setName] = useState('');
    const [artists, setArtists] = useState([]);

    return (
        <>
            <h2>Inspiring sculptors - useState with spread operator example</h2>
            <hr />
            {/* Initialize an input field that updates the "name" state */}
            <input
                value={name}
                onChange={e => setName(e.target.value)}
            />
            {/* Create a button that adds the "name" state as a new sculptor's entry */}
            <button onClick={() => {
                // Here we use the spread operator to update the list of available artists with the new entry
                setArtists([
                    ...artists,
                    { id: nextId++, name: name }
                ]);
            }}>Add sculptor</button>
            {/* Show the full list of artists as a bullet list*/}
            <ul>
                {artists.map(artist => (
                    <li key={artist.id}>{artist.name}</li>
                ))}
            </ul>
        </>
    );
}