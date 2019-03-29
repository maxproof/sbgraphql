package com.fluidity.sbgraphql.article;

import java.util.List;
import com.fluidity.sbgraphql.comment.Comment;
import com.fluidity.sbgraphql.comment.CommentRepository;
import com.fluidity.sbgraphql.profile.Profile;
import com.fluidity.sbgraphql.profile.ProfileRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleResolver implements GraphQLResolver<Article> {
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    public Profile getAuthor(Article article) {
        return profileRepository.findOne(article.getAuthorId());
    }

    public List<Comment> getComments(Article article) {
        return commentRepository.findByArticleId(article.getId());
    }
}
