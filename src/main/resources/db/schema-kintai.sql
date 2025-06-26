-- 勤怠関連テーブルのスキーマ定義用ファイル
DROP TABLE IF EXISTS kintai;

CREATE TABLE kintai (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 一意なID（自動採番）
    name VARCHAR(100) NOT NULL,        -- 従業員の名前
    start_time TIMESTAMP NOT NULL,     -- 出勤時間
    end_time TIMESTAMP NOT NULL        -- 退勤時間
);