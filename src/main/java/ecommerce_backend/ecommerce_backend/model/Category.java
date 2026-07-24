package ecommerce_backend.ecommerce_backend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public static <E> Enum<E> builder() {
    }

    public Long getId() {
    }

    public String getName() {
    }

    public void setName(@NotBlank(message = "Category name is required") @Size(max = 100, message = "Category name must not exceed 100 characters") String name) {
    }
}