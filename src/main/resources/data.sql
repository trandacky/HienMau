insert into quyen(role) VALUES ('ROLE_ADMIN');
insert into quyen(role) VALUES ('ROLE_USER');

insert into nguoi_dung(ten_dang_nhap, mat_khau, email, is_active)
VALUES('admin', '$2a$10$avfzSArvLkdrZ3nJn6iWGu4Gs46V62IPnU3wO0Wxprmx/JVQk2XPW', 'thithom.12082000@gmail.com', true);


insert into quyen_nguoi_dung(nguoi_dung_id, quyen_id)
VALUES((select id from nguoi_dung where ten_dang_nhap = 'admin'),
  (select id from quyen where role = 'ROLE_ADMIN'));