/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movielister;

/**
 *
 * @author alok
 */
public interface Lister {
    public void setMovieFinder(MovieFinder movieFinder);
    public void listMovieByName(String name);
    public void listMoviesByDirector(String name);
    public void listMoviesByReleaseDate(String name);
}
