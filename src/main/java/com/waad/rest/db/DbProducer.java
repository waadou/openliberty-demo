package com.waad.rest.db;

import com.waad.rest.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Alassani ABODJI <abodjialassani[at]gmail.com>
 */
@ApplicationScoped
public class DbProducer {

    @Produces
    @Selected(Selected.Type.TEST)
    public TestDatabase getDatabase() {
        return new TestDatabaseImpl();
    }

    private class TestDatabaseImpl implements TestDatabase {

        private final Map<String, User> users = new HashMap<>();

        public TestDatabaseImpl() {
            loadUsers();
        }

        @Override
        public void saveUser(User user) {
            if (isNull(user)) {
                return;
            }
            String username = user.getUsername();

            if (users.containsKey(username)) {
                users.remove(username);
            }
            users.put(username, user);
        }

        @Override
        public void deleteUser(String username) {
            if (isNull(username) || username.isBlank()) {
                return;
            }
            users.remove(username);
        }

        @Override
        public List<User> getUsers() {
            return new ArrayList<>(users.values());
        }

        @Override
        public List<User> getUsers(Collection<String> usernames) {
            if (isNull(usernames)) {
                return List.of();
            }
            return users.entrySet().stream()
                    .filter(entry -> usernames.contains(entry.getKey()))
                    .map(Map.Entry::getValue)
                    .collect(toList());
        }

        @Override
        public Optional<User> getUser(String username) {
            if (isNull(username) || username.isBlank()) {
                return Optional.empty();
            }
            return Optional.ofNullable(users.get(username));
        }

        private void loadUsers() {
            List<User> initialUsers = List.of(new User[]{
                User.of("dmelany", "Melany DEPARDY", "CEO"),
                User.of("ldarry", "Leon DARRY", "Manager"),
                User.of("johns", "John Senior", "Maintainer"),
                User.of("faddy", "Flaubert ADDY", "Developer"),
                User.of("llamard", "Lorent LAMARD", "Teacher")
            });

            saveUsers(initialUsers);
        }
    }
}

/*=============================================================================
 * Copyright 2023 Waad Soft<https://www.waadsoft.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 =============================================================================*/
