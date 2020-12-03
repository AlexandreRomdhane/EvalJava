package com.myaudiolibrary.web.service;

import com.myaudiolibrary.web.model.Album;
import com.myaudiolibrary.web.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album createAlbum(Album album) {
        album = albumRepository.save(album);
        return album;
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(Math.toIntExact(id));
    }
}