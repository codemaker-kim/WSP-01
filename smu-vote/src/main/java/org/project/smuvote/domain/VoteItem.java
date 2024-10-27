package org.project.smuvote.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteItem {
    private Long id;
    private Long voteId;
    private String title;
    private String item1;
    private String item2;
    private boolean check;
}
