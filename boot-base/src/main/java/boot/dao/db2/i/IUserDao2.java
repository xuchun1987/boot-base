package boot.dao.db2.i;

import org.springframework.data.jpa.repository.JpaRepository;

import boot.dao.db2.entity.User2;

public interface IUserDao2 extends JpaRepository<User2,Integer> {

}
