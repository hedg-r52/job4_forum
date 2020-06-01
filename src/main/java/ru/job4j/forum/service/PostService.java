package ru.job4j.forum.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public void add(Post post) {
        posts.save(post);
    }

    public void update(Post post) {
        Optional<Post> optionalPost = posts.findById(post.getId());
        if (optionalPost.isPresent()) {
            Post p = optionalPost.get();
            BeanUtils.copyProperties(post, p, "id");
            posts.save(p);
        }
    }

    public Post get(long id) {
        return posts.findById(id).orElseThrow();
    }

    public List<Post> getAll() {
        List<Post> result = new ArrayList<>();
        posts.findAll().forEach(result::add);
        return result;
    }
}
