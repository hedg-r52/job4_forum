package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(Post.of("Продаю машину ладу 01."));
    }

    public void add(Post post) {
        post.setId(maxId() + 1);
        posts.add(post);
    }

    public void update(Post post) {
        posts.set(getIndexById(post.getId()), post);
    }

    public Post get(int id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Post> getAll() {
        return posts;
    }

    private int getIndexById(int id) {
        int result = -1;
        for (Post p : posts) {
            if (p.getId() == id) {
                result = id;
                break;
            }
        }
        return result;
    }

    private Integer maxId() {
        return posts.stream()
                .map(Post::getId)
                .max(Integer::compare)
                .orElse(0);
    }
}
