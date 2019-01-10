package devy.cave.server.search.model.daum;

public class Photo {

    private String photoId;
    private String title;
    private String photoCategory;
    private String adultFlag;
    private String description;
    private String filename;
    private String fullname;
    private String width;
    private String height;
    private String status;
    private String userid;
    private String daumid;
    private String daumname;
    private String tag;
    private String shootDate;
    private String shootEndDate;
    private String cp;
    private String recCnt;
    private String commentRefId;
    private String thumbnail;
    private String photoMappings;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoCategory() {
        return photoCategory;
    }

    public void setPhotoCategory(String photoCategory) {
        this.photoCategory = photoCategory;
    }

    public String getAdultFlag() {
        return adultFlag;
    }

    public void setAdultFlag(String adultFlag) {
        this.adultFlag = adultFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDaumid() {
        return daumid;
    }

    public void setDaumid(String daumid) {
        this.daumid = daumid;
    }

    public String getDaumname() {
        return daumname;
    }

    public void setDaumname(String daumname) {
        this.daumname = daumname;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShootDate() {
        return shootDate;
    }

    public void setShootDate(String shootDate) {
        this.shootDate = shootDate;
    }

    public String getShootEndDate() {
        return shootEndDate;
    }

    public void setShootEndDate(String shootEndDate) {
        this.shootEndDate = shootEndDate;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getRecCnt() {
        return recCnt;
    }

    public void setRecCnt(String recCnt) {
        this.recCnt = recCnt;
    }

    public String getCommentRefId() {
        return commentRefId;
    }

    public void setCommentRefId(String commentRefId) {
        this.commentRefId = commentRefId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPhotoMappings() {
        return photoMappings;
    }

    public void setPhotoMappings(String photoMappings) {
        this.photoMappings = photoMappings;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "photoId='" + photoId + '\'' +
                ", title='" + title + '\'' +
                ", photoCategory='" + photoCategory + '\'' +
                ", adultFlag='" + adultFlag + '\'' +
                ", description='" + description + '\'' +
                ", filename='" + filename + '\'' +
                ", fullname='" + fullname + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", status='" + status + '\'' +
                ", userid='" + userid + '\'' +
                ", daumid='" + daumid + '\'' +
                ", daumname='" + daumname + '\'' +
                ", tag='" + tag + '\'' +
                ", shootDate='" + shootDate + '\'' +
                ", shootEndDate='" + shootEndDate + '\'' +
                ", cp='" + cp + '\'' +
                ", recCnt='" + recCnt + '\'' +
                ", commentRefId='" + commentRefId + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", photoMappings='" + photoMappings + '\'' +
                '}';
    }
}
