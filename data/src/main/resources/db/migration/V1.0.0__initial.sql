
    create table "commentaries" (
       id uuid not null,
        body varchar(255),
        title varchar(255),
        user_id uuid,
        primary key (id)
    );

    create table "databases" (
       id uuid not null,
        name varchar(255),
        primary key (id)
    );

    create table "frameworks" (
       id uuid not null,
        name varchar(255),
        primary key (id)
    );

    create table "programming_languages" (
       id uuid not null,
        name varchar(255),
        primary key (id)
    );

    create table "projects" (
       id uuid not null,
        link varchar(255),
        name varchar(255),
        year int4 not null,
        resume_id uuid,
        primary key (id)
    );

    create table "resumes" (
       id uuid not null,
        additionalInformation varchar(255),
        currentJob varchar(255),
        name varchar(255),
        otherTechnologies varchar(255),
        quote varchar(255),
        user_id uuid,
        primary key (id)
    );

    create table "resumes_databases" (
       "resumes_id" uuid not null,
        "databases_id" uuid not null,
        primary key ("resumes_id", "databases_id")
    );

    create table "resumes_frameworks" (
       "resumes_id" uuid not null,
        "frameworks_id" uuid not null,
        primary key ("resumes_id", "frameworks_id")
    );

    create table "resumes_programming_languages" (
       "resumes_id" uuid not null,
        "languages_id" uuid not null,
        primary key ("resumes_id", "languages_id")
    );

    create table "users" (
       id uuid not null,
        email varchar(255),
        login varchar(255),
        password varchar(255),
        role varchar(255),
        primary key (id)
    );

    create table "workExperienceItems" (
       id uuid not null,
        endDate date,
        place varchar(255),
        position varchar(255),
        startDate date,
        user_id uuid,
        primary key (id)
    );

    alter table if exists "commentaries" 
       add constraint FKdablc7xw22sjiv3uxfqv1obxk 
       foreign key (user_id) 
       references "users";

    alter table if exists "projects" 
       add constraint FK1nw14p4wsmaofd2gq0se1ej45 
       foreign key (resume_id) 
       references "resumes";

    alter table if exists "resumes" 
       add constraint FKqbc3sssxvg6dp9usbsggxcp28 
       foreign key (user_id) 
       references "users";

    alter table if exists "resumes_databases" 
       add constraint FK5tph83nh36vdxpeva2xvbm4vg 
       foreign key ("databases_id") 
       references "databases";

    alter table if exists "resumes_databases" 
       add constraint FKcp41i35drvo46i82um6ujkvfx 
       foreign key ("resumes_id") 
       references "resumes";

    alter table if exists "resumes_frameworks" 
       add constraint FKlpynkx38madfj8wkdae60k81n 
       foreign key ("frameworks_id") 
       references "frameworks";

    alter table if exists "resumes_frameworks" 
       add constraint FKsw4uy8vra4quas3h2qa4ciwrx 
       foreign key ("resumes_id") 
       references "resumes";

    alter table if exists "resumes_programming_languages" 
       add constraint FK4i3foluedkhyxgf23rb96w90t 
       foreign key ("languages_id") 
       references "programming_languages";

    alter table if exists "resumes_programming_languages" 
       add constraint FK7c0bouqb519pb5jdakjed0883 
       foreign key ("resumes_id") 
       references "resumes";

    alter table if exists "workExperienceItems" 
       add constraint FKlcko3v8u1whon6ibw5j2yd6e3 
       foreign key (user_id) 
       references "resumes";
