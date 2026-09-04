INSERT INTO users(mid, mpwd, create_date, update_time ) VALUES
('admin', '1234', now(),now() ),
('kafell', '141543', now(),now()),
('ganatech', 'pw123',now(),now());

INSERT INTO categories(cno, cname, create_date, update_time) VALUES
(1101, '반팔티',now(),now()),
(2303, '청바지',now(),now()),
(4301, '운동화',now(),now());

INSERT INTO clothes(mno, cno, clcolor, clname, retype, create_date, update_time) VALUES
(1, 1101, 'white', '흰색 반팔티', NULL,now(),now()),
(1, 2303, 'blue', '진청 청바지', NULL,now(),now()),
(1, 4301, 'white', '흰색 운동화', NULL,now(),now());

INSERT INTO wearlog(clno, wcontext, create_date, update_time) VALUES
(1, '2026-08-01',now(),now()),
(1, '2026-08-10',now(),now()),
(2, '2026-08-14',now(),now());