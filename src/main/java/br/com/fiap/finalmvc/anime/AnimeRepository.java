package br.com.fiap.finalmvc.anime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long>{
    
}
