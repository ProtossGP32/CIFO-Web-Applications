import React from "react";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import './App.css';
import Layout from "./route/Layout.jsx";
import Home from "./pages/home/Home.jsx";
import Contact from "./pages/contact/Contact.jsx";
import NoPage from "./route/NoPage.jsx";
import ApiRestMock from "./pages/apirest-mock/ApiRestMock.jsx";
import ApiRestReal from "./pages/apirest-real/ApiRestReal";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="apirest-mock" element={<ApiRestMock />} />
          <Route path="apirest-real" element={<ApiRestReal />} />
          <Route path="contact" element={<Contact />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
