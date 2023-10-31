package br.com.fiap.finalmvc.anime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import br.com.fiap.finalmvc.user.User;
import br.com.fiap.finalmvc.user.UserService;

@Service
public class AnimeService {

    @Autowired
    AnimeRepository repository;

    @Autowired
    UserService userService;

    public List<Anime> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var anime = repository.findById(id);
        if (anime.isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }

    public void save(Anime anime) {
        repository.save(anime);
    }

    public void catchAnime(Long id, User user) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("tarefa não encontrada");

        var anime = opt.get();

        if ( anime.getUser() != null && anime.getUser().equals(user))
            throw new RuntimeException("você já selecionou essa tarefa");

        if (anime.getUser() != null)
            throw new RuntimeException("tarefa já atribuída");

        anime.setUser(user);
        repository.save(anime);

    }

    public void dropAnime(Long id, User user) {
        var opt = repository.findById(id);
        if (opt.isEmpty())
            throw new RuntimeException("tarefa não encontrada");

        var anime = opt.get();
        if (anime.getUser() == null)
            throw new RuntimeException("tarefa não atribuída");

        if (!anime.getUser().equals(user))
            throw new RuntimeException("você não pode largar uma tarefa de outra pessoa");

        anime.setUser(null);
        repository.save(anime);
    }
    
}