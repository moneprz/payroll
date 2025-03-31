package com.example.payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin(origins = "http://localhost:8081") 



class SongController {

  private final SongRepository repository;

  SongController(SongRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/api/songs")
    public Page<Song> getPagedSongs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

  
  @GetMapping("/api/songs/all")
  List<Song> all() {
    return repository.findAll();
  }


  @PostMapping("/api/songs")
  Song newSong(@RequestBody Song newSong) {
    return repository.save(newSong);
  }




 
  @PutMapping("/api/songs/{id}")
  Song updateSong(@RequestBody Song newSong, @PathVariable Long id) {
    return repository.findById(id)
      .map(song -> {
        song.setName(newSong.getName());
        song.setArtist(newSong.getArtist());
        song.setGenre(newSong.getGenre());
        song.setLength(newSong.getLength());
        return repository.save(song);
      })
      .orElseGet(() -> {
        newSong.setId(id);
        return repository.save(newSong);
      });
  }

  
  @DeleteMapping("/api/songs/{id}")
  void deleteSong(@PathVariable Long id) {
    repository.deleteById(id);
  }
  @GetMapping("/api/songs/search")
    public List<Song> searchSongs(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String artist) {

  
        if ((name == null || name.isEmpty()) && (artist == null || artist.isEmpty())) {
            return repository.findAll();
        }

      
        return repository.findByNameContainingIgnoreCaseOrArtistContainingIgnoreCase(
                name != null ? name : "", 
                artist != null ? artist : ""
        );
    }



    
}
