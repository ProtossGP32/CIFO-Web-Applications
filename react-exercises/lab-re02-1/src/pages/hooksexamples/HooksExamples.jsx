import React from 'react';
import Sculptors from '../../components/Sculptors';
import PersonForm from '../../components/PersonForm';
import People from '../../components/People';
import Timer from '../../components/Timer';

function HooksExamples() {


    return (
        <>
            <h1>Hooks examples</h1>
            <People />
            <Sculptors />
            <PersonForm />
            <Timer />
        </>
    )
}

export default HooksExamples;