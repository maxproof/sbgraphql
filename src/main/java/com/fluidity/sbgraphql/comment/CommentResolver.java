package com.fluidity.sbgraphql.comment;

import com.fluidity.sbgraphql.profile.Profile;
import com.fluidity.sbgraphql.profile.ProfileRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentResolver implements GraphQLResolver<Comment> {
    private ProfileRepository profileRepository;

    public Profile getAuthor(Comment comment) {
        return profileRepository.findOne(comment.getAuthorId());
    }
}
