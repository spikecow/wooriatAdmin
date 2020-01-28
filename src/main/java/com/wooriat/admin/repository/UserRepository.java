package com.wooriat.admin.repository;

import com.wooriat.admin.domain.TbUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TbUser,Long> {
    public Page<TbUser> findByUserNmLikeOrEmailLike(String sw,String sw1, Pageable page);

    public TbUser findByUserId(String userId);
    public TbUser findByUserIdAndUserPwd(String userId, String userPwd);
    public TbUser findByEmail(String email);
}
