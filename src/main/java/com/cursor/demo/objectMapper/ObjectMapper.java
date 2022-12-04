package com.cursor.demo.objectMapper;

import com.cursor.demo.dto.UserDto;
import com.cursor.demo.model.UserEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectMapper {

    public static UserEntity mapFromDtoToEntity(UserDto userDto) {
        System.out.println("****************************************************" + userDto.getUserName());
        return new UserEntity(userDto.getUserName(), userDto.getPassword(), "@");
    }

}
