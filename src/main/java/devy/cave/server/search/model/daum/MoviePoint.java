package devy.cave.server.search.model.daum;

public class MoviePoint {
    
    private String movieId;
    private String expectPointAvg;
    private String expectPointCnt;
    private String expectPointTot;
    private String inspectPointAvg;
    private String inspectPointCnt;
    private String inspectPointTot;
    private String expertPointAvg;
    private String expertPointCnt;
    private String expertPointTot;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getExpectPointAvg() {
        return expectPointAvg;
    }

    public void setExpectPointAvg(String expectPointAvg) {
        this.expectPointAvg = expectPointAvg;
    }

    public String getExpectPointCnt() {
        return expectPointCnt;
    }

    public void setExpectPointCnt(String expectPointCnt) {
        this.expectPointCnt = expectPointCnt;
    }

    public String getExpectPointTot() {
        return expectPointTot;
    }

    public void setExpectPointTot(String expectPointTot) {
        this.expectPointTot = expectPointTot;
    }

    public String getInspectPointAvg() {
        return inspectPointAvg;
    }

    public void setInspectPointAvg(String inspectPointAvg) {
        this.inspectPointAvg = inspectPointAvg;
    }

    public String getInspectPointCnt() {
        return inspectPointCnt;
    }

    public void setInspectPointCnt(String inspectPointCnt) {
        this.inspectPointCnt = inspectPointCnt;
    }

    public String getInspectPointTot() {
        return inspectPointTot;
    }

    public void setInspectPointTot(String inspectPointTot) {
        this.inspectPointTot = inspectPointTot;
    }

    public String getExpertPointAvg() {
        return expertPointAvg;
    }

    public void setExpertPointAvg(String expertPointAvg) {
        this.expertPointAvg = expertPointAvg;
    }

    public String getExpertPointCnt() {
        return expertPointCnt;
    }

    public void setExpertPointCnt(String expertPointCnt) {
        this.expertPointCnt = expertPointCnt;
    }

    public String getExpertPointTot() {
        return expertPointTot;
    }

    public void setExpertPointTot(String expertPointTot) {
        this.expertPointTot = expertPointTot;
    }

    @Override
    public String toString() {
        return "MoviePoint{" +
                "movieId='" + movieId + '\'' +
                ", expectPointAvg='" + expectPointAvg + '\'' +
                ", expectPointCnt='" + expectPointCnt + '\'' +
                ", expectPointTot='" + expectPointTot + '\'' +
                ", inspectPointAvg='" + inspectPointAvg + '\'' +
                ", inspectPointCnt='" + inspectPointCnt + '\'' +
                ", inspectPointTot='" + inspectPointTot + '\'' +
                ", expertPointAvg='" + expertPointAvg + '\'' +
                ", expertPointCnt='" + expertPointCnt + '\'' +
                ", expertPointTot='" + expertPointTot + '\'' +
                '}';
    }
}
