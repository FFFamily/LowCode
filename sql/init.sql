create table core_data_source
(
    id          int auto_increment
        primary key,
    name        varchar(50)  null,
    type        int          null,
    url         varchar(255) null,
    password    varchar(255) null,
    username    varchar(255) null,
    status      int          null,
    tenant_id   int          null,
    create_by   varchar(50)  null,
    create_time datetime     null,
    update_by   varchar(50)  null,
    update_time datetime     null,
    remark      text         null
);

create table core_data_source_table
(
    id                  int auto_increment
        primary key,
    code_data_source_id int          null,
    name                varchar(255) null,
    type                int          null,
    table_name          varchar(255) null
);
