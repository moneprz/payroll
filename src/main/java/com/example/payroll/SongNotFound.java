package com.example.payroll;

class SongNotFound extends RuntimeException {
  SongNotFound(Long id) {
    super("Could not find song " + id);
  }
}
