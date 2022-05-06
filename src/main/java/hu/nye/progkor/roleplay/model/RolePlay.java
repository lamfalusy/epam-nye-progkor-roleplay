package hu.nye.progkor.roleplay.model;

import java.util.Objects;

public class RolePlay {

  private Long id;
  private String name;
  private Genre genre;
  private Complexity complexity;

  public RolePlay() {
  }

  public RolePlay(final Long id,
                  final String name,
                  final Genre genre,
                  final Complexity complexity) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.complexity = complexity;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(final Genre genre) {
    this.genre = genre;
  }

  public Complexity getComplexity() {
    return complexity;
  }

  public void setComplexity(final Complexity complexity) {
    this.complexity = complexity;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RolePlay)) {
      return false;
    }
    final RolePlay rolePlay = (RolePlay) o;
    return Objects.equals(id, rolePlay.id)
            && Objects.equals(name, rolePlay.name)
            && genre == rolePlay.genre
            && complexity == rolePlay.complexity;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, genre, complexity);
  }

  @Override
  public String toString() {
    return "RolePlay{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", genre=" + genre
            + ", complexity=" + complexity
            + '}';
  }
}
