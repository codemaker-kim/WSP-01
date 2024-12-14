package org.project.smumap.Tile.repository;

import org.project.smumap.domain.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TileRepository extends JpaRepository<Tile, Long> {
    Optional<Tile> findByTile(String tilePath);
    Optional<Tile> findByTileName(String tileName);

    @Query("SELECT t.tile FROM Tile t")
    Optional<List<String>> findAllTiles();
}
