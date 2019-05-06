create table code
(
  id          int auto_increment
  comment '主键'
    primary key,
  code_id     char(32)                            not null
  comment '代码ID',
  code_text   text                                not null
  comment '代码内容',
  create_time timestamp default CURRENT_TIMESTAMP not null
  comment '代码创建时间',
  update_time timestamp default CURRENT_TIMESTAMP not null
  comment '代码修改时间',
  user_id     int                                 not null
  comment '用户ID'
)
  comment '代码数据表';

create table project
(
  id          int auto_increment
  comment '主键'
    primary key,
  proj_id     char(32)                            not null
  comment '工程ID(关联treenode中parent_id)
',
  proj_name   varchar(30)                         not null
  comment '项目名称',
  user_id     int                                 not null
  comment '用户ID',
  create_time timestamp default CURRENT_TIMESTAMP not null,
  update_time timestamp default CURRENT_TIMESTAMP null
)
  comment '项目工程表';

create table treenode
(
  id          int auto_increment
  comment '主键'
    primary key,
  parent_id   char(32)                            not null
  comment '父节点ID',
  child_id    char(32)                            null
  comment '子节点ID(关联code中code_id)',
  node_name   varchar(15)                         not null
  comment 'child节点名称',
  label       char                                not null
  comment 'child_node状态:0为目录,1为文件
',
  create_time timestamp default CURRENT_TIMESTAMP not null
  comment '子节点创建时间
',
  update_time timestamp default CURRENT_TIMESTAMP not null
  comment '子节点修改时间',
  constraint ide_treenode_subId_uindex
  unique (child_id)
)
  comment '工程目录树节点储存';


