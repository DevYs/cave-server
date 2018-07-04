package devy.kave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ChannelMapper;
import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.VideoMapper;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.ContentsKey;
import devy.kave.server.db.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ContentsService {

    private final Logger logger = LoggerFactory.getLogger(ContentsService.class);

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private VideoMapper videoMapper;

    public StoredSortedValueSet<Channel> channelList() {
        return channelMapper.sortedSet();
    }

    public List<Contents> contentsList(int pageNo, String searchWord, long channelNo) {
        List<Contents> contentsList = new ArrayList<>();

        Iterator iterator = null;
        if(0L != channelNo) {
            iterator = contentsMapper.sortedMapByChannelNo().duplicates(new Long(channelNo)).iterator();
        } else {
            iterator = contentsMapper.sortedSet().iterator();
        }

        while(iterator.hasNext()) {
            Contents contents = (Contents) iterator.next();
            int indexOf = contents.getContentsName().indexOf(searchWord) +
                        contents.getDirector().indexOf(searchWord) +
                        contents.getActor().indexOf(searchWord) +
                        contents.getNation().indexOf(searchWord) +
                        contents.getGenre().indexOf(searchWord);
            if(-5 < indexOf) {
                contentsList.add(contents);
            }
        }

        int s = ((pageNo - 1) * 10) + 1;
        int e = s + 9;

        if(contentsList.size() < s) {
            return new ArrayList<>();
        }

        if(contentsList.size() < e) {
            e = contentsList.size();
        }

        return contentsList.subList(s - 1, e);
    }

    public boolean add(Contents contents) {
        contents.setContentsNo(DatabaseKeyCreator.createKey());
        return contentsMapper.add(contents);
    }

    public Contents remove(long contentsNo) {
        return (Contents) contentsMapper.remove(contentsNo);
    }

    public Contents mod(Contents contents) {
        return (Contents) contentsMapper.mod(contents);
    }

    public Contents getContents(long contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

    public Iterator<Video> videoList(long contentsNo) {
        return videoMapper.sortedSetByContentsNo().duplicates(new Long(contentsNo)).iterator();
    }
}