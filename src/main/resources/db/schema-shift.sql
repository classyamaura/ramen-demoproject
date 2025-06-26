-- シフト関連テーブルのスキーマ定義用ファイル
SELECT 1;

-- スタッフマスタテーブル
CREATE TABLE staff (
	staff_id INT PRIMARY KEY,
	staff_name VARCHAR(255)
);

-- シフト詳細テーブル
CREATE TABLE shifts (
    shift_id INT AUTO_INCREMENT PRIMARY KEY,
    staff_id INT,
    shift_date DATE,
    start_time TIME,
    end_time TIME,
    remarks VARCHAR(500),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

-- TODO:
CREATE TABLE part_time_staff (
	id int,
	name varchar(255),
	date varchar(255),
	syukkin varchar(100),
	taikin varchar(100),
	reason varchar(500)
	);	