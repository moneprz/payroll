import axios from "axios";

const API_URL = "http://localhost:8080/api/songs";

export default {
  getPagedSongs(page, size) {
    return axios.get(`${API_URL}?page=${page}&size=${size}`);
  },
  getAllSongs() {
    return axios.get(`${API_URL}/all`);
  },
  createSong(song) {
    return axios.post(API_URL, song);
  },
  updateSong(id, song) {
    return axios.put(`${API_URL}/${id}`, song);
  },
  deleteSong(id) {
    return axios.delete(`${API_URL}/${id}`);
  },
  searchSongs(name, artist) {
    return axios.get(`${API_URL}/search?name=${name}&artist=${artist}`);
  },

};
