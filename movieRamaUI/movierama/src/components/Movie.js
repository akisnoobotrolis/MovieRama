import React from "react";


const Movie = ({ movie }) => {
  
    
  

  return (
    <tr key={movie.title}>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{movie.title}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{movie.description}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap cursor-pointer">
        <div className="text-sm text-gray-500">{movie.username} </div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{movie.publicationDate}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{movie.numberOfLikes}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{movie.numberOfDislikes}</div>
      </td>
     
    </tr>
  );
};

export default Movie;