package pfe.service.authentication.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User>findByEmail(String email);
    public Optional<User> findByActivationToken(String activationToken);


    @Query(value = "SELECT COUNT(*) FROM USER",
            nativeQuery = true)
    int sizeUsers();


    @Query(value = "SELECT COUNT(*) FROM USER u where u.accountStatus='VERIFIED'",
            nativeQuery = true)
    int sizeUsersVerified();


    @Query(value = "SELECT COUNT(*) FROM USER u where u.accountStatus='NOT_VERIFIED'",
            nativeQuery = true)
    int sizeUsersNotVerified();

    @Query(value = "SELECT COUNT(*) FROM USER u where u.address= :address",
            nativeQuery = true)
    int sizeUsersByCountry(@Param("address") String address);












}

