package ecommerce_backend.ecommerce_backend.service;
import ecommerce_backend.ecommerce_backend.dto.AuthDTO;

public interface AuthService {

    AuthDTO.Response register(AuthDTO.RegisterRequest request);

    AuthDTO.Response login(AuthDTO.LoginRequest request);

}