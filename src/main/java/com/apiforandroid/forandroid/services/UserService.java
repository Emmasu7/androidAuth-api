    package com.apiforandroid.forandroid.services;

    import com.apiforandroid.forandroid.entity.UserEntity;
    import com.apiforandroid.forandroid.repository.UserRepository;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;

    @Controller
    public class UserService {

        private UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public UserEntity registerUser(UserEntity user){
             UserEntity userCreated =  userRepository.save(user);

            return userCreated;

        }

        public UserEntity login(String email, String password){

            UserEntity user = userRepository.findByEmail(email);

            if (user == null) {
                throw new RuntimeException("Usuario con ese email no encontrado.");
            }

            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Password incorrecta.");
            }

            return user;
        }
    }
