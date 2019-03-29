package com.fluidity.sbgraphql;

import com.fluidity.sbgraphql.article.Article;
import com.fluidity.sbgraphql.article.ArticleRepository;
import com.fluidity.sbgraphql.comment.Comment;
import com.fluidity.sbgraphql.comment.CommentRepository;
import com.fluidity.sbgraphql.profile.Profile;
import com.fluidity.sbgraphql.profile.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class DataProvider implements CommandLineRunner {
    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;
    private ProfileRepository profileRepository;


    @Override
    @Transactional
    public void run(String... strings) {
        Profile author = profileRepository.save(new Profile(null, "max", "nothing to say"));
        Profile admin = profileRepository.save(new Profile(null, "admin", "the administrator of nothing to say"));
        Article article1 = articleRepository.save(new Article(null, "Hello world", "This is a hello world", author.getId()));
        Article article2 = articleRepository.save(new Article(null, "Foo", "Bar", admin.getId()));
        commentRepository.save(new Comment(null, "Do you like this app?", article1.getId(), author.getId()));
        commentRepository.save(new Comment(null, "This is a great app", article1.getId(), admin.getId()));
        commentRepository.save(new Comment(null, "This is a comment", article2.getId(), admin.getId()));
    }
}
