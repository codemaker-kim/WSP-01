package org.project.smumap.Tile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TileInfoRequest {
    private String tilePath;
    private String tileName;
}
