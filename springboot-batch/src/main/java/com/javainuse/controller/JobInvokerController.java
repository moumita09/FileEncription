package com.javainuse.controller;
 
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
 
@Controller
public class JobInvokerController {
 
    @Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
    
  static  String path;
  
  @Autowired
  private ServletContext servletContext;
	@Autowired
    ServletContext context;
  
  @GetMapping("/")
  public String main(Model model) {
      return "fileEncription";
  }
  
  
    @PostMapping("/invokejob")
    public String handle(@RequestParam("startDate")MultipartFile file,Model model) throws Exception {
    	path=getServerStoredFilePath(file);
    	JobParameters jobParameters = new JobParametersBuilder().addString("path", ""+path+"").toJobParameters();
        jobLauncher.run(processJob, jobParameters);
        return "fileEncription";
    }
    
    public String getServerStoredFilePath(MultipartFile file) {
	 	   String path=context.getRealPath("")+"/storedFile";
	 		 String dataBasePath=path+"/"+ file.getOriginalFilename();
			   boolean flag=createFolder(path);
	 	   
	 	   File f = new File(path + "/" + file.getOriginalFilename());
			  try {
				  byte fileByte[] = file.getBytes();
			         
			          if (!f.exists()) {
			              f.createNewFile();
			          }

			          BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(f));
			          bout.write(fileByte);
			          bout.flush();
			          bout.close();
			          
			  }catch(Exception e){
				  System.out.println(e);
			  }
			  
			  return dataBasePath;
		    }
		

		
		  public boolean createFolder(String path) {
	   	   
	   	   boolean flag=false;
	   	   File file = new File(path);
	   	   if (!file.exists()) {
	              if (file.mkdir()) {
	                  System.out.println("Directory is created!");
	                  flag=true;
	              } else {
	                  System.out.println("Failed to create directory!");
	              }
	          }else {flag=true;}
	   	   
	   	   
	   	   return flag;
	      }
	
	
    
    public  String getJobParameters() {
    	return path;
    };
}