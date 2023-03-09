package com.waad.rest.model;

import java.util.Objects;
import java.io.Serializable;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

/**
 *
 * @author Alassani ABODJI <abodjialassani[at]gmail.com>
 */
public class User implements Serializable {

    private String username;
    private String fullName;
    private String function;

    /*---------------------------------------------------------
    |      C   O   N   S   T   R   U   C   T   O   R   S      |
    ==========================================================*/
    public User() {
    }

    public User(String username, String fullName, String function) {
        requireNonNull(username, "Username not specified !");

        this.username = username;
        this.fullName = fullName;
        this.function = function;
    }

    public static User of(String username, String fullName, String function) {
        return new User(username, fullName, function);
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFunction() {
        return function;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    /*---------------------------------------------------------
    |   H A S H C O D E  /  E Q U A L S  /  T O S T R I N G   |
    ==========================================================*/
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (isNull(obj) || !(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("username=").append(username);
        sb.append(", fullName=").append(fullName);
        sb.append(", function=").append(function);
        sb.append('}');
        return sb.toString();
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
