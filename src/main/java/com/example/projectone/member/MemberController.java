package com.example.projectone.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping(value="/member")
@RestController
public class MemberController {

	@Autowired
	MemberRepo repo;
	
	@PostMapping(value="/creat")
	public <T> ResponseEntity<?>  addMember (@RequestBody  Member mem){
//	public <T> ResponseEntity<?>  addMember (@RequestBody  Object mem){
				
        if(mem == null){
            return new ResponseEntity<String> ("none",HttpStatus.NOT_FOUND);
        }
        
        System.out.println(mem.toString());
        
        ObjectMapper mapper = new ObjectMapper(); 
//        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
//        Member recMem = new Member();
//        recMem.idx = 2;
//        recMem.userName ="name";
//        recMem.userId = "id";
//        recMem.passwd = "passtest";
//        System.out.println(recMem.toString());
        
        Member subMem = mapper.convertValue(mem, new TypeReference<Member>() {});
        System.out.println(subMem.toString());
        
        Member recMem = repo.save(subMem);
        
		return new ResponseEntity<> (recMem, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/list")
	public <T> ResponseEntity<?>  getListMember (){
		
		List<Member> listMem = repo.findAll();
		return new ResponseEntity<> (listMem, HttpStatus.OK);
	}
	
	@GetMapping(value="/one/{idx}")
	public <T> ResponseEntity<?>  getMemberbyIdx (@PathVariable(value="idx") Long idx){		
		Member getMem = repo.findById(idx).orElse(null);
		return new ResponseEntity<> (getMem, HttpStatus.OK);
	}
	
	
}
