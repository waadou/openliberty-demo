package com.waad.rest.db;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.enterprise.context.ApplicationScoped;

import com.waad.rest.model.User;

/**
 *
 * @author Alassani ABODJI <abodjialassani[at]gmail.com>
 */
@ApplicationScoped
public class AppEvent {

    private static final Logger LOGGER = Logger.getLogger(AppEvent.class.getName());

    public void onUserPersistedSynchronously(@Observes User user) {
        LOGGER.log(Level.INFO, "User ({0})", user);
        System.out.println("User: " + user);
    }

    public void onUserPersistedAsynchronously(@ObservesAsync User user) {
        LOGGER.log(Level.INFO, "User ({0})", user);
        System.out.println("User: " + user);
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
