package com.fluidity.sbgraphql;

import java.util.List;
import com.fluidity.sbgraphql.article.Article;
import com.fluidity.sbgraphql.article.ArticleRepository;
import com.fluidity.sbgraphql.comment.Comment;
import com.fluidity.sbgraphql.comment.CommentRepository;
import com.fluidity.sbgraphql.profile.Profile;
import com.fluidity.sbgraphql.profile.ProfileRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findOne(id);
    }
}
