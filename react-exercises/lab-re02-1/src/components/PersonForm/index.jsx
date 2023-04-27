import { useReducer } from "react";
// First, define an initial state to work it, although it is optional
const initialPerson = { name: "John", surname: "Smith", age: 25};

// Then define the reducer function
function reducer (state, action) {
  // Apply the changes in your state based on the provided application
  // Usually, the decision making is coded as a switch-cas or if-else conditional
  switch (action.type) {
    case "increment_age": {
      // Always return a new state (JSON object)
      return {
          name: state.name,
          surname: state.surname,
          age: state.age + 1
      };
    }
    case "decrement_age": {
      // Always return a new state (JSON object)
      return {
          name: state.name,
          surname: state.surname,
          age: state.age - 1
      };
    }
    case "change_name": {
      // Always return a new state (JSON object)
      // New values are included in the "action" parameter
      return {
          name: action.nextName,
          surname: state.surname,
          age: state.age
      };
    }
    case "change_surname": {
      // Always return a new state (JSON object)
      // New values are included in the "action" parameter
      return {
          name: state.name,
          surname: action.nextSurname,
          age: state.age
      };
    }
    default: {
      throw Error("Unknown action: " + action.type)
    }
  }
};

// Now define the PersonForm function that will return the render component
export default function PersonForm() {
  // Initialize the useReducer hook
  const [state, dispatch] = useReducer(reducer, initialPerson);

  // Define every "action" function that the reducer will use
  // - Function that increments the age of the person (initialPerson.age)
  function handleButtonClickIncrement() {
    // Invoke the dispatch function with a JSON object that contains the required params --> This will be the "action"
    dispatch({
      type: "increment_age"
    });
  };

  // - Function that decrements the age of the person (initialPerson.age)
  function handleButtonClickDecrement() {
    // Invoke the dispatch function with a JSON object that contains the required params --> This will be the "action"
    dispatch({
      type: "decrement_age"
    });
  };

  // - Function that updates the Name in the form based on the provided value
  function handleInputChangeName(e) {
    // Invoke the dispatch function with a JSON object that contains the required params --> This will be the "action"
    dispatch({
      type: "change_name",
      nextName: e.target.value
    });
  };
  // - Function that updates the Surname in the form based on the provided value
  function handleInputChangeSurname(e) {
    // Invoke the dispatch function with a JSON object that contains the required params --> This will be the "action"
    dispatch({
      type: "change_surname",
      nextSurname: e.target.value
    });
  };

  return (
    // Start with a fragment (empty tag)
    <>
      <h2>Person Form - useReduce example</h2>
      <hr />
      <input value={state.name} onChange={handleInputChangeName}/>
      <input value={state.surname} onChange={handleInputChangeSurname}/>
      <button onClick={handleButtonClickIncrement}>Increment age</button>
      <button onClick={handleButtonClickDecrement}>Decrement age</button>
      <p><strong>Name: </strong>{state.name}</p>
      <p><strong>Surname: </strong>{state.surname}</p>
      <p><strong>Age: </strong>{state.age}</p>
    </>
  );
}