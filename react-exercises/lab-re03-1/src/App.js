import { BrowserRouter, Routes, Route } from "react-router-dom"
import Layout from "./route/Layout";
import Home from "./pages/home/Home.jsx";
import TodoFake from "./pages/todofake/TodoFake.jsx";
import Contact from "./pages/contact/Contact.jsx";
import NoPage from "./route/NoPage";

function App() {
  // View with return JSX
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="todofake" element={<TodoFake />} />
          <Route path="contact" element={<Contact />} />
          <Route path="*" element={<NoPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
