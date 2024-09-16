package com.example.blog.service;

import com.example.blog.model.BlogPost;
import com.example.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public BlogPost createPost(BlogPost blogPost) {
        blogPost.setCreatedAt(LocalDateTime.now());
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updatePost(Long id, BlogPost updatedBlogPost) {
        Optional<BlogPost> existingPost = blogPostRepository.findById(id);
        if (existingPost.isPresent()) {
            BlogPost post = existingPost.get();
            post.setTitle(updatedBlogPost.getTitle());
            post.setContent(updatedBlogPost.getContent());
            post.setUpdatedAt(LocalDateTime.now());
            return blogPostRepository.save(post);
        }
        return null;

    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }
}
