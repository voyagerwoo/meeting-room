insert into user(id, name, dept) values (1001, 'voyagerwoo', 'Backend dev team');
insert into user(id, name, dept) values (1002, 'humanbeing', 'frontend dev team');

insert into meeting_room(id, name, size) values (10001, '도토리방', 8);
insert into meeting_room(id, name, size) values (10002, '참깨방', 4);
insert into meeting_room(id, name, size) values (10003, '참나무방', 12);

insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1001, 10001, '2019-12-26 09:00:00', '2019-12-26 10:00:00');
insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1001, 10002, '2019-12-27 09:00:00', '2019-12-27 10:00:00');
insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1001, 10002, '2019-12-28 09:00:00', '2019-12-28 10:00:00');
insert into reservation(user_id, meeting_room_id, start_time, end_time) values (1002, 10001, '2019-12-27 09:00:00', '2019-12-27 10:00:00');