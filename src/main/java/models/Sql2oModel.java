package models;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.UUID;

public class Sql2oModel implements Model, UserModel {

    private Sql2o sql2o;

    public Sql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;

    }

    @Override
    public UUID createPost(String title, String content) {
        //TODO - implement this
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        //TODO - implement this
        return null;
    }

    @Override
    public UUID createUser(String first_name, String last_name, String password, String email) {
        try (Connection conn = sql2o.beginTransaction()) {
            UUID userUuid = UUID.randomUUID();
            conn.createQuery("insert into users(id, first_name, last_name, password, email) VALUES (:id, :first_name, :last_name, :email, :password)")
                    .addParameter("id", userUuid)
                    .addParameter("first_name", first_name)
                    .addParameter("last_name", last_name)
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .executeUpdate();
            conn.commit();
            return userUuid;
        }
    }
}