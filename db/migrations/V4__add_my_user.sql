-- Create a test user
insert into ft.user (email, `password`, created_on) values ('danmofo+ft-test@gmail.com', '{bcrypt}$2a$10$Kyi7f2xlP8Js9RSf3h0XpOgip6QUiMjLsScgRfe1JCIZooShwvA/m', now());

-- Create my user
insert into ft.user (email, `password`, created_on) values ('danmofo@gmail.com', '{bcrypt}$2a$10$Kyi7f2xlP8Js9RSf3h0XpOgip6QUiMjLsScgRfe1JCIZooShwvA/m', now());