-- 勤怠関連テーブルのスキーマ定義用ファイル
SELECt 1;
-- TODO:

CREATE TABLE kintai (
    name VARCHAR(100) NOT NULL,            -- 従業員の名前
    start_time TIMESTAMP NOT NULL,         -- 出勤時間
    end_time TIMESTAMP NOT NULL,           -- 退勤時間
    work_time INTERVAL DAY TO SECOND NOT NULL  -- 勤務時間（INTERVAL型）
);