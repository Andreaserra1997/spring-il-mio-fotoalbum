INSERT INTO db_album.photos (title, description, url, visible) VALUES('img1', 'Immagine1', 'https://picsum.photos/id/100/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img2', 'Immagine2', 'https://picsum.photos/id/101/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img3', 'Immagine3', 'https://picsum.photos/id/102/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img4', 'Immagine4', 'https://picsum.photos/id/103/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img5', 'Immagine5', 'https://picsum.photos/id/104/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img6', 'Immagine6', 'https://picsum.photos/id/110/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img7', 'Immagine7', 'https://picsum.photos/id/106/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img8', 'Immagine8', 'https://picsum.photos/id/107/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img9', 'Immagine9', 'https://picsum.photos/id/108/200/300', 1);
INSERT INTO db_album.photos (title, description, url, visible) VALUES('img10', 'Immagine10', 'https://picsum.photos/id/109/200/300', 1);

INSERT INTO categories(name) VALUES('wild');
INSERT INTO categories(name) VALUES('natura');
INSERT INTO categories(name) VALUES('sport');
INSERT INTO categories(name) VALUES('bianco e nero');
INSERT INTO categories(name) VALUES('scienza');
INSERT INTO categories(name) VALUES('paesaggi');

INSERT INTO db_album.photos_categories (photos_id, categories_id) VALUES(1, 2);
INSERT INTO db_album.photos_categories (photos_id, categories_id) VALUES(1, 6);


INSERT INTO roles (id, name) VALUES(1, 'ADMIN');
INSERT INTO roles (id, name) VALUES(2, 'USER');

INSERT INTO users (email, password, first_name, last_name, registered_at) VALUES ('andrea@mail.com', '{noop}andrea', 'Andrea', 'Serra', '2023-11-19 12:00');
INSERT INTO users (email, password, first_name, last_name, registered_at) VALUES ('robi@mail.com', '{noop}robi', 'Roberta', 'Carboni', '2023-11-19 12:30');

INSERT INTO users_roles (user_id, roles_id) VALUE(1, 1);
INSERT INTO users_roles (user_id, roles_id) VALUE(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUE(2, 2);