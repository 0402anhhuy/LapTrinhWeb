package service.impl;

import dao.UserProfileDao;
import dao.impl.UserProfileDaoImpl;
import enity.UserProfile;
import service.UserProfileService;

public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileDao userProfileDao= new UserProfileDaoImpl();
    @Override
    public void createUserProfile(UserProfile userProfile) {
        userProfileDao.createUserProfile(userProfile);
    }
}
