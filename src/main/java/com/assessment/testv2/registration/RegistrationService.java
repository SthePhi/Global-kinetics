package com.assessment.testv2.registration;

import com.assessment.testv2.model.ApplicationUser;
import com.assessment.testv2.registration.token.ConfirmationToken;
import com.assessment.testv2.registration.token.ConfirmationTokenService;
import com.assessment.testv2.services.UserService;
import com.assessment.testv2.utility.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isEmailValid = emailValidator.test(request.getEmail());

        if (!isEmailValid) {
            throw new IllegalStateException("Email is not valid");
        }
        return userService.signUpUser(new ApplicationUser(request.getFirstName(),
                request.getLastName(),
                request.getEmail(), request.getPassword(),
                UserRole.USER));
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.get
    }
}
