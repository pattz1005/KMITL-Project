/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movielister;

import java.util.List;

/**
 *
 * @author alok
 */
public interface MovieFinder {
    public List findMoviesByName(String name);
    public List findMoviesByDirector(String name);
    public List findMoviesByReleaseDate(String name);
}
