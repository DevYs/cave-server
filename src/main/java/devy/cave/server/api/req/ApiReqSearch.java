package devy.cave.server.api.req;

public class ApiReqSearch extends ApiRequest {

    private int pageNo;
    private int pagePerSize;
    private String searchWord;
    private String channelNo;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPagePerSize() {
        return pagePerSize;
    }

    public void setPagePerSize(int pagePerSize) {
        this.pagePerSize = pagePerSize;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    @Override
    public String toString() {
        return "ApiReqSearch{" +
                "pageNo=" + pageNo +
                ", pagePerSize=" + pagePerSize +
                ", searchWord='" + searchWord + '\'' +
                ", channelNo='" + channelNo + '\'' +
                '}';
    }
}
