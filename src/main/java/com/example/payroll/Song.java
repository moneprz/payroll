package com.example.payroll;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;



@Entity
class Song {

  private @Id
  @GeneratedValue Long id;
  @NotBlank(message = "Title cannot be empty")
  @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
  private String name;

  @NotBlank(message = "Artist cannot be empty")
  private String artist;

  @NotBlank(message = "Genre cannot be empty")
  private String genre;
  @NotNull(message = "Length is required")
  @Min(value = 1, message = "Length must be at least 1 second")
  private int length;


  

  Song() {}

  Song(String name, String artist, String genre, int length) {

    this.name = name;
    this.artist = artist;
    this.genre = genre;
    this.length = length;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getArtist() {
    return this.artist;
  }
  public String getGenre() {
    return this.genre;
  }
  public int getLength() {
    return this.length;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }
  public void setGenre(String genre) {
    this.genre = genre;
  }
  public void setLength(int length) {
    this.length = length;
  }



  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.artist, this.genre, this.length);
  }

  @Override
  public String toString() {
    return "Song{" + "id=" + this.id + ", name='" + this.name + '\'' + ", artist='" + this.artist + '\''  + ", genre='" + this.genre + ", length='" + this.length  + '}';
  }
}