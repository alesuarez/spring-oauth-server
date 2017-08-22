insert into user(id, name, login, password) values (1,'Roy','roy','spring');
insert into user(id, name, login, password) values (2,'Craig','craig','spring');
insert into user(id, name, login, password) values (3,'Greg','greg','spring');
 
insert into role(id, name) values (1,'ROLE_USER');
insert into role(id, name) values (2,'ROLE_ADMIN');
insert into role(id, name) values (3,'ROLE_GUEST'); 

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (1,2);
insert into user_role(user_id, role_id) values (2,1);
insert into user_role(user_id, role_id) values (3,1);

CREATE TABLE oauth_client_details (
  client_id CHARACTER VARYING(256) PRIMARY KEY,
  resource_ids CHARACTER VARYING(256),
  client_secret CHARACTER VARYING(256),
  scope CHARACTER VARYING(256),
  authorized_grant_types CHARACTER VARYING(256),
  web_server_redirect_uri CHARACTER VARYING(256),
  authorities CHARACTER VARYING(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information CHARACTER VARYING(4096),
  autoapprove CHARACTER VARYING(256)
);

CREATE TABLE oauth_client_token (
  token_id CHARACTER VARYING(256),
  token BYTEA,
  authentication_id CHARACTER VARYING(256),
  user_name CHARACTER VARYING(256),
  client_id CHARACTER VARYING(256)
);

CREATE TABLE oauth_access_token (
  token_id CHARACTER VARYING(256),
  token BYTEA,
  authentication_id CHARACTER VARYING(256),
  user_name CHARACTER VARYING(256),
  client_id CHARACTER VARYING(256),
  authentication BYTEA,
  refresh_token CHARACTER VARYING(256)
);

CREATE TABLE oauth_refresh_token (
  token_id CHARACTER VARYING(256),
  token BYTEA,
  authentication BYTEA
);

CREATE TABLE oauth_code (
  code CHARACTER VARYING(256), authentication BYTEA
);

CREATE TABLE oauth_approvals (
  userId CHARACTER VARYING(256),
  clientId CHARACTER VARYING(256),
  scope CHARACTER VARYING(256),
  status CHARACTER VARYING(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);