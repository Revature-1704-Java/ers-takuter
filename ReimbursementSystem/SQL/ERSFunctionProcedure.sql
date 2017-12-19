create or replace procedure ReimReq
(eid in integer, did in integer, amount in NUMBER, reason in varchar2) AS
BEGIN
    insert into REIMBURSEMENT(EMP_ID, DPT_ID, REIM_AMOUNT, REIM_REASON) values (eid, did, amount, reason);
END;
/

create or replace procedure CompleteReq
(aprvl in char, rid in integer) AS
BEGIN
    update REIMBURSEMENT
    set REIM_APPROVAL = aprvl , REIM_COMPLETE = 1;
END;
/