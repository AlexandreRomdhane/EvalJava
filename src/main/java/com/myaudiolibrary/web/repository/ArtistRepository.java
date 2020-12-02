package com.myaudiolibrary.web.repository;

import com.myaudiolibrary.web.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = "SELECT * FROM artist WHERE artist.Name LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    List<Artist> findByNameLike(@Param("name")String name);

    // List<Artist> listArtists(int page, int size, String sortPagination, String sortDirection);

    Artist findByName(String name);

    @Query("Update Artist Set name = :name WHERE id = :id")
    Artist updateArtist(String name, Long id);

    Optional<Artist> findById(Long id);
}