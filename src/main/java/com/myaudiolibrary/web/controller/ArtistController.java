package com.myaudiolibrary.web.controller;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    /*

    // Supression Artist
    @RequestMapping(value = "", method = RequestMethod.DELETE, params={"id"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist DeleteArtist(@RequestParam Long id) {
        Artist deleteArtist = artistService.save();
        return deleteArtist;
    }




    // MAJ Artist
    @RequestMapping(value = "", method = RequestMethod.PUT, params={"id", "name"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist UpdateArtist(@RequestParam Long id,
                               @RequestParam String name) {
        Artist updateArtist = artistService.updateArtist(name, id);
        return updateArtist;
    }

/*
    @RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.save(artist);
    }
*/
}