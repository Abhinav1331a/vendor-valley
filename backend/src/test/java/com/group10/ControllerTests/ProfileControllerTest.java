package com.group10.ControllerTests;

import com.group10.Controller.ProfileController;
import com.group10.Exceptions.UserDoesntExistException;
import com.group10.Model.SignUpModel;
import com.group10.Service.ProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProfileControllerTest
{
    @InjectMocks
    private ProfileController profileController;
    @Mock
    private ProfileService customerProfileService;

    private SignUpModel user;
    private int user_id;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    private void initializeUser() {
        user = SignUpModel.builder().
                userId(543).
                firstName("Dev").
                lastName("Patel").
                mobile("9099929025").
                isVendor(0).
                street("111 Highpark").
                city("Toronto").
                province("Ontario").
                country("Canada").
                email("131eu@gmail.com").
                password("IDKTHEPASSWORD").
                userRole("manager").
                companyName("Dal").
                companyEmail("boon@dal.ca").
                companyRegistrationID("352523").
                companyMobile("9029895043").
                companyStreet("Clyde St").
                companyCity("Halifax").
                companyProvince("Nova Scotia").
                companyCountry("Canada").
                build();
    }

    @Test
    public void getProfile_Successful() throws SQLException, UserDoesntExistException
    {
        user_id = 5;
        initializeUser();
        when(customerProfileService.getProfile(user_id)).thenReturn(user);
        ResponseEntity<SignUpModel> res = ResponseEntity.ok(user);
        assertEquals(res, profileController.getProfile(user_id));
    }
    @Test
    public void getProfile_SQLException() throws SQLException, UserDoesntExistException
    {
        user_id = 5;
        initializeUser();
        when(customerProfileService.getProfile(user_id)).thenThrow(new SQLException("Problem while fetching data from database"));
        ResponseEntity<SignUpModel> res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        assertEquals(res,profileController.getProfile(user_id));
    }

    @Test
    public void getProfile_UserDoesntExistException() throws SQLException, UserDoesntExistException
    {
        user_id = 5;
        initializeUser();
        when(customerProfileService.getProfile(user_id)).thenThrow(new UserDoesntExistException("No such user is present"));
        ResponseEntity<SignUpModel> res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        assertEquals(res,profileController.getProfile(user_id));
    }
}
