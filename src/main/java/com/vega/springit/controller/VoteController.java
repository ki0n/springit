package com.vega.springit.controller;

import com.vega.springit.domain.Link;
import com.vega.springit.domain.Vote;
import com.vega.springit.repository.LinkRepository;
import com.vega.springit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }

    // http://localhost:8080/vote/link/1/direction/-1/votecount/5
    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount){
        Optional<Link> optionalLink = linkRepository.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            Vote vote = new Vote(direction, link);
            voteRepository.save(vote);

            int updateVoteCount = voteCount + direction;
            link.setVoteCount(updateVoteCount);
            linkRepository.save(link);
            return updateVoteCount;
        }
        return voteCount;
    }
}
