package com.app.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class WriterService {

    private final CommentService commentService;

    public WriterService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostConstruct
    public void writeToFile() {
        try(PrintWriter pw = new PrintWriter(Files.newBufferedWriter(
                Paths.get("output.txt")))) {
            pw.println("id | body | postId | username | updatedAt");
            Arrays.stream(commentService.getComments())
                            .forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
