package com.gmail.kutepov89.sergey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhotoNotFoundException extends RuntimeException {}