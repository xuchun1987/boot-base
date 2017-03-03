package boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import boot.dao.db1.entity.User;
import boot.dao.db1.i.IUserDao;
import boot.dao.db2.entity.User2;
import boot.dao.db2.i.IUserDao2;

@Service
public class UserService {
	 @Autowired
	 private IUserDao iUserDao;
	 
	 @Autowired
	 private IUserDao2 iUserDao2;
	 
	 public User findOneById(Integer id){
		 return iUserDao.findOne(id);
	 }
	 
	 public User2 findOneById2(Integer id){
		 return iUserDao2.findOne(id);
	 }
	 
	@Transactional(value="transactionManagerOne",rollbackFor={Exception.class, RuntimeException.class})
	 public void saveUser(User user) throws Exception{
		 iUserDao.save(user);
		 throw new Exception("xxxxxx");
	 }
	
	@Transactional(value="transactionManagerSecondary")
	 public void saveUser2(User2 user) throws Exception{
		 iUserDao2.save(user);
		 throw new Exception("xxxxxx");
	 }
	
	@Transactional(value="transactionManagerOne",
			rollbackFor={Exception.class, RuntimeException.class},
			propagation=Propagation.NOT_SUPPORTED)
	public void testAdd() throws Exception{
		User user=new User();
		user.setEmail("yy");
		user.setNickName("yy");
		user.setPassword("pp");
		user.setUsername("yy");
		iUserDao.save(user);
		testAdd2();
	}
	
	@Transactional(value="transactionManagerOne",
			rollbackFor={Exception.class, RuntimeException.class},
			propagation = Propagation.REQUIRED)//外面的失败不回滚里面的方法
	public void testAdd2() throws Exception{
		User user=new User();
		user.setEmail("yy");
		user.setNickName("yy");
		user.setPassword("pp");
		user.setUsername("yy");
		iUserDao.save(user);
		 throw new Exception("xxxxxx");
	}

}
