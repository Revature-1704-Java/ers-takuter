CREATE USER ERS
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to ERS;
GRANT resource to ERS;
GRANT create session to ERS;
GRANT create table to ERS;
GRANT create view to ERS;

conn ERS/p4ssw0rd

create table EMPLOYEE(
    EMP_ID integer,
    EMP_NAME varchar2(20) not null,
    EMP_PASSWORD varchar2(32) not null,
    EMP_DPT integer default 1 not null,
    CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_ID)
);

create sequence SQ_EMP_PK
    start with 1
    minvalue 1
    maxvalue 1000;

create trigger TR_EMPLOYEE_BI
before insert on EMPLOYEE
    for each row
        begin
            select SQ_EMP_PK.NEXTVAL
            into :New.EMP_ID from DUAL;
end;
/


create table REIMBURSEMENT(
    REIM_ID integer,
    EMP_ID integer not null,
    REIM_REASON varchar2(200),
    REIM_AMOUNT number(10,2) DEFAULT 0.00,
    DPT_ID integer not null,
    REIM_APPROVAL char(1) default 0 not null,
    REIM_COMPLETE char(1) default 0 not null,
    CONSTRAINT PK_REIMBURSEMENT PRIMARY KEY(REIM_ID),
    CONSTRAINT CK_BOOL_REIMBURSEMENT_APRVL CHECK (REIM_APPROVAL = 0 or  REIM_APPROVAL = 1),
    CONSTRAINT CK_BOOL_REIMBURSEMENT_CMP CHECK (REIM_COMPLETE = 0 or  REIM_COMPLETE = 1) 
);

create sequence SQ_REIM_PK
    start with 1
    minvalue 1
    maxvalue 10000;

create trigger TR_REIM_BI
before insert on REIMBURSEMENT
    for each row
        begin
            select SQ_REIM_PK.NEXTVAL
            into :NEW.REIM_ID from DUAL;
end;
/

create table DEPARTMENT(
    DPT_ID integer,
    DPT_NAME varchar(25) not null,
    MGR_ID integer,
    CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DPT_ID)
);


create sequence SQ_DPT_PK
    start with 1
    minvalue 1
    maxvalue 20;

create trigger TR_DEPARTMENT_BI
before insert on DEPARTMENT
    for each row
        begin
            select SQ_DPT_PK.NEXTVAL
            into :New.DPT_ID from DUAL;
end;
/

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_DPT FOREIGN KEY(EMP_DPT) REFERENCES DEPARTMENT(DPT_ID);

ALTER TABLE DEPARTMENT ADD CONSTRAINT FK_DEPARTMENT_MGR FOREIGN KEY(MGR_ID) REFERENCES EMPLOYEE(EMP_ID);

ALTER TABLE REIMBURSEMENT ADD (CONSTRAINT FK_REIMBURSEMENT_DEPARTMENT FOREIGN KEY(DPT_ID) REFERENCES DEPARTMENT(DPT_ID),
CONSTRAINT FK_REIMBURSEMENT_EMPLOYEE FOREIGN KEY(EMP_ID) REFERENCES EMPLOYEE(EMP_ID));

commit;

DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;
DROP TABLE REIMBURSEMENT;