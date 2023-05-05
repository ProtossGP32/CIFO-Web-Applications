/*
    Timer example: combination of useRef, useReducer, and useEffect
*/
import { useRef, useReducer, useEffect } from "react";

// Define an initialState of the timer
const initialTimerState = {
    isRunning: false,
    time: 0
}

// Define the reducer function that manages the features of the timer
function reducer(state, action) {
    switch (action.type) {
        case "start":
            // Change the 'isRunning' value to true
            // --> This will trigger the useEffect and set an interval task to update the time each second
            return {
                ...state,
                isRunning: true
            };
        case "stop":
            // Change the 'isRunning' value to false
            // --> This will trigger the useEffect again and clear the previously defined interval
            return {
                ...state,
                isRunning: false
            };
        case "reset":
            // Returns the initial state of the timer
            // --> This will trigger the useEffect if the isRunning state changes; else, it won't need
            return initialTimerState;
        case "tick":
            // Returns the updated state.time value
            // --> This WON'T trigger the useEffect as the isRunning value won't change
            return {
                ...state,
                time: state.time + 1
            };
        default:
            // Throw an error for any unexpected value
            throw new Error();
    }
}

// Define the Timer main function
export default function Timer() {
    // First off, initialize a timer state and its reducer function
    // --> The reducer will be the previously defined function that will manage the behaviour of the Timer
    // --> We also provide the desired initialTimerState
    const [state, dispatch] = useReducer(reducer, initialTimerState);
    // Then, define a reference value (idRef) using the `useRef` hook to handle the time value update
    const idRef = useRef(0);

    // Define the useEffect hook that updates the lifecycle of the timer
    useEffect(() => {
        // if 'state.isRunning' changes to false, there's no need to do anything. Reasons:
        // --> The cleanup code from the "start" business logic already clears the "setInterval"
        // --> The return here only serves to avoid executing the rest of the code
        if (!state.isRunning) {
            //
            return;
        };

        // Set the idRef current value to the initialization of a new interval
        // --> This interval is the responsible of calling the useReducer hook with the 'tick' action type
        console.log("Starting Timer");
        idRef.current = setInterval(
            () => dispatch(
                    {
                        type: "tick"
                    }), 1000); // Set the interval value to 1 sec
        
        // Define the CLEANUP code that runs on re-rendering when dependencies have changed
        // --> This function is stored in memory and when executed it uses the old props and state
        return () => {
            console.log("Stopping Timer");
            console.log(idRef.current);
            clearInterval(idRef.current);
            idRef.current = 0;
            console.log(idRef.current);
        }

    }, [state.isRunning]); // useEffect will only run once on component creation/mount and each time the value of 'state.isRunning' changes

    // Return the HTML snippet
      // https://www.w3schools.com/colors/colors_shades.asp
  return (
    <div style={{ backgroundColor: "#D0D0D0", padding: "12px" }}>
      <h2>Timer - combination of useEffect (with cleanup code), useReducer and useState hooks </h2>
      <div
        style={{
          backgroundColor: "#F0F0F0",
          boxShadow: "0 4px 20px 0 rgba(0,0,0,0.8)",
          transition: "0.3s",
          width: "40%",
          borderRadius: "5px",
          padding: "12px",
          textAlign: "center",
          fontSize: "20px"
        }}
      >
        {/* 'state.time' is the only state value that changes without user intervention and will trigger a re-render of this component by itself */}
        {state.time}s
      </div>
      <br />
      <div style={{ display: "flex" }}>
        <button
          style={{ margin: "8px" }}
          onClick={() => dispatch({ type: "start" })}
        >
          Start
        </button>
        <button
          style={{ margin: "8px" }}
          onClick={() => dispatch({ type: "stop" })}
        >
          Stop
        </button>
        <button
          style={{ margin: "8px" }}
          onClick={() => dispatch({ type: "reset" })}
        >
          Reset
        </button>
      </div>
    </div>
  );
}
    
