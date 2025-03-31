<template>
    <div>
      <h1>Song List</h1>
      <SongList :songs="songs" />
    </div>
  </template>
  
  <script>
  import SongList from "@/components/SongList.vue";
  
  export default {
    name: "HomeView",
    components: { SongList },
    data() {
      return {
        songs: [],
      };
    },
    created() {
      fetch("http://localhost:8080/api/songs")
        .then((response) => response.json())
        .then((data) => {
          this.songs = data;
        });
    }
  };
  </script>