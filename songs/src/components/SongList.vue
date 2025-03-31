<template>
    <div>
      <input 
        v-model="searchQuery" 
        @input="searchSongs" 
        placeholder="Search by Title or Artist" 
        type="text" 
      />
      <button @click="showCreateForm">Add New Song</button>
      <button @click="fetchPagedSongs">Refresh</button>
      <div v-if="showForm">
        <h3>{{ isEditing ? "Edit Song" : "Create Song" }}</h3>
        <input v-model="currentSong.name" placeholder="Title" />
        
        <input v-model="currentSong.artist" placeholder="Artist" />
        <input v-model="currentSong.genre" placeholder="Genre" />
        <input v-model.number="currentSong.length" type="number" placeholder="Length (seconds)" />
        <select v-model="currentSong.artistId">
        <option v-for="artist in artists" :key="artist.id" :value="artist.id">
          {{ artist.name }}
        </option>
      </select>
        <button @click="saveSong">{{ isEditing ? "Update" : "Save" }}</button>
      </div>
      <div>
    
    </div>
      <ul v-if="songs.length > 0">
        <li v-for="song in songs" :key="song.id">
          Titel: {{ song.name }} - Artist:{{ song.artist }} Genre:{{ song.genre }}- Length:{{ song.length }}
          <button @click="editSong(song)">Edit</button>
          <button @click="deleteSong(song.id)">Delete</button>
        </li>
      </ul>
      <p v-else> No songs can be found. Please adjust your search.</p>
    </div>

    <div class="pagination">
      <button @click="firstPage" :disabled="page === 0">First</button>
      <button @click="prevPage" :disabled="page === 0">Previous</button>
      <span>Page {{ page + 1 }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="page === totalPages - 1">Next</button>
      <button @click="lastPage" :disabled="page === totalPages - 1">Last</button>
    </div>
  </template>
  
  <script>
  import songService from "@/services/SongService";
  import artistService from "@/services/ArtistService";
  
  export default {
    data() {
      return {
        songs: [],
        page: 0,
        size: 5,
        totalPages: 0,
        showForm: false,
        isEditing: false,
        searchQuery: "",
        currentSong: { name: "", artist: "", genre: "", length: 0 },
      };
    },
    methods: {
      async fetchPagedSongs() {
      try {
        const response = await songService.getPagedSongs(this.page, this.size);
        this.songs = response.data.content;
        this.totalPages = response.data.totalPages;
      } catch (error) {
        console.error("Error fetching songs:", error);
      }
    },
    firstPage() {
      this.page = 0;
      this.fetchPagedSongs();
    },
    prevPage() {
      if (this.page > 0) {
        this.page--;
        this.fetchPagedSongs();
      }
    },
    nextPage() {
      if (this.page < this.totalPages - 1) {
        this.page++;
        this.fetchPagedSongs();
      }
    },
    lastPage() {
      this.page = this.totalPages - 1;
      this.fetchPagedSongs();
    },
      fetchSongs() {
        songService.getAllSongs().then((response) => {
          this.songs = response.data;
        });
      },

      async searchSongs() {
        try {
          const name = this.searchQuery;
          const artist = this.searchQuery;

          const response = await songService.searchSongs(name, artist);
          this.songs = response.data;

          if (this.songs.length === 0) {
            console.log("Keine Songs gefunden");
          }
        } catch (error) {
          console.error("Fehler bei der Suche:", error);
        }
},
      fetchArtists() {
        artistService.getAllArtists().then((response) => {
          this.artists = response.data;
        });
      },
      showCreateForm() {
        this.showForm = true;
        this.isEditing = false;
        this.currentSong = { title: "", artist: "", genre: "", length: 0 };
      },
      saveSong() {
        if (this.isEditing) {
          songService.updateSong(this.currentSong.id, this.currentSong).then(() => {
            this.fetchSongs();
            this.showForm = false;
          });
        } else {
          songService.createSong(this.currentSong).then(() => {
            this.fetchSongs();
            this.showForm = false;
          });
        }
      },
      editSong(song) {
        this.showForm = true;
        this.isEditing = true;
        this.currentSong = { ...song };
      },
      deleteSong(id) {
        songService.deleteSong(id).then(() => {
          this.fetchSongs();
        });
      },
      
    },
    mounted() {
      this.fetchSongs();
      this.fetchPagedSongs();
    },
  };
  </script>
  
  