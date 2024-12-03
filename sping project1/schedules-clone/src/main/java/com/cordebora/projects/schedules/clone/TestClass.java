package com.cordebora.projects.schedules.clone;

import com.cordebora.projects.schedules.clone.demo.Roster;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import com.cordebora.projects.schedules.clone.demo.Roster;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


@SpringBootApplication
@RestController
public class TestClass {


 private Map<String, Roster> db = new HashMap<>()  {{
        put ("1", new Roster("Booby" , "Brink"));
    }};

    @GetMapping("/")
    public String home() {
        return "Application is running!";
    }

    @GetMapping("/roster")
    public Collection<Roster> get() {
        return db.values();
    }

    //GET: Localhost "/1" will return BB
    @GetMapping("/roster/{firstName}")
    public Roster get(@PathVariable String firstName) {
        Roster roster = db.get(firstName);
        if (roster == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return roster;
    }
    //Delete: WIll not return anythng but deleted file returns 200
    @DeleteMapping("/roster")
    public void  delete(@PathVariable String firstName) {
        Roster roster = db.remove(firstName);
        if (roster == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // POST:
    @PostMapping("/roster")
    public Roster create(@RequestPart("data") MultipartFile file) throws IOException {
        Roster roster = new Roster();
        roster.setFirstName(UUID.randomUUID().toString());
        roster.setFirstName(file.getOriginalFilename());
        roster.setData(file.getBytes());
        db.put(roster.getFirstName(), roster);
        return roster;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestClass.class, args);
    }
}
