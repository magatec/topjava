DELETE FROM meals;
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

INSERT INTO meals (id, user_id, date_time, description, calories)
VALUES (100003, 100000, '2020-01-30 10:00', 'Завтрак', 500),
       (100004, 100000, '2020-01-30 13:00', 'Обед', 1000),
       (100005, 100000, '2020-01-30 20:00', 'Ужин', 500),
       (100006, 100000, '2020-01-31 00:00', 'Еда на граничное значение', 100),
       (100007, 100000, '2020-01-31 10:00', 'Завтрак', 500),
       (100008, 100000, '2020-01-31 13:00', 'Обед', 1000),
       (100009, 100000, '2020-01-31 20:00', 'Ужин', 510),
       (100010, 100001, '2020-01-31 14:00', 'Админ ланч', 510),
       (100011, 100001, '2020-01-31 21:00', 'Админ ужин', 1500);

