DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, data_time, description, calories)
VALUES (100000, '2022-01-01 12:00:00', 'Обед', 1550),
       (100000, '2022-01-01 19:10:00', 'Ужин', 1050),
       (100000, '2022-01-02 09:00:00', 'Завтрак', 550),
       (100000, '2022-01-02 12:00:00', 'Обед', 1570),
       (100000, '2022-01-02 18:30:00', 'Ужин', 850),
       (100001, '2022-01-03 08:10:00', 'Админ завтрак', 350),
       (100000, '2022-01-03 12:30:00', 'Админ обед', 1270);