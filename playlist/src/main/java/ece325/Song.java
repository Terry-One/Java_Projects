package ece325;

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Song} class
 */
public class Song {
    // TODO: Assignment 6 -- complete this Song class to pass the tests
    private String Artist;
    private String Title;
    private double Length;

    public Song(String Artist, String Title, double Length)
    {
        this.Artist = Artist;
        this.Title = Title;
        this.Length = Length;
    }

    public String getArtist()
    {
        return Artist;
    }
    public String getTitle()
    {
        return Title;
    }
    public double getLength()
    {
        return Length;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Song))
			return false;

		Song song = (Song)obj;
		return getArtist().toLowerCase().equals(song.getArtist().toLowerCase()) &&
               getLength()==song.getLength() &&
               getTitle().toLowerCase().equals(song.getTitle().toLowerCase());
    }
    
    public boolean isArtist(String artist)
    {
        return artist.toLowerCase().equals(this.getArtist().toLowerCase());
    }

    public boolean isTitle(String title)
    {
        return title.toLowerCase().equals(this.getTitle().toLowerCase());
    }
    

}
