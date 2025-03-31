import axios from "axios";

const API_URL = "http://localhost:8080/api/artists";

export default {
  getAllArtists() {
    return axios.get(API_URL);
  },
  createArtist(artist) {
    return axios.post(API_URL, artist);
  },
  updateArtist(id, artist) {
    return axios.put(`${API_URL}/${id}`, artist);
  },
  deleteArtist(id) {
    return axios.delete(`${API_URL}/${id}`);
  }
};
