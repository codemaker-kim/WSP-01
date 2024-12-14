package org.project.smumap.Tile.service;

import lombok.RequiredArgsConstructor;
import org.project.smumap.Tile.dto.TileInfoRequest;
import org.project.smumap.Tile.repository.TileRepository;
import org.project.smumap.domain.Tile;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TileServiceImpl implements TileService {

    private final TileRepository tileRepository;

    @Override
    public Long save(TileInfoRequest dto) {

        Tile tile = Tile.builder()
                .tile(dto.getTilePath())
                .tileName(dto.getTileName())
                .build();

        return tileRepository.save(tile)
                .getId();
    }

    @Override
    public Tile findByTile(String tilePath) {
        return null;
    }

    @Override
    public List<String> findAllTiles() {
        return tileRepository.findAllTiles()
                .orElseThrow(()->new IllegalArgumentException("No tiles"));
    }

}
