package com.revature.tests;

import com.revature.mindlight.dao.UserDAO;
import com.revature.mindlight.models.Items;
import com.revature.mindlight.models.User;
import com.revature.mindlight.services.UserService;
import com.revature.mindlight.util.custom_exception.InvalidUserException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class UserServiceTest {
    Items item = new Items();
    User user = new User();
    UserService userservice = new UserService(new UserDAO());

    @Test
    public void testingIsNotDuplicateUsername() throws IOException {

        Assert.assertEquals(true, userservice.isNotDuplicateUsername("mbquercioli1"));
        Assert.assertThrows(InvalidUserException.class, () -> {userservice.isNotDuplicateUsername("mbquercioli"); });
    }
    @Test
    public void testingIsValidPassword() throws IOException {
        Assert.assertEquals(true, userservice.isValidPassword("@applerunner1"));
        Assert.assertThrows(InvalidUserException.class, () -> {userservice.isValidPassword("mbq"); });
    }
    @Test
    public void testinggettingUsername() {
        user.setUsername("markymarkk");
        Assert.assertEquals("markymarkk",user.getUsername() );
    }
    @Test
    public void testinggettingRole() {
        user.setRole("ADMIN");
        Assert.assertEquals("ADMIN",user.getRole() );
    }
    @Test
    public void testinggettingItemPrice() {
        item.setItemname("Cognitive");
        Assert.assertEquals("Cognitive", item.getItemname());
    }
}
