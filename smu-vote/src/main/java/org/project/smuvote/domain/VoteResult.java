package org.project.smuvote.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteResult {
    private Long id;
    private Long voteItemId;
    private Long voterId;
    private boolean check;
}
