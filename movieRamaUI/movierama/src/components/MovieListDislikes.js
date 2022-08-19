import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import MovieService from "../services/MovieService.js";
import Movie from "./Movie";

const MovieList = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [movies, setMovies] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      try {
        const response = await MovieService.getMovies();
        setMovies(response.data);
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };
    fetchData();
  }, []);

  

  return (
    <div className="container mx-auto my-8">
      <div className="h-12">
        <button
          onClick={() => navigate("/addMovie")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold">
          Add Movie
        </button>
        <button
          onClick={() => navigate("/authenticate")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold">
          Log in
        </button>
        <button
          onClick={() => navigate("/register")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold">
          Register
        </button>
      </div>
      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Title
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Description
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Username
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
              <div onClick={() => navigate("/movieList")}>Upload Date</div>
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
              <div onClick={() => navigate("/movieListLikes")}>Likes</div>
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Dislikes
              </th>
              
              <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Actions
              </th>
            </tr>
          </thead>
          {!loading && (
            <tbody className="bg-white">
              {movies.map((movie) => (
                <Movie
                  movie={movie}
                  key={movie.title}></Movie>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default MovieList;