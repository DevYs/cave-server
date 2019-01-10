package devy.cave.server.search.model.daum;

public class Page {

    private String size;
    private String no;
    private String groupRange;
    private String totalItemCount;
    private String totalItemIndex;
    private String last;
    private String totalCount;
    private String pageSize;
    private String calculatedSize;
    private String from;
    private String current;
    private String lastInCurrentGroup;
    private String currentGroup;
    private String firstInCurrentGroup;
    private String lastGroup;
    private String firstInNextGroup;
    private String firstInPrevGroup;
    private String lastInPrevGroup;
    private String fromMinus1;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getGroupRange() {
        return groupRange;
    }

    public void setGroupRange(String groupRange) {
        this.groupRange = groupRange;
    }

    public String getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(String totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public String getTotalItemIndex() {
        return totalItemIndex;
    }

    public void setTotalItemIndex(String totalItemIndex) {
        this.totalItemIndex = totalItemIndex;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCalculatedSize() {
        return calculatedSize;
    }

    public void setCalculatedSize(String calculatedSize) {
        this.calculatedSize = calculatedSize;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getLastInCurrentGroup() {
        return lastInCurrentGroup;
    }

    public void setLastInCurrentGroup(String lastInCurrentGroup) {
        this.lastInCurrentGroup = lastInCurrentGroup;
    }

    public String getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(String currentGroup) {
        this.currentGroup = currentGroup;
    }

    public String getFirstInCurrentGroup() {
        return firstInCurrentGroup;
    }

    public void setFirstInCurrentGroup(String firstInCurrentGroup) {
        this.firstInCurrentGroup = firstInCurrentGroup;
    }

    public String getLastGroup() {
        return lastGroup;
    }

    public void setLastGroup(String lastGroup) {
        this.lastGroup = lastGroup;
    }

    public String getFirstInNextGroup() {
        return firstInNextGroup;
    }

    public void setFirstInNextGroup(String firstInNextGroup) {
        this.firstInNextGroup = firstInNextGroup;
    }

    public String getFirstInPrevGroup() {
        return firstInPrevGroup;
    }

    public void setFirstInPrevGroup(String firstInPrevGroup) {
        this.firstInPrevGroup = firstInPrevGroup;
    }

    public String getLastInPrevGroup() {
        return lastInPrevGroup;
    }

    public void setLastInPrevGroup(String lastInPrevGroup) {
        this.lastInPrevGroup = lastInPrevGroup;
    }

    public String getFromMinus1() {
        return fromMinus1;
    }

    public void setFromMinus1(String fromMinus1) {
        this.fromMinus1 = fromMinus1;
    }

    @Override
    public String toString() {
        return "Page{" +
                "size='" + size + '\'' +
                ", no='" + no + '\'' +
                ", groupRange='" + groupRange + '\'' +
                ", totalItemCount='" + totalItemCount + '\'' +
                ", totalItemIndex='" + totalItemIndex + '\'' +
                ", last='" + last + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", calculatedSize='" + calculatedSize + '\'' +
                ", from='" + from + '\'' +
                ", current='" + current + '\'' +
                ", lastInCurrentGroup='" + lastInCurrentGroup + '\'' +
                ", currentGroup='" + currentGroup + '\'' +
                ", firstInCurrentGroup='" + firstInCurrentGroup + '\'' +
                ", lastGroup='" + lastGroup + '\'' +
                ", firstInNextGroup='" + firstInNextGroup + '\'' +
                ", firstInPrevGroup='" + firstInPrevGroup + '\'' +
                ", lastInPrevGroup='" + lastInPrevGroup + '\'' +
                ", fromMinus1='" + fromMinus1 + '\'' +
                '}';
    }
}
