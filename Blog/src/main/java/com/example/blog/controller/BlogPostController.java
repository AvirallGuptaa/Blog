package com.example.blog.controller;

import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", blogPostService.getAllPosts());
        return "posts"; // Returns to posts.html view
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Optional<BlogPost> post = blogPostService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post"; 
        } else {
            return "redirect:/posts";
    }
    }

    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new BlogPost());
        return "create-post"; 
    }

    @PostMapping
    public String createPost(@ModelAttribute BlogPost blogPost) {
        blogPostService.createPost(blogPost);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model) {
        Optional<BlogPost> post = blogPostService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "edit-post"; 
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute BlogPost blogPost) {
        blogPostService.updatePost(id, blogPost);
        return "redirect:/posts";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        blogPostService.deletePost(id);
        return "redirect:/posts";
    }
}
