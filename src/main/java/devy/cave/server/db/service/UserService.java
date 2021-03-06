package devy.cave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.cave.server.db.DatabaseKeyCreator;
import devy.cave.server.db.DatabaseSource;
import devy.cave.server.db.mapper.UserMapper;
import devy.cave.server.db.model.User;
import devy.cave.server.db.model.UserKey;
import devy.cave.server.util.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private DatabaseSource databaseSource;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();

        User user = User.emptyUser();
        try {
            user = getUserByUserId(username);
        } catch(Exception e) {
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(""));
            logger.info("'" + username + "' is not User");
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new UserInfo(user, authorities);
    }

    public User isValidPasswordAndUser(String userId, String inputPassword) {
        User user = getUserByUserId(userId);
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(inputPassword, user.getPassword()) ? user : null;
    }

    public User getUserByUserId(String userId) {
        return (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
    }

    public boolean add(User user) {
        user.setUserNo(DatabaseKeyCreator.createKey());
        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        return userMapper.add(user);
    }

    public User mod(User user) {
        if(!user.getPassword().isEmpty()) {
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
        } else {
            User storedUser = (User) userMapper.map().duplicates(new UserKey(user.getUserNo())).iterator().next();
            user.setPassword(storedUser.getPassword());
        }
        return (User) userMapper.mod(user);
    }

    public User remove(String userNo) {
        return (User) userMapper.remove(new UserKey(userNo));
    }

    public StoredSortedValueSet<User> userList() {
        return userMapper.sortedSet();
    }

    public List<User> userList(int pageNo, String searchWord) {

        List<User> userList = new ArrayList<>();
        Iterator iterator = userMapper.sortedSet().iterator();
        while(iterator.hasNext()) {
            User user = (User) iterator.next();

            boolean r = (-1 < user.getUserId().indexOf(searchWord)) ||
                    (-1 < user.getUserName().indexOf(searchWord)) ||
                    (-1 < user.getEmail().indexOf(searchWord));

            if(r) {
                userList.add(user);
            }
        }

        // 내림차순 정렬
        userList.sort(new Sort().descending());

        int pagePerSize = 20;
        int s = ((pageNo - 1) * pagePerSize) + 1;
        int e = s + (pagePerSize - 1);

        if(userList.size() < s) {
            return new ArrayList<>();
        }

        if(userList.size() < e) {
            e = userList.size();
        }

        return userList.subList(s - 1, e);
    }

    public User getUser(String userNo) {
        return (User) userMapper.map().duplicates(new UserKey(userNo)).iterator().next();
    }

    private class UserInfo extends org.springframework.security.core.userdetails.User {

        public UserInfo(User user, Collection<? extends GrantedAuthority> authorities) {
            super(user.getUserId(), user.getPassword(), authorities);
        }

        public UserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

    }
}
