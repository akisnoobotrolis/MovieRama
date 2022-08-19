import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import AddMovie from "./components/AddMovie";
import MovieList from "./components/MovieList";
import Navbar from "./components/Navbar";
import MovieListLikes from "./components/MovieListLikes";
import MovieListDislikes from "./components/MovieListDislikes";
import Register from "./components/Register";



function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route index element={<MovieList />} />
          <Route path="/" element={<MovieList />}></Route>
            <Route path="/register" element={<Register />} />
            <Route path="/movieList" element={<MovieList />} />
            <Route path="/movieListLikes" element={<MovieListLikes />} />
            <Route path="/movieListDislikes" element={<MovieListDislikes />} />
          <Route path="/addMovie" element={<AddMovie />} />

        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;