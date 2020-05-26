insert into sys_user (username,password,nickname, create_time, update_time) select stu_id as username, '123456' as password, stu_id as nickname, NOW() as create_time, NOW() as update_time from student where stu_id not in (select username from sys_user);
insert into sys_user_role (user_id, role_id) select id as user_id, 5 as role_id from sys_user where id not in (select user_id from sys_user_role)
