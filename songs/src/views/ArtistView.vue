<template>
    <div>
      <h1>ArtistList</h1>
      <ArtistList :songs="songs" />
    </div>
  </template>
  
  <script>
  import ArtistList from "@/components/ArtistList.vue";
  
  export default {
    name: "ArtistView",
    components: { ArtistList },
    data() {
      return {
        songs: [],
      };
    },
    created() {
      fetch("http://localhost:8080/api/artists")
        .then((response) => response.json())
        .then((data) => {
          this.songs = data;
        });
    }
  };
  </script>