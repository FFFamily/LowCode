create table a_test
(
    id        varchar(50) not null
        primary key,
    test_id   varchar(50) not null,
    create_by varchar(50) null,
    create_at varchar(50) null,
    update_by varchar(50) null,
    update_at varchar(50) null
);

create table application
(
    id          int auto_increment
        primary key,
    code        varchar(50)  null,
    name        varchar(50)  null,
    status      int          null,
    type        int          null,
    create_by   varchar(255) null,
    create_time timestamp    null,
    update_by   varchar(255) null,
    update_time timestamp    null,
    remark      text         null,
    tenant_id   int          null
);

create table bumne
(
    id         varchar(50) not null
        primary key,
    conpany_id varchar(50) not null,
    create_by  varchar(50) null,
    create_at  varchar(50) null,
    update_by  varchar(50) null,
    update_at  varchar(50) null
);

create table conpany
(
    id        varchar(50) not null
        primary key,
    create_by varchar(50) null,
    create_at varchar(50) null,
    update_by varchar(50) null,
    update_at varchar(50) null
);

create table core_data_source
(
    id          int auto_increment
        primary key,
    name        varchar(50)  null,
    type        varchar(100) null,
    url         varchar(255) null,
    password    varchar(255) null,
    username    varchar(255) null,
    status      int          null,
    tenant_id   int          null,
    create_by   varchar(50)  null,
    create_time datetime     null,
    update_by   varchar(50)  null,
    update_time datetime     null,
    remark      text         null,
    ds_name     varchar(100) null
);

create table core_data_source_config
(
    id            varchar(100) not null
        primary key,
    name          varchar(255) null,
    sql_statement varchar(255) null
);

create table core_data_source_config_link
(
    id                    int auto_increment
        primary key,
    data_source_id        varchar(100) null,
    data_source_config_id varchar(100) null
);

create table fake_user
(
    id       varchar(100) null,
    username varchar(100) null,
    dept     varchar(100) null
);

create table form
(
    id                varchar(100) not null
        primary key,
    name              varchar(100) null,
    code              varchar(100) null,
    type              varchar(100) null,
    view_form_id      varchar(100) null,
    view_list_form_id varchar(100) null,
    form_status       varchar(100) null
);

create table form_action
(
    ac_type     varchar(100) null,
    ac_resource varchar(100) null,
    ac_target   varchar(100) null,
    ac_config   json         null,
    id          varchar(100) not null
        primary key,
    ac_name     varchar(100) null
);

create table form_action_condition
(
    id           varchar(100) not null
        primary key,
    action_id    varchar(100) null,
    condition_id varchar(100) null
);

create table form_condition
(
    id        varchar(100) not null
        primary key,
    form_id   varchar(100) null,
    cd_name   varchar(100) null,
    cd_type   varchar(100) null,
    cd_config json         null,
    cd_enable varchar(100) null
);

create table form_show_rule
(
    id                 varchar(100) null,
    name               varchar(100) null,
    pre_condition_node varchar(100) null,
    action             varchar(100) null,
    form_id            varchar(100) null
);

create table form_table
(
    id         varchar(100) not null
        primary key,
    form_id    varchar(100) null,
    name       varchar(100) null,
    table_name varchar(100) null,
    type       varchar(100) null
);

create table form_table_field
(
    id             varchar(100) not null,
    form_id        varchar(100) null,
    form_table_id  varchar(100) null,
    code           varchar(100) null,
    type           varchar(100) null,
    interface_type varchar(100) null,
    name           varchar(100) null,
    status         varchar(100) null,
    options        json         null,
    description    varchar(100) null,
    field_index    varchar(100) null
);

create table sys_role
(
    id             bigint auto_increment comment '角色ID'
        primary key,
    tenant_code    varchar(32) default ''  null comment '租户编号',
    name           varchar(30)             not null comment '角色名称',
    permission_key varchar(100)            not null comment '角色权限字符串',
    sort           int                     not null comment '显示顺序',
    data_scope     char        default '1' null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status         char                    not null comment '角色状态（0正常 1停用）',
    del_flag       char        default '0' null comment '删除标志（0代表存在 2代表删除）',
    create_by      varchar(64) default ''  null comment '创建者',
    create_time    datetime                null comment '创建时间',
    update_by      varchar(64) default ''  null comment '更新者',
    update_time    datetime                null comment '更新时间'
)
    comment '角色信息表' charset = utf8mb3
                         row_format = DYNAMIC;

create table sys_user
(
    id           bigint auto_increment comment '用户ID'
        primary key,
    tenant_code  varchar(32)  default ''   null comment '租户编号',
    dept_id      bigint                    null comment '部门ID',
    login_name   varchar(30)               not null comment '登录账号',
    user_name    varchar(30)  default ''   null comment '用户昵称',
    user_type    varchar(2)   default '00' null comment '用户类型（00系统用户 01注册用户 02租户管理员）',
    email        varchar(50)  default ''   null comment '用户邮箱',
    phone_number varchar(11)  default ''   null comment '手机号码',
    sex          char         default '0'  null comment '用户性别（0男 1女 2未知）',
    avatar       varchar(100) default ''   null comment '头像路径',
    password     varchar(50)  default ''   null comment '密码',
    salt         varchar(20)  default ''   null comment '盐加密',
    status       char         default '0'  null comment '帐号状态（0正常 1停用）',
    del_flag     char         default '0'  null comment '删除标志（0代表存在 2代表删除）',
    login_client varchar(100)              null comment '最后登陆使用客户端',
    login_ip     varchar(50)  default ''   null comment '最后登陆IP',
    login_date   datetime                  null comment '最后登陆时间',
    create_by    varchar(64)  default ''   null comment '创建者',
    create_time  datetime                  null comment '创建时间',
    update_by    varchar(64)  default ''   null comment '更新者',
    update_time  datetime                  null comment '更新时间',
    remark       varchar(500)              null comment '备注'
)
    comment '用户信息表' charset = utf8mb3
                         row_format = DYNAMIC;

create table test
(
    id        varchar(50)  not null
        primary key,
    create_by varchar(50)  null,
    create_at varchar(50)  null,
    update_bt varchar(50)  null,
    update_at varchar(50)  null,
    company   varchar(255) null,
    select_da varchar(255) null,
    code      varchar(255) null,
    time      varchar(255) null,
    textgg    varchar(255) null
);

create table view_form
(
    id          varchar(100) null,
    form_id     varchar(100) null,
    type        varchar(100) null,
    name        varchar(100) null,
    system_type varchar(100) null
);

create table view_form_config
(
    view_form_id varchar(100) null,
    type         varchar(100) null,
    name         varchar(100) null,
    system_type  varchar(100) null,
    is_show      varchar(100) null,
    show_config  json         null,
    options      json         null,
    id           varchar(100) not null
        primary key
);

