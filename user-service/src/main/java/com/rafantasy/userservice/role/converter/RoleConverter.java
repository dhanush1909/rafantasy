package com.rafantasy.userservice.role.converter;

import com.rafantasy.userservice.role.Role;
import com.rafantasy.userservice.role.RoleType;
import com.rafantasy.userservice.domain.service.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static com.rafantasy.userservice.common.Constants.ROLE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class RoleConverter implements Converter<RoleType, Role> {

    private final RoleRepository roleRepository;

    @Override
    public Role convert(RoleType roleType) {
        return roleRepository.findByRoleType(roleType).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, ROLE_NOT_FOUND));
    }
}
