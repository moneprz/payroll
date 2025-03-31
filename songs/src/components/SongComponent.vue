<template>
    <div class="song">
      <p>Title{{ song.title }}</p>
      <p>Artist: {{ song.artist }}</p>
      <p>Genre: {{ song.genre }}</p>
      <p>Length: {{ formatLength(song.length) }}</p>
    </div>
  </template>
  
  <script>
  export default {
    name: "SongComponent",
    props: {
      song: Object,
    },
    methods: {
      formatLength(seconds) {
        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = seconds % 60;
        return `${minutes}:${remainingSeconds.toString().padStart(2, "0")}`;
      },
    },
  };
  </script>
  
  <style scoped>
  .song {
    border: 1px solid #ccc;
    padding: 1em;
    margin: 1em;
  }
  </style>