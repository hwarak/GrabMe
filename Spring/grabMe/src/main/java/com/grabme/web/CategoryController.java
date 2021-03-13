package com.grabme.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = { "6. Category" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

	
}
