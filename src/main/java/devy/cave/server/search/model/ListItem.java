package devy.cave.server.search.model;

public class ListItem {

    private String contentsId;
    private String posterUrl;
    private String title;
    private String titleEng;
    private String director;
    private String actor;
    private String year;
    private String nation;
    private String genre;

    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "contentsId='" + contentsId + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", title='" + title + '\'' +
                ", titleEng='" + titleEng + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", year='" + year + '\'' +
                ", nation='" + nation + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
