package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Book;
import com.example.service.TopHostService;

@Controller
@RequestMapping("/topHost")
public class TopHostController {
	
	@Autowired
	TopHostService service;

	@ResponseBody
	@RequestMapping(value = "/getTopHosts", method = RequestMethod.GET)
	public String readersBooks() {
		String res = service.getTopHostList("top1");
		return res;
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		return "redirect:/{reader}";
	}

}
