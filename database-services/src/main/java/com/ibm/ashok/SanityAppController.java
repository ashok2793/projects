package com.ibm.ashok;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Sanity;
import com.ibm.service.SanityService;

@RestController
@RequestMapping("/sanity")
public class SanityAppController {
	
	
	@Autowired
	private SanityService sanityService;
	
	public SanityAppController(SanityService sanityService){
		this.sanityService = sanityService;
	}


	@ResponseBody
	@PostMapping("/insert")
	public Sanity createSanity(@RequestBody Sanity sanity) {
		return sanityService.addSanity(sanity);
	}
	
	@ResponseBody
	@GetMapping("/getById/{id}")
	public Optional<Sanity> getSanityById(@PathVariable("id") Long id){
		return sanityService.getSanityById(id);
	}
	
	@ResponseBody
	@PostMapping("/update")
	public Sanity update(@RequestBody Sanity sanity) throws Exception{
		 return sanityService.update(sanity);
	}
	
	@ResponseBody
	@PostMapping("/delete")
	public void delete(@PathVariable("id") Long id){
		  sanityService.delete(id);;
	}
	
	@ResponseBody
	@GetMapping("/getAll")
	public List<Sanity> getAllSanity(){
		return sanityService.getAllSanities();
	}
	
	@PostMapping("/data")
	public void parseData() throws IOException {
		sanityService.sendData();
	}
	
	
}
