import axios from "axios";

const MOVIE_API_BASE_URL = "http://localhost:8080";

class MovieService {
  saveMovie(movieUp) {
    return axios.post("http://localhost:8080", movieUp);
  }

  getMovies() {
    return axios.get("http://localhost:8080/movie/findAllMoviesByDate");
  }
  getMoviesLikes() {
    return axios.get(MOVIE_API_BASE_URL+"/movie/findAllMoviesByLikes");
  }
  getMoviesDislikes() {
    return axios.get(MOVIE_API_BASE_URL+"/movie/findAllMoviesByDislikes");
  }
  

  getUserMovies(username) {
    return axios.get(MOVIE_API_BASE_URL+"/movie/findAllMoviesByUser",username );
  }


  deleteMovie(id) {
    return axios.delete(MOVIE_API_BASE_URL + "/" + id);
  }

  getMovieById(id) {
    return axios.get(MOVIE_API_BASE_URL + "/" + id);
  }

  updateMovie(movie, id) {
    return axios.put(MOVIE_API_BASE_URL + "/" + id, movie);
  }
  saveUser(user) {
    return axios.get("http://localhost:8080/register",user);
  }
}

export default new MovieService();

