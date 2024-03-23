package br.edu.postech.shopinildo.usuario;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.edu.postech.shopinildo.usuario.dto.UserRequest;

// Todo: encrypt password
// Todo: validate email uniqueness?
@Service
public class UserService {

    private final MongoTemplate mongoTemplate;

    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public User registerNewCustomer(UserRequest userRequestDTO) {
        return mongoTemplate
                .save(new User(null, userRequestDTO.email(), userRequestDTO.password(), Role.ROLE_CUSTOMER));
    }

    public User registerNewAdmin(UserRequest userRequestDTO) {
        return mongoTemplate
                .save(new User(null, userRequestDTO.email(), userRequestDTO.password(), Role.ROLE_ADMIN));
    }

    public User login(String email, String password) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("email").is(email).and("password").is(password)),
                User.class);
    }

    public User updatePassword(Long userId, String newPassword) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(userId)),
                Update.update("password", newPassword),
                User.class);
    }

    public User updateEmail(Long userId, String newEmail) {
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("id").is(userId)),
                Update.update("email", newEmail),
                User.class);
    }
}
