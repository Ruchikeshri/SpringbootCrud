package com.stackroute.service;

import com.stackroute.domain.Blog;
import com.stackroute.exception.BlogAlreadyExistsException;
import com.stackroute.exception.BlogNotFoundException;
import com.stackroute.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/* This is ServiceImplementation Class which should implement BlogService Interface and override all the implemented methods.
 * Handle suitable exceptions for all the implemented methods*/

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    @Override
    public Blog saveBlog(Blog blog) throws BlogAlreadyExistsException {
        if(blogRepository.existsById(blog.getBlogId())){
            throw new BlogAlreadyExistsException();
        }
        else{
            Blog savedBlog=blogRepository.save(blog);
            return savedBlog;}
    }

    @Override
    public List<Blog> getAllBlogs() {


        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(int id) throws BlogNotFoundException {
        Blog blog = null;
        Optional optional=blogRepository.findById(id);
        if(optional.isPresent()){
            blog=blogRepository.findById(id).get();
        }
        else throw new BlogNotFoundException();
        return blog;
    }

    @Override
    public Blog deleteBlog(int id) throws BlogNotFoundException {
        Blog blog = null;
        Optional optional = blogRepository.findById(id);
        if(optional.isPresent()) {
            blog = blogRepository.findById(id).get();
            blogRepository.deleteById(id);
            return blog;
        }
        else{
            throw new BlogNotFoundException();
        }
    }

    @Override
    public Blog updateBlog(Blog blog) throws BlogAlreadyExistsException, BlogNotFoundException {

        Blog updatedBlog = null;
        boolean bool=blogRepository.existsById(blog.getBlogId());
        if(bool){
            Blog actualBlog=getBlogById(blog.getBlogId());
            actualBlog.setBlogContent(blog.getBlogContent());
            updatedBlog=blogRepository.save(actualBlog);
        }
        else {
            throw new BlogNotFoundException();
        }
        return updatedBlog;
    }
}

