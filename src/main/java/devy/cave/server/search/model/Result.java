package devy.cave.server.search.model;

import java.util.List;

public class Result {

    private int page;
    private int sizePerPage;
    private int lastPage;
    private int totalCount;
    private List<ListItem> itemList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ListItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Result{" +
                "page=" + page +
                ", sizePerPage=" + sizePerPage +
                ", lastPage=" + lastPage +
                ", totalCount=" + totalCount +
                ", itemList=" + itemList +
                '}';
    }
}
