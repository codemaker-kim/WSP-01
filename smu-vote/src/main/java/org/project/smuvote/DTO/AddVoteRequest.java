package org.project.smuvote.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddVoteRequest {
    private String title;
    private Long creatorId;
}
