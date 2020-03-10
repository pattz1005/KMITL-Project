/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example2.movielister;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alok
 */
@Entity
@Table(name = "MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie_1.findAll", query = "SELECT m FROM Movie_1 m")
    , @NamedQuery(name = "Movie_1.findById", query = "SELECT m FROM Movie_1 m WHERE m.id = :id")
    , @NamedQuery(name = "Movie_1.findByMovieName", query = "SELECT m FROM Movie_1 m WHERE m.movieName = :movieName")
    , @NamedQuery(name = "Movie_1.findByDirector", query = "SELECT m FROM Movie_1 m WHERE m.director = :director")})
public class Movie_1 implements Serializable, Movie {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MOVIE_NAME")
    private String movieName;
    @Column(name = "DIRECTOR")
    private String director;

    public Movie_1() {
    }

    public Movie_1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie_1)) {
            return false;
        }
        Movie_1 other = (Movie_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(movieName==null){movieName = "unknown";}
        if(director==null){director = "unknown";}
        String r = "Name: ";
        r = r+this.movieName;
        r = r+"\nDirector: ";
        r = r+this.director;
        return r;
    }
    
}
