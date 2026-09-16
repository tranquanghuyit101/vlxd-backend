package org.backend.controller;

import org.backend.model.News;
import org.backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin("*")
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping
    public List<News> getAll() { return newsRepository.findAll(); }

    @GetMapping("/{id}")
    public News getOne(@PathVariable Long id) {
        return newsRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public News create(@RequestBody News news) { return newsRepository.save(news); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { newsRepository.deleteById(id); }
}
