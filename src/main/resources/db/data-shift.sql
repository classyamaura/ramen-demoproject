-- シフト関連テーブルのデータ定義用ファイル
SELECT 1;
-- TODO:
INSERT INTO staff (staff_id, staff_name) VALUES (1, '佐藤 翔太');
INSERT INTO staff (staff_id, staff_name) VALUES (2, '田中 美咲');
INSERT INTO staff (staff_id, staff_name) VALUES (3, '山本 健');
INSERT INTO staff (staff_id, staff_name) VALUES (4, '鈴木 彩乃');
INSERT INTO staff (staff_id, staff_name) VALUES (5, '中村 大輔');
INSERT INTO staff (staff_id, staff_name) VALUES (6, '井上 由佳');
INSERT INTO staff (staff_id, staff_name) VALUES (7, '松本 陽平');
INSERT INTO staff (staff_id, staff_name) VALUES (8, '木村 奈々');
INSERT INTO staff (staff_id, staff_name) VALUES (9, '高橋 亮');
INSERT INTO staff (staff_id, staff_name) VALUES (10, '渡辺 真理');
INSERT INTO staff (staff_id, staff_name) VALUES (11, '藤田 悠人');
INSERT INTO staff (staff_id, staff_name) VALUES (12, '石井 結衣');
INSERT INTO staff (staff_id, staff_name) VALUES (13, '小林 翔');
INSERT INTO staff (staff_id, staff_name) VALUES (14, '長谷川 優');
INSERT INTO staff (staff_id, staff_name) VALUES (15, '大野 沙羅');

-- アルバイトスタッフのシフトデータ例 (2025年に更新、備考欄を空白に)
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (2, '2025-06-01', '09:00:00', '15:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (3, '2025-06-01', '10:00:00', '16:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (4, '2025-06-02', '11:00:00', '17:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (5, '2025-06-02', '12:00:00', '18:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (6, '2025-06-03', '09:00:00', '14:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (7, '2025-06-03', '17:00:00', '22:00:00', '');

-- 追加のシフトデータ (2025年に更新、備考欄を空白に)
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (1, '2025-06-01', '10:00:00', '18:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (8, '2025-06-04', '13:00:00', '20:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (9, '2025-06-04', '10:00:00', '16:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (10, '2025-06-05', '09:00:00', '15:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (11, '2025-06-05', '16:00:00', '22:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (12, '2025-06-06', '11:00:00', '19:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (13, '2025-06-06', '09:00:00', '13:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (14, '2025-06-07', '18:00:00', '23:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (15, '2025-06-07', '14:00:00', '21:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (1, '2025-06-08', '09:00:00', '17:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (2, '2025-06-08', '12:00:00', '19:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (3, '2025-06-08', '11:00:00', '18:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (4, '2025-06-09', '10:00:00', '17:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (5, '2025-06-09', '15:00:00', '22:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (6, '2025-06-10', '09:00:00', '16:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (7, '2025-06-10', '14:00:00', '21:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (8, '2025-06-11', '10:00:00', '18:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (9, '2025-06-11', '13:00:00', '20:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (10, '2025-06-12', '09:00:00', '14:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (11, '2025-06-12', '16:00:00', '23:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (12, '2025-06-13', '11:00:00', '17:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (13, '2025-06-13', '08:00:00', '12:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (14, '2025-06-14', '17:00:00', '23:00:00', '');
INSERT INTO shifts (staff_id, shift_date, start_time, end_time, remarks) VALUES (15, '2025-06-14', '13:00:00', '20:00:00', '');






