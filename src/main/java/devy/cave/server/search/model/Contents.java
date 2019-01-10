package devy.cave.server.search.model;

import java.util.List;

public class Contents {

    private String posterUrl;
    private String title;
    private String titleEng;
    private String genre;
    private String nation;
    private String releaseDate;
    private String runningTime;
    private String age;
    private String story;
    private String imageUrl;
    private float emphGrade;
    private List<Person> people;
    private ClipVideo clipVideo;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getEmphGrade() {
        return emphGrade;
    }

    public void setEmphGrade(float emphGrade) {
        this.emphGrade = emphGrade;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public ClipVideo getClipVideo() {
        return clipVideo;
    }

    public void setClipVideo(ClipVideo clipVideo) {
        this.clipVideo = clipVideo;
    }

    @Override
    public String toString() {
        return "Contents{" +
                "posterUrl='" + posterUrl + '\'' +
                ", title='" + title + '\'' +
                ", titleEng='" + titleEng + '\'' +
                ", genre='" + genre + '\'' +
                ", nation='" + nation + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", age='" + age + '\'' +
                ", story='" + story + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", emphGrade=" + emphGrade +
                ", people=" + people +
                ", clipVideo=" + clipVideo +
                '}';
    }
}
