import React from "react";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import './App.css';
import Layout from "./route/Layout.jsx";
import Home from "./pages/home/Home.jsx";
import Contact from "./pages/contact/Contact.jsx";
import NoPage from "./route/NoPage.jsx";
import ApiRestDomains from './pages/apirest-domains/ApiRestDomains';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="apirest-domains" element={<ApiRestDomains />} />
          <Route path="contact" element={<Contact />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
