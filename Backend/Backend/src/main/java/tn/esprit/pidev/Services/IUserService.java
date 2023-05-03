package tn.esprit.pidev.Services;


import tn.esprit.pidev.Entities.User;

import java.util.List;

public interface IUserService {



    User retrieveUser (Integer id);

    List<User> retrieveAllUser();

}
