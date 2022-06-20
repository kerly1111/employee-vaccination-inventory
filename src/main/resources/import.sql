/* Roles del sistem */
INSERT INTO "role" (name) VALUES ('ROLE_ADMIN');
INSERT INTO "role" (name) VALUES ('ROLE_EMPLOYEE');

/* Usuario administrador */
/* usuario : admin */
/*   password : 12345 */

INSERT INTO "user" (id_user, username, password, is_active, registration_date, fk_role) VALUES (1, 'admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS', true, '2022-01-01', 1);