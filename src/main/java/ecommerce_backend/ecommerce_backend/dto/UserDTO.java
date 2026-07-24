package ecommerce_backend.ecommerce_backend.dto;
import ecommerce_backend.ecommerce_backend.model.Role;

public class UserDTO {

    public record Response(

            Long id,
            String name,
            String email,
            Role role

    ) {}

}