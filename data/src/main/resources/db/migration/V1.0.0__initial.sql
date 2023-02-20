
    create table "commentaries" (
       id uuid not null,
        body varchar(255),
        title varchar(255),
        user_id uuid,
        primary key (id)
    );

    create table "projects" (
       id uuid not null,
        link varchar(255),
        name varchar(255),
        year int4 not null,
        user_id uuid,
        primary key (id)
    );

    create table "resumes" (
       id uuid not null,
        additionalInformation varchar(255),
        currentJob varchar(255),
        databases varchar(255),
        frameworks varchar(255),
        languages varchar(255),
        name varchar(255),
        otherTechnologies varchar(255),
        quote varchar(255),
        user_id uuid,
        primary key (id)
    );

    create table "users" (
       id uuid not null,
        login varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table "workExperienceItems" (
       id uuid not null,
        dates varchar(255),
        place varchar(255),
        position varchar(255),
        user_id uuid,
        primary key (id)
    );

    alter table if exists "commentaries" 
       add constraint FKdablc7xw22sjiv3uxfqv1obxk 
       foreign key (user_id) 
       references "users";

    alter table if exists "projects" 
       add constraint FKc5tgkacvno41q4caubx05pc3j 
       foreign key (user_id) 
       references "users";

    alter table if exists "resumes" 
       add constraint FKqbc3sssxvg6dp9usbsggxcp28 
       foreign key (user_id) 
       references "users";

    alter table if exists "workExperienceItems" 
       add constraint FK8h1iautls4bw9fha1euu9hwja 
       foreign key (user_id) 
       references "users";
