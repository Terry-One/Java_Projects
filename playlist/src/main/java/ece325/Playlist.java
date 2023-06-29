package ece325;

import java.util.*; 

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Playlist} class
 */
// @SuppressWarnings("serial")
public class Playlist<E extends Song> extends java.util.ArrayList<E> {
    java.util.Iterator<E> itr = this.iterator();       // Generic Iterator; Use it whenever you need it! 

    // TODO: Assignment 6 -- complete this Playlist class to pass the tests

    private int num_of_songs = 0;
    private int num_of_artist = 0;
    private int num_of_titles = 0;
    private double playtime = 0;
    private String title;

    public Playlist(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean addtoPlist(E song)
    {
        if (song != null)
        {
            if (this.contains(song))
            {
                return false;
            }
            else
            {
                if (!this.hasArtist(song.getArtist()))
                    num_of_artist +=1;
                if (!this.hasSongTitles(song.getTitle()))
                    num_of_titles +=1;
                this.add(song);
                num_of_songs+=1;
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public boolean removeFromPlist(E song)
    {
        if (song != null)
        {
            if (this.contains(song))
            {
                this.remove(song);
                num_of_songs-=1;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public E getSong(int k)
    {
        java.util.Iterator<E> itr = this.iterator(); 
        for(int i=0;i<k;i++) 
        {
            if (itr.hasNext())
                itr.next();
        }
        return itr.next();
    }
    public boolean hasTitle(String title)
    {
        return title.toLowerCase().equals(this.getTitle().toLowerCase());
    }

    public boolean hasArtist(String artist)
    {
        java.util.Iterator<E> itr = this.iterator(); 
        if (artist==null) 
        {
            while (itr.hasNext())
                if (itr.next()==null)
                    return true;
        } else {
            while (itr.hasNext())
                if (artist.toLowerCase().equals(itr.next().getArtist().toLowerCase()))
                    return true;
        }
        return false;
    }

    //implemented to get the numbero of titles
    public boolean hasSongTitles(String title)
    {
        java.util.Iterator<E> itr = this.iterator(); 
        if (title==null) 
        {
            while (itr.hasNext())
                if (itr.next()==null)
                    return true;
        } else {
            while (itr.hasNext())
                if (title.toLowerCase().equals(itr.next().getTitle().toLowerCase()))
                    return true;
        }
        return false;
    }

    public int numberOfSongs()
    {
        return num_of_songs;
    }
    
    public int numberOfArtists()
    {
        return num_of_artist;
    }

    public int numberOfTitles()
    {
        return num_of_titles;
    }

    public double playTime()
    {
        java.util.Iterator<E> itr = this.iterator();
        while (itr.hasNext())
            playtime+=itr.next().getLength();
        return playtime;
    }

    public int findSong(E song)
    {
        java.util.Iterator<E> itr = this.iterator(); 
        int i = 0;
        while (itr.hasNext())
        {
            if (itr.next().equals(song))
            {
                return i;
            }   
            else 
            {
                i+=1;
            }
        }
        return -1;
    }

    public void sortByArtist()
    {
        String artists;
        //create a arraylist Artist to store the name of the artist and sort them
        ArrayList<String> Artists = new ArrayList<String>(); 
        java.util.Iterator<E> it = this.iterator(); 
        while(it.hasNext())
        {
            Artists.add(it.next().getTitle());
        }
        Collections.sort(Artists);
        
        for (int i=0;i<Artists.size();i++)
        {
            //redo the iteration for every title
            java.util.Iterator<E> itr = this.iterator(); 
            while (itr.hasNext())
            {
                E song = itr.next();
                artists = song.getArtist();
                if(Artists.get(i) == artists)
                {
                    this.add(song);
                    this.remove(song);
                    break;
                }
            }
        }
    }

    public void sortByTitle()
    {
        String titles;
        //create a arraylist Titles to store the name of the titles and sort them
        ArrayList<String> Titles = new ArrayList<String>(); 
        java.util.Iterator<E> it = this.iterator(); 
        while(it.hasNext())
        {
            Titles.add(it.next().getTitle());
        }
        Collections.sort(Titles);
        
        for (int i=0;i<Titles.size();i++)
        {
            //redo the iteration for every title
            java.util.Iterator<E> itr = this.iterator(); 
            while (itr.hasNext())
            {
                E song = itr.next();
                titles = song.getTitle();
                if(Titles.get(i) == titles)
                {
                    this.add(song);
                    this.remove(song);
                    break;
                }
            }
        }
    }
}
