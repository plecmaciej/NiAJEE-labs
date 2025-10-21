package org.example.configuration.observer;

import org.example.movie.entity.EnumMovie;
import org.example.movie.entity.Movie;
import org.example.movie.service.MovieService;
import org.example.movieType.entity.MovieType;
import org.example.movieType.entity.EnumMovieType;
import org.example.movieType.service.MovieTypeService;
import org.example.user.entity.User;
import org.example.user.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Listener started automatically on servlet context initialization.
 * Initializes the application with default example data.
 */
@ApplicationScoped
public class InitializedData {

    /**
     * User service.
     */
    private final UserService userService;

    private final MovieTypeService movieTypeService;

    private final MovieService movieService;

    /**
     * The CDI container provides a built-in instance of {@link RequestContextController} that is dependent scoped for
     * the purposes of activating and deactivating.
     */
    private final RequestContextController requestContextController;

    /**
     *
     * @param userService user service
     * @param requestContextController CDI request context controller
     */
    @Inject
    public InitializedData(UserService userService,MovieTypeService movieTypeService, MovieService movieService, RequestContextController requestContextController) {
        this.userService = userService;
        this.movieTypeService = movieTypeService;
        this.movieService = movieService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    /**
     * Initializes example users in the application.
     */
    @SneakyThrows
    private void init() {
        requestContextController.activate();
        // Default admin user
        User admin = User.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .login("admin")
                .name("System")
                .surname("Admin")
                .birthDate(LocalDate.of(1990, 10, 21))
                .email("admin@example.com")
                .password("adminadmin")
                .build();

        // Example users
        User emily = User.builder()
                .id(UUID.fromString("7f2a9f17-4a9c-4b4c-8b1b-6d0e3b8f9c42"))
                .login("emily")
                .name("Emily")
                .surname("Stone")
                .birthDate(LocalDate.of(1998, 5, 14))
                .email("emily.stone@example.com")
                .password("useruser")
                .build();

        User daniel = User.builder()
                .id(UUID.fromString("2c3b6c62-8a34-44b5-b69a-fcb5b1a47d9a"))
                .login("daniel")
                .name("Daniel")
                .surname("Frost")
                .birthDate(LocalDate.of(1995, 9, 23))
                .email("daniel.frost@example.com")
                .password("useruser")
                .build();

        User sophia = User.builder()
                .id(UUID.fromString("e6a8740e-4a39-4f63-b63e-96a7cfe0b981"))
                .login("sophia")
                .name("Sophia")
                .surname("Miller")
                .birthDate(LocalDate.of(2000, 2, 11))
                .email("sophia.miller@example.com")
                .password("useruser")
                .build();

        User michael = User.builder()
                .id(UUID.fromString("b5e92a39-5578-4e67-9d46-2bbac8f8a78a"))
                .login("michael")
                .name("Michael")
                .surname("Reed")
                .birthDate(LocalDate.of(1997, 12, 3))
                .email("michael.reed@example.com")
                .password("useruser")
                .build();

        // Save users
        userService.create(admin);
        userService.updateAvatar(admin.getId(), this.getClass().getResourceAsStream("/rob.png"));
        userService.create(emily);
        userService.create(daniel);
        userService.create(sophia);
        userService.create(michael);


        MovieType horror = MovieType.builder()
                .id(UUID.fromString("47fc4252-9c4e-4b61-8042-05648be14bc0"))
                .typeName("Horror slasher")
                .description("Usually group of people trying to survive serial killer.")
                .age(EnumMovieType.Age.ADULTS)
                .build();

        MovieType comedy = MovieType.builder()
                .id(UUID.fromString("fe6946e8-db8c-4ffe-ac7a-b457372e65aa"))
                .typeName("Comedy")
                .description("Family comedy")
                .age(EnumMovieType.Age.CHILDRENS)
                .build();

        MovieType drama = MovieType.builder()
                .id(UUID.fromString("bbb60000-1a7d-45cb-b6ac-3812c8f8eb3d"))
                .typeName("Drama")
                .description("Romeo and juliet type movies.")
                .age(EnumMovieType.Age.TEENAGERS)
                .build();

        movieTypeService.create(horror);
        movieTypeService.create(comedy);
        movieTypeService.create(drama);

        Movie it = Movie.builder()
                .id(UUID.fromString("d8422238-ce90-4c9b-87ec-cd75f2bb32f3"))
                .title("It")
                .status(EnumMovie.Status.AVAILABLE)
                .releaseDate(LocalDate.of(2017, 1, 1))
                .price(40.20)
                .MovieType(horror)
                .user(admin)
                .build();

        Movie romeo = Movie.builder()
                .id(UUID.fromString("c2776082-6691-44e8-a9d9-1f405637d226"))
                .title("Romeo and Juliett")
                .status(EnumMovie.Status.BORROWED)
                .releaseDate(LocalDate.of(2013, 1, 1))
                .price(13.25)
                .MovieType(drama)
                .user(admin)
                .build();

        Movie smile = Movie.builder()
                .id(UUID.fromString("289b0034-2a5f-49e1-8701-81d5b3cf63b9"))
                .title("Smile")
                .status(EnumMovie.Status.RESERVED)
                .releaseDate(LocalDate.of(2017, 1, 1))
                .price(60.02)
                .MovieType(comedy)
                .user(admin)
                .build();

        movieService.create(it);
        movieService.create(romeo);
        movieService.create(smile);

        requestContextController.deactivate();
    }

    /**
     * Reads a resource file as a byte array.
     *
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }

}
