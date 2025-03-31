<template>
    <div>
      <h2>Artists</h2>
      <input v-model="newArtist" placeholder="Add new artist" />
      <button @click="addArtist">Add</button>
  
      <ul>
        <li v-for="artist in artists" :key="artist.id">
          {{ artist.name }}
          <button @click="deleteArtist(artist.id)">Delete</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import artistService from "@/services/ArtistService";
  
  export default {
    data() {
      return {
        artists: [],
        newArtist: "",
      };
    },
    methods: {
      fetchArtists() {
        artistService.getAllArtists().then((response) => {
          this.artists = response.data;
        });
      },
      addArtist() {
        if (!this.newArtist) return;
        artistService.createArtist({ name: this.newArtist }).then(() => {
          this.fetchArtists();
          this.newArtist = "";
        });
      },
      deleteArtist(id) {
        artistService.deleteArtist(id).then(() => {
          this.fetchArtists();
        });
      },
    },
    mounted() {
      this.fetchArtists();
    },
  };
  </script>
  