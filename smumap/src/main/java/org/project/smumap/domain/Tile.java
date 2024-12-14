package org.project.smumap.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "tile", nullable = false)
    private String tile;

    @Column(name = "tilename", nullable = false)
    private String tileName;

    @Builder
    public Tile(String tile, String tileName) {
        this.tile = tile;
        this.tileName = tileName;
    }

}