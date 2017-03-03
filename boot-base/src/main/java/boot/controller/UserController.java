package boot.controller;

import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.dao.db1.entity.User;
import boot.dao.db2.entity.User2;
import boot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	
    @ApiOperation(value="查询用户", notes="根据用户id查询用户")
  /*  @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")*/
    @RequestMapping(value="/user/{id}")
	public User user(@PathVariable("id") Integer id) throws Exception{
    	User user=userService.findOneById(id);
    	if(user!=null){
    		logger.info(user.getNickName()+":"+user.getEmail());
    	}else{
    		throw new Exception("查询用户异常！");
    	}
		return user;
	}
    
    @RequestMapping(value="/user2/{id}")
	public User2 user2(@PathVariable("id") Integer id) throws Exception{
    	User2 user=userService.findOneById2(id);
    	if(user!=null){
    		logger.info(user.getNickName()+":"+user.getEmail());
    	}else{
    		throw new Exception("查询用户异常！");
    	}
		return user;
	}
    
    
    @RequestMapping(value="/user/add")
   	public User addUser() throws Exception{
       	User user=new User();
       	user.setEmail("xxx");
       	user.setNickName("yy");
       	user.setPassword("111");
       	user.setUsername("gggg");
       	userService.saveUser(user);
   		return user;
   	}
    
    @RequestMapping(value="/user2/add")
   	public User2 addUser2() throws Exception{
       	User2 user=new User2();
       	user.setEmail("xxx");
       	user.setNickName("yy");
       	user.setPassword("111");
       	user.setUsername("gggg");
       	userService.saveUser2(user);
   		return user;
   	}
    
    @RequestMapping(value="/test")
    public String testadd(){
    	try {
			userService.testAdd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "xx";
    }

}
