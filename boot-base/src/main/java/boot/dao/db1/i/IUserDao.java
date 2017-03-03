package boot.dao.db1.i;

import org.springframework.data.jpa.repository.JpaRepository;

import boot.dao.db1.entity.User;

public interface IUserDao extends JpaRepository<User,Integer> {

}
