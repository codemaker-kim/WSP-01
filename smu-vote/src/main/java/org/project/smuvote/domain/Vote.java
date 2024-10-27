package org.project.smuvote.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {
    private Long id;
    private Long creatorId;
    private String title;
}
