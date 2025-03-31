package com.example.payroll;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:8081") 
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistRepository repository;

    public ArtistController(ArtistRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return repository.findAll();
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return repository.save(artist);
    }

    @PutMapping("/api/artists/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist updatedArtist) {
        return repository.findById(id)
                .map(artist -> {
                    artist.setName(updatedArtist.getName());
                    return repository.save(artist);
                })
                .orElseGet(() -> {
                    updatedArtist.setId(id);
                    return repository.save(updatedArtist);
                });
    }

    @DeleteMapping("/api/artists/{id}")
    public void deleteArtist(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

