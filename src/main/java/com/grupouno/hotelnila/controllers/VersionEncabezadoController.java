package com.grupouno.hotelnila.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionEncabezadoController {

	@GetMapping(value = "/api", headers = "X-API-VERSION=1")
	public String getV1() {
		return "Request Header Versioning - V1";
	}
	
	@GetMapping(value = "/api", headers = "X-API-VERSION=2")
	public String getV2() {
		return "Request Header Versioning - V2";
	}
	
}
