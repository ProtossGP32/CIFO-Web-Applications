import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import BookList from './BookList';
import PersonList from './PersonList';
import TriggerAPI from './TriggerAPI';
import "semantic-ui-css/semantic.min.css";
import ListPublications from './Components/publication/ListPublications';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
    <BookList />
    <PersonList />
    <TriggerAPI />
    <ListPublications />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
