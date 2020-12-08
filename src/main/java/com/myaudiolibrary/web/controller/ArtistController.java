package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional <Artist> findById(@PathVariable(value = "id") Long id){
        Optional <Artist> findId = artistService.findById(id);

        return findId;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, params={"name"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Artist> findByName(@RequestParam String name) {
        List<Artist> optionalArtist = artistService.findByNameLike(name);
        return optionalArtist;
    }

    // Affichage par page
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Artist> listArtist(@RequestParam Integer page,
                                   @RequestParam Integer size,
                                   @RequestParam String sortProperty,
                                   @RequestParam String sortDirection) {
        return artistService.findAllArtist(page, size, sortProperty, sortDirection);
    }

    // Ajout d'artist
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist CreateArtist(@RequestBody Artist artist) {
        artist = artistService.createArtist(artist);
        return artist;
    }


    // Suppression d'artist
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
    }



    // MAJ Artist
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist UpdateArtist(@RequestBody Artist artist) {
        Artist updateArtist = artistService.updateArtist(artist);
        return updateArtist;
    }

}