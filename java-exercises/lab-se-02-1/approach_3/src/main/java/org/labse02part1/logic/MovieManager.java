package org.labse02part1.logic;

import org.labse02part1.domain.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieManager {
    private Map<String, Movie> movieMap;

    public MovieManager() {
        this.movieMap = new HashMap<>();
    }

    static boolean createMovie(Scanner reader) {
        return false;
    }

    static Movie readMovie(Scanner reader) {
        return null;
    }

    static boolean updateMovie(Scanner reader) {
        return false;
    }
    static boolean deleteMovie(Scanner reader) {
        return false;
    }

}
