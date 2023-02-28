INSERT INTO m_user (user_name,email,password,role,user_status,requested_at)
VALUES('山田太郎','yamada@co.jp','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','ROLE_GENERAL','1','2022-07-01 00:00:00');
-- ,('アイウエオ','asd@co.jp','$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa','ROLE_GENERAL','0','2022-07-01 00:00:00')
-- ,('2','8','9:00:00','1:00:00','18:00:00','2022-4-1','LIM','2022-6-30');

INSERT INTO m_month (contract_id,years,months)
VALUES('1','2022','04');

INSERT INTO work_time (month_id,work_day,start_time,break_time,end_time)
VALUES('1','2022-4-1','09:00:00','01:00:00','18:00:00');

-- '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa'
