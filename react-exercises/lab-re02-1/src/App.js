import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./route/Layout.jsx";
import Home from "./pages/home/Home.jsx";
import HooksExamples from "./pages/hooksexamples/HooksExamples.jsx"
import Contact from "./pages/contact/Contact.jsx";
import NoPage from "./route/NoPage.jsx";

function App() {
  // View with return JSX
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="hooksexamples" element={<HooksExamples />} />
          <Route path="contact" element={<Contact />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
