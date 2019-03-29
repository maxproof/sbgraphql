package com.fluidity.sbgraphql;

import com.fluidity.sbgraphql.article.Article;
import com.fluidity.sbgraphql.article.ArticleNotFoundException;
import com.fluidity.sbgraphql.article.ArticleRepository;
import com.fluidity.sbgraphql.article.CreateArticleInput;
import com.fluidity.sbgraphql.article.UpdateArticleInput;
import com.fluidity.sbgraphql.comment.Comment;
import com.fluidity.sbgraphql.comment.CommentRepository;
import com.fluidity.sbgraphql.comment.CreateCommentInput;
import com.fluidity.sbgraphql.profile.CreateProfileInput;
import com.fluidity.sbgraphql.profile.Profile;
import com.fluidity.sbgraphql.profile.ProfileNotFoundException;
import com.fluidity.sbgraphql.profile.ProfileRepository;
import com.fluidity.sbgraphql.profile.UpdateProfileInput;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    @Transactional
    public Article createArticle(CreateArticleInput input) {
        return articleRepository.saveAndFlush(new Article(null, input.getTitle(), input.getText(), input.getAuthorId()));
    }

    @Transactional
    public Article updateArticle(UpdateArticleInput input) {
        Article article = articleRepository
            .findById(input.getId())
            .orElseThrow(() -> new ArticleNotFoundException(input.getId()));
        article.setText(input.getText());
        article.setTitle(input.getTitle());
        return article;
    }

    @Transactional
    public int deleteArticle(Long id) {
        return articleRepository.deleteById(id);
    }

    @Transactional
    public Profile createProfile(CreateProfileInput input) {
        return profileRepository.saveAndFlush(new Profile(null, input.getUsername(), input.getBio()));
    }

    @Transactional
    public Profile updateProfile(UpdateProfileInput input) {
        Profile profile = profileRepository
            .findById(input.getId())
            .orElseThrow(() -> new ProfileNotFoundException(input.getId()));
        profile.setBio(input.getBio());
        return profile;
    }

    @Transactional
    public int deleteProfile(Long id) {
        return profileRepository.deleteById(id);
    }

    @Transactional
    public Comment createComment(CreateCommentInput input) {
        return commentRepository.saveAndFlush(new Comment(null, input.getText(), input.getArticleId(), input.getAuthorId()));
    }

    @Transactional
    public int deleteComment(Long id) {
        return commentRepository.deleteById(id);
    }
}
