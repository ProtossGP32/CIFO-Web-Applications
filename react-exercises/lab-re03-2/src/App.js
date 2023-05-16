import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Layout from "./route/Layout";
import Home from "./pages/home/Home.jsx";
import HighchartsExamples from "./pages/highcharts-examples/HighchartsExamples.jsx"
import Contact from "./pages/contact/Contact.jsx";
import NoPage from "./route/NoPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="highcharts-examples" element={<HighchartsExamples />} />
          <Route path="contact" element={<Contact />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
