package com.library.svc.svcImpl;

import com.library.dao.model.User;
import com.library.dao.repository.UserRepository;
import com.library.svc.contracts.UserSvc;
import com.library.validation.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSvcImpl implements UserSvc {

    // Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repo;

    @Override
    public void createClient(UserForm clientForm) {

        //Long userId = this.getMaxUserId() + 1;
        String encrytedPassword = this.passwordEncoder.encode(clientForm.getPassword());

        User client = new User(clientForm.getFirstName(), //
                clientForm.getLastName(), clientForm.getMail(), //
                encrytedPassword);

        repo.save(client);

    }
}
