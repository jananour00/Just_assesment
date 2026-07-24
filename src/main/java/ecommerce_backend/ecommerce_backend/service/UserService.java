package ecommerce_backend.ecommerce_backend.service;

import ecommerce_backend.ecommerce_backend.dto.UserDTO;

public interface UserService {

    UserDTO.Response getProfile(String email);

    UserDTO.Response updateProfile(String email, UserDTO.UpdateRequest request);
}

