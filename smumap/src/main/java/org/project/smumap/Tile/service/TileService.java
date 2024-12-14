package org.project.smumap.Tile.service;

import org.project.smumap.Tile.dto.TileInfoRequest;
import org.project.smumap.domain.Tile;

import java.util.List;

public interface TileService {

    // 이미지 저장
    Long save(TileInfoRequest request);

    // 이미지 불러오기
    Tile findByTile(String tilePath);

    // 이미지 전체 불러오기
    List<String> findAllTiles();
}
