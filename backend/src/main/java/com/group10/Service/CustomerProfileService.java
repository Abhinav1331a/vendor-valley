package com.group10.Service;

import com.group10.Model.Booking;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerProfileService extends ProfileService
{
    @Override
    public List<Booking> getBookings(int userId) {
            return null;
    }

    @Override
    public boolean editProfile(int userId) throws SQLException
    {

        return false;
    }
}
