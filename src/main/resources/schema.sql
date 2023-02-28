CREATE TABLE IF NOT EXISTS m_user (
    user_id INT auto_increment PRIMARY KEY,
    user_name VARCHAR(100), 
    email VARCHAR(50),
    password VARCHAR(100),
    role VARCHAR(50),
    user_status INT,
    requested_at VARCHAR(19)
);

CREATE TABLE IF NOT EXISTS m_contract (
    contract_id INT auto_increment PRIMARY KEY,
    user_id INT,
    contract_time INT, 
    start_time TIME,
    break_time TIME,
    end_time TIME,
    start_date DATE,
    office_name VARCHAR(100),
    end_date DATE
);

CREATE TABLE IF NOT EXISTS m_month (
    month_id INT auto_increment PRIMARY KEY,
    contract_id INT,
    years INT,
    months INT
);

CREATE TABLE IF NOT EXISTS work_time (
    work_time_id INT auto_increment PRIMARY KEY,
    month_id INT,
    work_day DATE,
    start_time TIME,
    break_time TIME,
    end_time TIME
);