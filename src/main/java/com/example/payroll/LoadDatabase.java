package com.example.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(SongRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Song("Paranoid", "Palaye Royale", "Rock", 240)));
      log.info("Preloading " + repository.save(new Song("Bohemian Rhapsody", "Queen", "Rock", 354)));
      log.info("Preloading " + repository.save(new Song("Shape of You", "Ed Sheeran", "Pop", 233)));
      log.info("Preloading " + repository.save(new Song("Blinding Lights", "The Weeknd", "Pop", 200)));
      log.info("Preloading " + repository.save(new Song("Hotel California", "Eagles", "Rock", 390)));
      log.info("Preloading " + repository.save(new Song("Smells Like Teen Spirit", "Nirvana", "Rock", 301)));
        log.info("Preloading " + repository.save(new Song("Lose Yourself", "Eminem", "Hip-Hop", 326)));
        log.info("Preloading " + repository.save(new Song("Billie Jean", "Michael Jackson", "Pop", 294)));
        log.info("Preloading " + repository.save(new Song("Wonderwall", "Oasis", "Rock", 258)));
        log.info("Preloading " + repository.save(new Song("Sweet Child O' Mine", "Guns N' Roses", "Rock", 356)));
        log.info("Preloading " + repository.save(new Song("Back in Black", "AC/DC", "Rock", 255)));
        log.info("Preloading " + repository.save(new Song("Humble", "Kendrick Lamar", "Hip-Hop", 177)));
        log.info("Preloading " + repository.save(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", "Pop", 269)));
        log.info("Preloading " + repository.save(new Song("Rolling in the Deep", "Adele", "Pop", 228)));
    };
  }
}
