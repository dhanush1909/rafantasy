package com.rafantasy.userservice.domain.converter;

import com.rafantasy.userservice.domain.model.Image;
import com.rafantasy.userservice.domain.service.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Component
public class ImageConverter implements Converter<String, Image> {

    private final ImageRepository imageRepository;

    @Override
    public Image convert(String s) {
        return imageRepository.findById(Integer.parseInt(s)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "no.such.image"));
    }
}
