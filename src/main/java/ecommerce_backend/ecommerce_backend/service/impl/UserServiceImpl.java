package ecommerce_backend.ecommerce_backend.service.impl;

import ecommerce_backend.ecommerce_backend.dto.UserDTO;
import ecommerce_backend.ecommerce_backend.exception.BadRequestException;
import ecommerce_backend.ecommerce_backend.exception.ResourceNotFoundException;
import ecommerce_backend.ecommerce_backend.model.User;
import ecommerce_backend.ecommerce_backend.repository.UserRepository;
import ecommerce_backend.ecommerce_backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO.Response getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        return new UserDTO.Response(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public UserDTO.Response updateProfile(String email, UserDTO.UpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        // Update name if provided
        if (request.name() != null && !request.name().isBlank()) {
            user.setName(request.name());
        }

        // Change password if both old and new passwords are provided
        if (request.oldPassword() != null && request.newPassword() != null
                && !request.oldPassword().isBlank() && !request.newPassword().isBlank()) {

            if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
                throw new BadRequestException("Old password is incorrect");
            }

            user.setPassword(passwordEncoder.encode(request.newPassword()));
        }

        userRepository.save(user);

        return new UserDTO.Response(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}

