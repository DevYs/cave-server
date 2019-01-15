package devy.cave.server.db.service;

import devy.cave.server.api.req.ApiReqSearch;
import devy.cave.server.db.mapper.ChannelMapper;
import devy.cave.server.db.mapper.ClipMapper;
import devy.cave.server.db.mapper.ContentsMapper;
import devy.cave.server.db.model.*;
import devy.cave.server.db.model.api.Movie;
import devy.cave.server.util.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private ClipMapper clipMapper;

    public List<Contents> searchContents(String searchWord, String channelNo) {
        List<Contents> contentsList = new ArrayList<>();

        Iterator iterator = null;
        if(!channelNo.equals("0")) {
            iterator = contentsMapper.sortedMapByChannelNo().duplicates(new ChannelKey(channelNo)).iterator();
        } else {
            iterator = contentsMapper.sortedSet().iterator();
        }

        while(iterator.hasNext()) {
            Contents contents = (Contents) iterator.next();
            int indexOf = contents.getContentsName().indexOf(searchWord) +
                    contents.getDirector().indexOf(searchWord) +
                    contents.getActor().indexOf(searchWord) +
                    contents.getNation().indexOf(searchWord) +
                    contents.getGenre().indexOf(searchWord) +
                    contents.getReleaseDate().indexOf(searchWord);
            if(-6 < indexOf) {
                contentsList.add(contents);
            }
        }

        return contentsList;
    }

    public List<Contents> contentsList(int pageNo, String searchWord, String channelNo) {
        int defaulatPagePerSize = 12;
        return contentsList(pageNo, searchWord, channelNo, defaulatPagePerSize);
    }

    public List<Contents> contentsList(int pageNo, String searchWord, String channelNo, int pagePerSize) {
//        List<Contents> contentsList = new ArrayList<>();
//
//        Iterator iterator = null;
//        if(!channelNo.equals("0")) {
//            iterator = contentsMapper.sortedMapByChannelNo().duplicates(new ChannelKey(channelNo)).iterator();
//        } else {
//            iterator = contentsMapper.sortedSet().iterator();
//        }
//
//        while(iterator.hasNext()) {
//            Contents contents = (Contents) iterator.next();
//            int indexOf = contents.getContentsName().indexOf(searchWord) +
//                    contents.getDirector().indexOf(searchWord) +
//                    contents.getActor().indexOf(searchWord) +
//                    contents.getNation().indexOf(searchWord) +
//                    contents.getGenre().indexOf(searchWord) +
//                    contents.getReleaseDate().indexOf(searchWord);
//            if(-6 < indexOf) {
//                contentsList.add(contents);
//            }
//        }

        List<Contents> contentsList = searchContents(searchWord, channelNo);

        // 내림차순으로 정렬
        contentsList.sort(new Sort().descending());

        int s = ((pageNo - 1) * pagePerSize) + 1;
        int e = s + (pagePerSize - 1);

        if(contentsList.size() < s) {
            return new ArrayList<>();
        }

        if(contentsList.size() < e) {
            e = contentsList.size();
        }

        return contentsList.subList(s - 1, e);
    }

    public List<Movie> movieList(ApiReqSearch apiReqSearch) {
        List<Movie> movieList = new ArrayList<>();

        List<Contents> contents = contentsList(apiReqSearch.getPageNo(), apiReqSearch.getSearchWord(), apiReqSearch.getChannelNo(), apiReqSearch.getPagePerSize());

        for(Contents c : contents) {
            Movie movie = new Movie();
            movie.setContents(c);

            Iterator iterator = clipMapper.map().duplicates(new ClipKey(c.getContentsNo())).iterator();
            if(iterator.hasNext()) {
                Clip clip = (Clip) iterator.next();
                movie.setClip(clip);
            }

            movieList.add(movie);
        }

        return movieList;
    }

    public Channel getChannel(String channelNo) {
        return (Channel) channelMapper.sortedMap().duplicates(new ChannelKey(channelNo)).iterator().next();
    }

}
