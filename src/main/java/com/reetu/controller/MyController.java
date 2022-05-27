package com.reetu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler;

import com.reetu.beans.Med;

@Controller
public class MyController {
	
	RestTemplate rt=new RestTemplate();
	String URL="http://localhost:9888";
	
	       @RequestMapping("/")
	       public String homepage() {
	    	   return "index";
	       }
	       
	       @RequestMapping("/addmed")
	       public String addmed(@ModelAttribute Med m, MultipartFile image, Model model) {
	    	   String API="/addmedicine";
	    	   
	    	   HttpHeaders header=new HttpHeaders();
	    	   header.setContentType(MediaType.MULTIPART_FORM_DATA);
	    	   
	    	   LinkedMultiValueMap<String, Object> data=new LinkedMultiValueMap<>();
	    	   data.add("Med", m);
	    	   data.add("image", convert(image));
	    	   
	    	   HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity<>(data, header);
	    	   
	    	   ResponseEntity<String> s= rt.postForEntity(URL+API, requestEntity, null, String.class);
	    	   if(s.getStatusCode()==HttpStatus.OK) {
	    		   model.addAttribute("result" ,m.getName()+ "Added Successfully");
	    	   }else {
	    		   model.addAttribute("result" ,m.getName()+ "Already exists");
	    	   }
	    	   return "index";
	    	   
	    	   
	       }
	       
	       //convert file to FileSystemResouurce 
	       public static FileSystemResource convert(MultipartFile file) {
	    	   File convert=new File(file.getOriginalFilename());
	    	   try {
	    		   convert.createNewFile();
	    		   FileOutputStream fos=new FileOutputStream(convert);
	    		   fos.write(file.getBytes());
	    		   fos.close();
	    		   
				       
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	   
	    	   return  new FileSystemResource(convert);
	       }
	       
	       //to view all
	       @RequestMapping("/viewall")
	       public String Viewall(Model model) {
	    	   String API="/getallmed";
	    	   List<Object> med=rt.getForObject(URL+API, List.class);
	    	   model.addAttribute("med",med);
	    	   return "toviewall";
	       }
	       
	       //to get image
	       @RequestMapping("/getimage")
	       public void getimage(int mid, HttpServletResponse response) {
	    	   String API="/getimage/"+mid;
	    	   byte[] b = rt.getForObject(URL+API, byte[].class);
	    	   try {
				        response.getOutputStream().write(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
	       }
	       
	       //update image
	       @RequestMapping("/Updateimage")
	       public String Updateimage(int mid, MultipartFile image, Model model) {
	       String API = "/updateimage";
	       
	       HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	       
	       LinkedMultiValueMap<String, Object> data=new LinkedMultiValueMap<>();
	       data.add("mid", mid);
	       data.add("image", convert(image));
	       
	       HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity=new HttpEntity<>(data,headers);
	       ResponseEntity<String> result=rt.exchange(URL+API, HttpMethod.PUT, requestEntity, String.class);
	       if(result.getStatusCode()==HttpStatus.OK) {
	    	   model.addAttribute("result", "Success");
	       }else {
	    	   model.addAttribute("result", "Failed");
	       }
	       
	       API="/getallmed";
    	   List<Object> med=rt.getForObject(URL+API, List.class);
    	   model.addAttribute("med",med);
	        return "toviewall";
	       
	      }
	       
	      //common for all
	       @ModelAttribute
	       public void getallids(Model model) {
	    	   String API="/getallids";
	    	   List<Integer> ids=rt.getForObject(URL+API, List.class);
	    	   model.addAttribute("ids", ids);
	    	   
	       }
	       
	       //update by ids
	       @RequestMapping("/updatemed")
	       public String Update(@ModelAttribute Med m, Model model) {
	    	   String API="/update";
	    	   HttpEntity<Med> requestEntity= new HttpEntity<Med>(m);
	    	   ResponseEntity<String> result=rt.exchange(URL+API, HttpMethod.PUT, requestEntity, String.class);
	    	   if(result.getStatusCode()==HttpStatus.OK) {
	    		   model.addAttribute("result", m.getName() + "Updated Sucessfully");
	    	   }else {
	    		   model.addAttribute("result", m.getName() + "Already Exists");
	    	   }
	    	  return "update";
	       }
	       
	       @RequestMapping("/UPDATE")
	       public String page() {
	    	   return "update";
	       }
}
