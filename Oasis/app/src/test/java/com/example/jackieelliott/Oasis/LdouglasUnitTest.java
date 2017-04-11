package com.example.jackieelliott.Oasis;

import org.junit.Test;
import com.example.jackieelliott.Oasis.Model.User;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings({"FeatureEnvy", "JavaDoc"})
public class LdouglasUnitTest {
    /**
     * test method for sortReports located in QualityReports
     */
    @SuppressWarnings("OverlyLongMethod")
    @Test
    public final void testFindUserNames() {
        User user1 = new User("Username1", "Password1", "ID1");
        User user2 = new User("Username2", "Password2", "ID2");
        User user3 = new User("Username3", "Password3", "ID3");
        User user4 = new User("Username4", "Password4", "ID4");
        User user5 = new User("Username5", "Password5", "ID5");
        User user6 = new User("Username6", "Password6", "ID6");
        User user7 = new User("Username7", "Password7", "ID7");
        User user8 = new User("Username8", "Password8", "ID8");
        User user9 = new User("Username9", "Password9", "ID9");
        User user10 = new User("Username10", "Password10", "ID10");
        User userNull = new User(null, null, null);
        User defaultUser = new User();

        //Creates the lists of users to get the usernames out of
        List<User> emptyUserArrayList = new ArrayList<>();
        List<User> allUserArrayList = new ArrayList<>();
        List<User> nullUserArrayList = new ArrayList<>();
        List<User> defaultUserArrayList = new ArrayList<>();
        List<User> oneUserArrayList = new ArrayList<>();

        allUserArrayList.add(user1);
        allUserArrayList.add(user2);
        allUserArrayList.add(user3);
        allUserArrayList.add(user4);
        allUserArrayList.add(user5);
        allUserArrayList.add(user6);
        allUserArrayList.add(user7);
        allUserArrayList.add(user8);
        allUserArrayList.add(user9);
        allUserArrayList.add(user10);

        nullUserArrayList.add(user1);
        nullUserArrayList.add(user4);
        nullUserArrayList.add(userNull);
        nullUserArrayList.add(user6);

        defaultUserArrayList.add(user2);
        defaultUserArrayList.add(user5);
        defaultUserArrayList.add(defaultUser);
        defaultUserArrayList.add(user9);

        oneUserArrayList.add(user1);

        //String lists for comparing
        String[] emptyList = new String[0];
        String[] fullList = new String[]{"Username1", "Username2",
                "Username3", "Username4", "Username5",
                "Username6", "Username7", "Username8",
                "Username9", "Username10"};
        String[] nullList = new String[]{"Username1",
                "Username4", null, "Username6"};
        String[] defaultList = new String[]{"Username2",
                "Username5", "default", "Username9"};
        String[] oneList = new String[]{"Username1"};

        //actual testing
        assertArrayEquals("Passing an empty User list should return " +
                        "an empty array",
                emptyList,
                User.findUserNames(emptyUserArrayList));
        assertArrayEquals("Passing a full User list should return " +
                        "list of usernames of the same length", fullList,
                User.findUserNames(allUserArrayList));
        assertArrayEquals("Passing a User list with a null should " +
                        "return list of usernames of the same length with a null",
                nullList, User.findUserNames(nullUserArrayList));
        assertArrayEquals("Passing a default User list should return " +
                        "list of usernames of the same length with default",
                defaultList, User.findUserNames(defaultUserArrayList));
        assertArrayEquals("Passing a User list with one user should " +
                        "return list of usernames of the same length",
                oneList, User.findUserNames(oneUserArrayList));
    }
}

