package com.waad.rest.db;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.Collection;
import static java.util.Objects.isNull;

import com.waad.rest.model.User;

/**
 *
 * @author Alassani ABODJI (abodjialassani[at]gmail.com)
 */
public interface Database {

    void saveUser(User user);

    void deleteUser(String username);

    Optional<User> getUser(String username);

    List<User> getUsers();

    List<User> getUsers(Collection<String> usernames);

    default void deleteUsers(Collection<String> usernames) {
        if (isNull(usernames)) {
            return;
        }
        usernames.forEach(this::deleteUser);
    }

    default void deleteUsers(String... usernames) {
        if (isNull(usernames)) {
            return;
        }
        deleteUsers(Arrays.asList(usernames));
    }

    default void deleteUser(User user) {
        if (isNull(user)) {
            return;
        }
        deleteUser(user.getUsername());
    }

    default void saveUsers(Iterable<User> users) {
        if (isNull(users)) {
            return;
        }
        users.forEach(this::saveUser);
    }

    default void saveUsers(User... users) {
        if (isNull(users)) {
            return;
        }
        saveUsers(Arrays.asList(users));
    }

    default List<User> getUsers(String... usernames) {
        if (isNull(usernames)) {
            return List.of();
        }
        return getUsers(Arrays.asList(usernames));
    }
}
