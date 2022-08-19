import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import MovieService from "../services/MovieService";

const AddMovie = () => {
  const [movieUp, setMovieUp] = useState({
    title: "",
    description: "",

  });

  const navigaye = useNavigate();

  const handleChange = (e) => {
    const value = e.target.value;
    setMovieUp({ ...movieUp, [e.target.name]: value });
  };

  const saveMovieUp = (e) => {
    e.preventDefault();
    MovieService.saveMovie(movieUp)
      .then((response) => {
        console.log(response);
        navigaye("/movieList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setMovieUp({
      title: "",
      description: "",
    
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Movie</h1>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Title
          </label>
          <input
            type="text"
            name="title"
            value={movieUp.title}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Description
          </label>
          <input
            type="text"
            name="description"
            value={movieUp.description}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"></input>
        </div>
       
       
       

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          <button
            onClick={saveMovieUp}
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6">
            Save
          </button>
          <button
            onClick={reset}
            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6">
            Clear
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddMovie;
