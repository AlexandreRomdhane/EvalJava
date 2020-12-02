package com.myaudiolibrary.web.service;

import com.myaudiolibrary.web.model.Artist;
import com.myaudiolibrary.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Page<Artist> findAllArtist(Integer page, Integer size, String sortProperty, String sortDirection) {
        if(page < 0 || size < 0){
            throw new IllegalArgumentException("Le numéro de page et la taille des pages ne peuvent pas être négatifs !");
        }
        Long nbPageMax = artistRepository.count() / size;
        if(page > nbPageMax){
            throw new IllegalArgumentException("Avec une taille de " + size + ", le numéro de page doit être compris entre 0 et " + nbPageMax);
        }
        try {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.fromString(sortDirection),sortProperty));
            Pageable pageable = PageRequest.of(page,size,sort);
            return artistRepository.findAll(pageable);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Erreur lors de la recherche paginée ! Vérifier les paramètres !");
        }
    }

    public Optional<Artist> findById(Long id) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        return optionalArtist;
    }

    public List<Artist> findByNameLike(String name) {
        List<Artist> optionalArtist = artistRepository.findByNameLike(name);
        return optionalArtist;
    }

    public Artist updateArtist(String name, Long id) {
        artistRepository.updateArtist(name, id);
        return null;
    }

    public Artist createArtist(Artist artist) {
        artist = artistRepository.save(artist);
        return artist;
    }
}