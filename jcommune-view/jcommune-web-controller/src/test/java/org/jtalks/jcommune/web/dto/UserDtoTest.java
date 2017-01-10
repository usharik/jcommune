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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by Alexey Usharovskiy on 11.01.17.
 */
public class UserDtoTest {
    private List<User> userList;

    @BeforeMethod
    public void initTestUserList() {
        userList = new ArrayList<>();
        userList.add(new User("name4", "mail4@mail.com", "password4", ""));
        userList.add(new User("name2", "mail2@mail.com", "password2", ""));
        userList.add(new User("name3", "mail3@mail.com", "password3", ""));
        userList.add(new User("name7", "mail7@mail.com", "password7", ""));
        userList.add(new User("name9", "mail9@mail.com", "password9", ""));
    }

    @Test
    public void doConvertingToDtoListWithoutSortAndLimit() {
        List<UserDto> userDtos = UserDto.convertToUserDtoList(userList, null);
        assertEquals(userDtos.size(), userList.size());
        Iterator<User> userIterator = userList.iterator();
        for (UserDto userDto : userDtos) {
            User user = userIterator.next();
            assertEquals(userDto.getId(), user.getId());
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getEmail(), user.getEmail());
        }
    }

    @Test
    public void doConvertingToDtoListWithSort() {
        List<UserDto> userDtos = UserDto.convertToUserDtoList(userList, UserDto.BY_NAME_COMPARATOR);
        Collections.sort(userList, BY_NAME_COMPARATOR);
        Iterator<User> userIterator = userList.iterator();
        for (UserDto userDto : userDtos) {
            User user = userIterator.next();
            assertEquals(userDto.getId(), user.getId());
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getEmail(), user.getEmail());
        }
    }

    private static Comparator<User> BY_NAME_COMPARATOR = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            return o1.getUsername().compareTo(o2.getUsername());
        }
    };
}
