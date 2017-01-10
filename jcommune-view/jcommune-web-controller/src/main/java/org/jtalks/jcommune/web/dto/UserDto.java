/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.web.dto;

import org.jtalks.common.model.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Simple Dto for User object
 * Created by Alexey Usharovskiy on 11.01.17.
 */
public class UserDto {
    private long id;
    private String username;
    private String email;

    UserDto() {
    }

    UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.id = user.getId();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public static List<UserDto> convertToUserDtoList(List<User> users, Comparator<UserDto> comparator) {
        List<UserDto> result = new ArrayList<>();
        if (users == null) {
            return result;
        }
        for (User user : users) {
            result.add(new UserDto(user));
        }
        if (comparator != null) {
            Collections.sort(result, comparator);
        }
        return result;
    }

    public static Comparator<UserDto> BY_NAME_COMPARATOR = new Comparator<UserDto>() {
        @Override
        public int compare(UserDto o1, UserDto o2) {
            return o1.getUsername().compareTo(o2.getUsername());
        }
    };
}
