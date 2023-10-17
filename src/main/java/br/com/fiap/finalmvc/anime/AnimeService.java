package br.com.fiap.finalmvc.anime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    @Autowired
    AnimeRepository repository;

    public List<Anime> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var anime = repository.findById(id);
        if(anime.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public void save(Anime anime) {
        repository.save(anime);
    }
    
}