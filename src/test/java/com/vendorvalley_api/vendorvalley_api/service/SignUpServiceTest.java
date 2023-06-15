package com.vendorvalley_api.vendorvalley_api.service;

import com.vendorvalley_api.vendorvalley_api.model.SignUpModel;
import com.vendorvalley_api.vendorvalley_api.repository.SignUpRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class SignUpServiceTest {

    @MockBean
    SignUpRepository signUpRepository;


    @Test
    public void signUpVendorTest() {
        SignUpModel signUpModel = SignUpModel.builder().userId(666).
                userRole("role").
                companyName("Google").
                companyEmail("boon@g.com").
                companyRegistrationID("g7x3L").
                companyMobile("9029895043").
                companyStreet("Clyde St").
                companyCity("Halifax").
                companyProvince("NS").
                companyCountry("CA").build();

        given(signUpRepository.saveVendor(signUpModel)).willReturn(true);

        assertTrue(signUpRepository.saveVendor(signUpModel));
    }

}
