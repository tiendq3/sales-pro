package com.example.quanlybanhang.repository;


import com.example.quanlybanhang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Modifying
//    @Query(
//            value = "update users set users.username =:newUserName ,users.password =:newPassword,users.last_name=:newLastName,users.first_name=:newFirstName where users.id=:id",
//            nativeQuery = true)
//    void updateUser(
//            @Param("id") Long id,
//            @Param("newUserName") String newUserName,
//            @Param("newPassword") String newPassword,
//            @Param("newLastName") String newLastName,
//            @Param("newFirstName") String newFirstName);
}
