package com.example.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SongRepository extends JpaRepository<Song, Long> {


   List<Song> findByNameContainingIgnoreCaseOrArtistContainingIgnoreCase(String name, String artist);
}