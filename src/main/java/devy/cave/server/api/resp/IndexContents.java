package devy.cave.server.api.resp;

import devy.cave.server.db.model.Deck;
import devy.cave.server.db.model.Watching;
import devy.cave.server.db.model.api.Movie;

import java.util.List;

public class IndexContents {

    private List<Movie> newMovieList;
    private List<Watching> watchingList;
    private List<Deck> deckList;

    public List<Movie> getNewMovieList() {
        return newMovieList;
    }

    public void setNewMovieList(List<Movie> newMovieList) {
        this.newMovieList = newMovieList;
    }

    public List<Watching> getWatchingList() {
        return watchingList;
    }

    public void setWatchingList(List<Watching> watchingList) {
        this.watchingList = watchingList;
    }

    public List<Deck> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<Deck> deckList) {
        this.deckList = deckList;
    }

    @Override
    public String toString() {
        return "IndexContents{" +
                "newMovieList=" + newMovieList +
                ", watchingList=" + watchingList +
                ", deckList=" + deckList +
                '}';
    }
}
